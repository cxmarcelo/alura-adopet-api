package br.com.mcb.adopet.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mcb.adopet.dto.AdoptionRequestDto;
import br.com.mcb.adopet.excpetion.ValidationException;
import br.com.mcb.adopet.model.AdoptionStatusEnum;
import br.com.mcb.adopet.repository.AdoptionRepository;

@Component
public class PetAdoptionInProgressValidation implements AdoptionRequestValidation {

	@Autowired
	private AdoptionRepository adocaoRepository;

	public void validate(AdoptionRequestDto dto) {
		boolean petTemAdocaoEmAndamento = adocaoRepository
				.existsByPetIdAndStatus(dto.petId(), AdoptionStatusEnum.AWAITING_EVALUATION);

		if (petTemAdocaoEmAndamento) {
			throw new ValidationException("Pet já está aguardando avaliação para ser adotado!");
		}
	}

}
