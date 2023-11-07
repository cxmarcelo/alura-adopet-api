package br.com.mcb.adopet.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mcb.adopet.dto.AdoptionRequestDto;
import br.com.mcb.adopet.excpetion.ValidationException;
import br.com.mcb.adopet.model.PetModel;
import br.com.mcb.adopet.repository.PetRepository;

@Component
public class PetAvailableValidation implements AdoptionRequestValidation {

    @Autowired
    private PetRepository petRepository;

    public void validate(AdoptionRequestDto dto) {
        PetModel pet = petRepository.getReferenceById(dto.petId());
        if (pet.getAdopted()) {
            throw new ValidationException("Pet já foi adotado!");
        }
    }

}
