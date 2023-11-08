package br.com.mcb.adopet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mcb.adopet.dto.ShelterRegisterDto;
import br.com.mcb.adopet.model.PetModel;
import br.com.mcb.adopet.model.ShelterModel;
import br.com.mcb.adopet.repository.PetRepository;
import br.com.mcb.adopet.repository.ShelterRepository;
import jakarta.validation.ValidationException;

@Service
public class ShelterService {

	@Autowired
	private ShelterRepository repository;

	@Autowired
	private PetRepository petRepository;
	
	public List<ShelterModel> findAll() {
		return repository.findAll();
	}

	public ShelterModel register(ShelterRegisterDto dto) {
		if (repository.existsByNameOrPhoneOrEmail(dto.name(), dto.phone(), dto.email())) {
			throw new ValidationException("Dados já cadastrados para outro abrigo!");
		} else {
			ShelterModel shelter = new ShelterModel(dto);
			return repository.save(shelter);
		}
	}

	public List<PetModel> listPets(String idOrName) {
		ShelterModel shelter = findShelter(idOrName);
        return petRepository.findByShelter(shelter);
	}
	
	
    public ShelterModel findShelter(String idOrName) {
        Optional<ShelterModel> optional;
        try {
            Long id = Long.parseLong(idOrName);
            optional = repository.findById(id);
        } catch (NumberFormatException exception) {
            optional = repository.findByName(idOrName);
        }

        return optional.orElseThrow(() -> new ValidationException("Abrigo não encontrado"));
    }

}
