package br.com.mcb.adopet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mcb.adopet.model.PetModel;

public interface PetRepository extends JpaRepository<PetModel, Long> {

}
