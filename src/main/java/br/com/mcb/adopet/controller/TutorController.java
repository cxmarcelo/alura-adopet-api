package br.com.mcb.adopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mcb.adopet.dto.TutorRegisterDto;
import br.com.mcb.adopet.dto.TutorUpdateDto;
import br.com.mcb.adopet.service.TutorService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/tutors")
public class TutorController {

	@Autowired
	private TutorService service;

	@PostMapping
	@Transactional
	public ResponseEntity<String> register(@RequestBody @Valid TutorRegisterDto dto) {
		try {
			service.register(dto);
			return ResponseEntity.ok().build();
		} catch (ValidationException exception) {
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}

	@PutMapping
	@Transactional
	public ResponseEntity<String> update(@RequestBody @Valid TutorUpdateDto dto) {
		try {
			service.atualizar(dto);
			return ResponseEntity.ok().build();
		} catch (ValidationException exception) {
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}

}
