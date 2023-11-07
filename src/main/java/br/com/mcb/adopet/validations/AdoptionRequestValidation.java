package br.com.mcb.adopet.validations;

import br.com.mcb.adopet.dto.AdoptionRequestDto;

public interface AdoptionRequestValidation {

    void validate(AdoptionRequestDto dto);

}
