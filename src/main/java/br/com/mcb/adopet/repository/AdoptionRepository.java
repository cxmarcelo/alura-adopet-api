package br.com.mcb.adopet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mcb.adopet.model.AdoptionModel;
import br.com.mcb.adopet.model.AdoptionStatusEnum;

public interface AdoptionRepository extends JpaRepository<AdoptionModel, Long> {

	boolean existsByPetIdAndStatus(Long petId, AdoptionStatusEnum status);


}
