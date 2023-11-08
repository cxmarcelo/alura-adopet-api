package br.com.mcb.adopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mcb.adopet.dto.AdoptionApprovalDto;
import br.com.mcb.adopet.dto.AdoptionRejectionDto;
import br.com.mcb.adopet.dto.AdoptionRequestDto;
import br.com.mcb.adopet.service.AdoptionService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> request(@RequestBody @Valid AdoptionRequestDto dto) {
        try {
            this.adoptionService.request(dto);
            return ResponseEntity.ok("Adoção solciitada com sucesso!");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/approve")
    @Transactional
    public ResponseEntity<String> approve(@RequestBody @Valid AdoptionApprovalDto dto) {
        this.adoptionService.approve(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/disapprove")
    @Transactional
    public ResponseEntity<String> disapprove(@RequestBody @Valid AdoptionRejectionDto dto) {
        this.adoptionService.disapprove(dto);
        return ResponseEntity.ok().build();
    }

}
