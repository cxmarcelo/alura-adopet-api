package br.com.mcb.adopet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mcb.adopet.model.ShelterModel;

public interface ShelterRepository extends JpaRepository<ShelterModel, Long> {

	boolean existsByNameOrPhoneOrEmail(String name, String phone, String email);

	Optional<ShelterModel> findByName(String name);

}
