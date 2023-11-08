package br.com.mcb.adopet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mcb.adopet.dto.PetRegisterDto;
import br.com.mcb.adopet.model.PetModel;
import br.com.mcb.adopet.model.ShelterModel;
import br.com.mcb.adopet.repository.PetRepository;

@Service
public class PetService {

	@Autowired
	private PetRepository repository;

	public List<PetModel> findAllAvaliable() {
		return repository.findByAdopted(false);
	}

	public void registerPet(ShelterModel shelter, PetRegisterDto dto) {
		this.repository.save(new PetModel(dto, shelter));
	}

}
