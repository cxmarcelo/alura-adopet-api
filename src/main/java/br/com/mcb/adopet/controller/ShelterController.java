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

import br.com.mcb.adopet.dto.PetDto;
import br.com.mcb.adopet.dto.PetRegisterDto;
import br.com.mcb.adopet.dto.ShelterDto;
import br.com.mcb.adopet.dto.ShelterRegisterDto;
import br.com.mcb.adopet.model.PetModel;
import br.com.mcb.adopet.model.ShelterModel;
import br.com.mcb.adopet.service.PetService;
import br.com.mcb.adopet.service.ShelterService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/shelters")
public class ShelterController {

	@Autowired
	private ShelterService service;

	@Autowired
	private PetService petService;

	@GetMapping
	public ResponseEntity<List<ShelterDto>> list() {
		List<ShelterModel> list = service.findAll();
		List<ShelterDto> listDto = ShelterDto.convert(list);
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<String> register(@RequestBody @Valid ShelterRegisterDto shelter) {
		try {
			service.register(shelter);
			return ResponseEntity.ok().body("Abrigo Criado");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Dados já cadastrados para outro abrigo!");
		}
	}

	@GetMapping("/{idOrName}/pets")
	public ResponseEntity<List<PetDto>> listPets(@PathVariable String idOrName) {
		List<PetModel> list = service.listPets(idOrName);
		List<PetDto> listDto = PetDto.convert(list);
		return ResponseEntity.ok().body(listDto);

	}

	@PostMapping("/{idOrName}/pets")
	@Transactional
	public ResponseEntity<String> registerPet(@PathVariable String idOrName, @RequestBody @Valid PetRegisterDto dto) {
		try {
			ShelterModel shelter = service.findShelter(idOrName);
			petService.registerPet(shelter, dto);
			return ResponseEntity.ok().build();
		} catch (ValidationException exception) {
			return ResponseEntity.notFound().build();
		}
	}

}
