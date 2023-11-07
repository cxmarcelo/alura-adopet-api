package br.com.mcb.adopet.validations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mcb.adopet.dto.AdoptionRequestDto;
import br.com.mcb.adopet.excpetion.ValidationException;
import br.com.mcb.adopet.model.AdoptionModel;
import br.com.mcb.adopet.model.AdoptionStatusEnum;
import br.com.mcb.adopet.model.TutorModel;
import br.com.mcb.adopet.repository.AdoptionRepository;
import br.com.mcb.adopet.repository.TutorRepository;

@Component
public class TutorAdoptionLimitValidation implements AdoptionRequestValidation {

	@Autowired
	private AdoptionRepository adocaoRepository;

	@Autowired
	private TutorRepository tutorRepository;

	public void validate(AdoptionRequestDto dto) {
		List<AdoptionModel> adoptions = adocaoRepository.findAll();
		TutorModel tutor = tutorRepository.getReferenceById(dto.tutorId());
		for (AdoptionModel a : adoptions) {
			int count = 0;
			if (a.getTutor() == tutor && a.getStatus() == AdoptionStatusEnum.APPROVED) {
				count = count + 1;
			}
			if (count == 5) {
				throw new ValidationException("Tutor chegou ao limite máximo de 5 adoções!");
			}
		}
	}

}
