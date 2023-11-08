package br.com.mcb.adopet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mcb.adopet.model.TutorModel;

public interface TutorRepository extends JpaRepository<TutorModel, Long> {

	boolean existsByPhoneOrEmail(String phone, String email);

}
