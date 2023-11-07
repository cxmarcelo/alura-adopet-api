package br.com.mcb.adopet.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mcb.adopet.dto.AdoptionApprovalDto;
import br.com.mcb.adopet.dto.AdoptionRejectionDto;
import br.com.mcb.adopet.dto.AdoptionRequestDto;
import br.com.mcb.adopet.model.AdoptionModel;
import br.com.mcb.adopet.model.PetModel;
import br.com.mcb.adopet.model.TutorModel;
import br.com.mcb.adopet.repository.AdoptionRepository;
import br.com.mcb.adopet.repository.PetRepository;
import br.com.mcb.adopet.repository.TutorRepository;
import br.com.mcb.adopet.validations.AdoptionRequestValidation;

@Service
public class AdocaoService {

	@Autowired
	private AdoptionRepository repository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private List<AdoptionRequestValidation> validations;

	public void request(AdoptionRequestDto dto) {
		PetModel pet = petRepository.getReferenceById(dto.petId());
		TutorModel tutor = tutorRepository.getReferenceById(dto.tutorId());

		validations.forEach(v -> v.validate(dto));

		AdoptionModel adoption = new AdoptionModel(tutor, pet, dto.reason());
		repository.save(adoption);

		emailService.enviarEmail(
				adoption.getPet().getShelter().getEmail(),
				"Solicitação de adoção",
				"Olá " + adoption.getPet().getShelter().getName() +"!\n\nUma solicitação de adoção foi registrada hoje para o pet: " + adoption.getPet().getName() +". \nFavor avaliar para aprovação ou reprovação.");
	}

	public void approve(AdoptionApprovalDto dto) {
		AdoptionModel adoption = repository.getReferenceById(dto.adoptionId());
		adoption.approve();

		emailService.enviarEmail(
				adoption.getPet().getShelter().getEmail(),
				"Adoção aprovada",
				"Parabéns " + adoption.getTutor().getName() +"!\n\nSua adoção do pet " + adoption.getPet().getName() +", solicitada em " + adoption.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " + adoption.getPet().getShelter().getName() +" para agendar a busca do seu pet.");
	}

	public void disapprove(AdoptionRejectionDto dto) {
		AdoptionModel adoption = repository.getReferenceById(dto.adoptionId());
		adoption.disapprove(dto.justification());

		emailService.enviarEmail(
				adoption.getPet().getShelter().getEmail(),
				"Solicitação de adoção",
				"Olá " +adoption.getTutor().getName() +"!\n\nInfelizmente sua adoção do pet " +adoption.getPet().getName() +", solicitada em " +adoption.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " + adoption.getPet().getShelter().getName() +" com a seguinte justificativa: " + adoption.getJustificationStatus());
	}

}
