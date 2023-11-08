package br.com.mcb.adopet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mcb.adopet.model.PetModel;
import br.com.mcb.adopet.model.ShelterModel;

public interface PetRepository extends JpaRepository<PetModel, Long> {

	List<PetModel> findByAdopted(boolean adopetd);
	
	List<PetModel> findByShelter(ShelterModel shelter);

}
