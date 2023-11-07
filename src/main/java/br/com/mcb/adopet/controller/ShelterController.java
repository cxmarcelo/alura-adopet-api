package br.com.mcb.adopet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mcb.adopet.model.ShelterModel;
import br.com.mcb.adopet.model.PetModel;
import br.com.mcb.adopet.repository.ShelterRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/shelters")
public class ShelterController {

    @Autowired
    private ShelterRepository repository;

    @GetMapping
    public ResponseEntity<List<ShelterModel>> list() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> register(@RequestBody @Valid ShelterModel shelter) {
        boolean nameAlreadyRegistered = repository.existsByName(shelter.getName());
        boolean phoneAlreadyRegistered = repository.existsByPhone(shelter.getPhone());
        boolean emailAlreadyRegistered = repository.existsByEmail(shelter.getEmail());

        if (nameAlreadyRegistered || phoneAlreadyRegistered || emailAlreadyRegistered) {
            return ResponseEntity.badRequest().body("Dados já cadastrados para outro abrigo!");
        } else {
            repository.save(shelter);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/{idOrName}/pets")
    public ResponseEntity<List<PetModel>> listPets(@PathVariable String idOrName) {
        try {
            Long id = Long.parseLong(idOrName);
            List<PetModel> pets = repository.getReferenceById(id).getPets();
            return ResponseEntity.ok(pets);
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            try {
                List<PetModel> pets = repository.findByName(idOrName).getPets();
                return ResponseEntity.ok(pets);
            } catch (EntityNotFoundException e1) {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PostMapping("/{idOrName}/pets")
    @Transactional
    public ResponseEntity<String> registerPet(@PathVariable String idOrName, @RequestBody @Valid PetModel pet) {
        try {
            Long id = Long.parseLong(idOrName);
            ShelterModel shelter = repository.getReferenceById(id);
            pet.setShelter(shelter);
            pet.setAdopted(false);
            shelter.getPets().add(pet);
            repository.save(shelter);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            try {
                ShelterModel abrigo = repository.findByName(idOrName);
                pet.setShelter(abrigo);
                pet.setAdopted(false);
                abrigo.getPets().add(pet);
                repository.save(abrigo);
                return ResponseEntity.ok().build();
            } catch (EntityNotFoundException e1) {
                return ResponseEntity.notFound().build();
            }
        }
    }

}
