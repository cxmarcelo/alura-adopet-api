package br.com.mcb.adopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mcb.adopet.model.TutorModel;
import br.com.mcb.adopet.repository.TutorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tutors")
public class TutorController {

	@Autowired
	private TutorRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<String> register(@RequestBody @Valid TutorModel tutor) {
		boolean phoneAlreadyRegistered = repository.existsByPhone(tutor.getPhone());
		boolean emailAlreadyRegistered = repository.existsByEmail(tutor.getEmail());

		if (phoneAlreadyRegistered || emailAlreadyRegistered) {
			return ResponseEntity.badRequest().body("Dados já cadastrados para outro tutor!");
		} else {
			repository.save(tutor);
			return ResponseEntity.ok().build();
		}
	}

	@PutMapping
	@Transactional
	public ResponseEntity<String> update(@RequestBody @Valid TutorModel tutor) {
		repository.save(tutor);
		return ResponseEntity.ok().build();
	}

}
