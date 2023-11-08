package br.com.mcb.adopet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mcb.adopet.dto.PetDto;
import br.com.mcb.adopet.model.PetModel;
import br.com.mcb.adopet.service.PetService;

@RestController
@RequestMapping("/pets")
public class PetController {

	@Autowired
	private PetService service;

	@GetMapping
	public ResponseEntity<List<PetDto>> listAllAvailable() {
		List<PetModel> list = service.findAllAvaliable();
		List<PetDto> listDto = PetDto.convert(list);
		return ResponseEntity.ok(listDto);
	}

}
