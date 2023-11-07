package br.com.mcb.adopet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mcb.adopet.model.ShelterModel;

public interface ShelterRepository extends JpaRepository<ShelterModel, Long> {

	boolean existsByName(String name);
	boolean existsByPhone(String telefone);
	boolean existsByEmail(String email);
	ShelterModel findByName(String name);

}
