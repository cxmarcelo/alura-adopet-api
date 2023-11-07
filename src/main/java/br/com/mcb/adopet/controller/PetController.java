package br.com.mcb.adopet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mcb.adopet.model.PetModel;
import br.com.mcb.adopet.repository.PetRepository;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository repository;

    @GetMapping
    public ResponseEntity<List<PetModel>> listAllAvailable() {
        List<PetModel> pets = repository.findAll();
        List<PetModel> available = new ArrayList<>();
        for (PetModel pet : pets) {
            if (pet.getAdopted() == false) {
            	available.add(pet);
            }
        }
        return ResponseEntity.ok(available);
    }

}
