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
public class TutorWithAdoptionInProgressValidation implements AdoptionRequestValidation {

	@Autowired
	private AdoptionRepository adocaoRepository;

	@Autowired
	private TutorRepository tutorRepository;

	public void validate(AdoptionRequestDto dto) {
		List<AdoptionModel> adoptions = adocaoRepository.findAll();
		TutorModel tutor = tutorRepository.getReferenceById(dto.tutorId());
		for (AdoptionModel a : adoptions) {
			if (a.getTutor() == tutor && a.getStatus() == AdoptionStatusEnum.AWAITING_EVALUATION) {
				throw new ValidationException("Tutor já possui outra adoção aguardando avaliação!");
			}
		}
	}

}
