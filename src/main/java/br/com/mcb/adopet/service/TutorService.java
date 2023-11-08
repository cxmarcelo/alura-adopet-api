package br.com.mcb.adopet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mcb.adopet.dto.TutorRegisterDto;
import br.com.mcb.adopet.dto.TutorUpdateDto;
import br.com.mcb.adopet.model.TutorModel;
import br.com.mcb.adopet.repository.TutorRepository;
import jakarta.validation.ValidationException;

@Service
public class TutorService {

	@Autowired
	private TutorRepository repository;


	public void register(TutorRegisterDto dto) {
		boolean isAlreadyRegistered = repository.existsByPhoneOrEmail(dto.phone(), dto.email());

		if (isAlreadyRegistered) {
			throw new ValidationException("Dados já cadastrados para outro tutor!");
		}

		repository.save(new TutorModel(dto));
	}

	public void atualizar(TutorUpdateDto dto) {
		TutorModel tutor = repository.getReferenceById(dto.id());
		tutor.update(dto);
	}

}
