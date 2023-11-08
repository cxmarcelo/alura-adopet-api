package br.com.mcb.adopet.model;

import java.util.List;
import java.util.Objects;

import br.com.mcb.adopet.dto.TutorRegisterDto;
import br.com.mcb.adopet.dto.TutorUpdateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tutors")
public class TutorModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone;
	private String email;

	@OneToMany(mappedBy = "tutor")
	private List<AdoptionModel> adoptions;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TutorModel tutor = (TutorModel) o;
		return Objects.equals(id, tutor.id);
	}

	public TutorModel() {
	}

	public TutorModel(TutorRegisterDto dto) {
		this.name = dto.name();
		this.phone = dto.phone();
		this.email = dto.email();
	}

	public void update(TutorUpdateDto dto) {
		this.name = dto.name();
		this.phone = dto.phone();
		this.email = dto.email();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AdoptionModel> getAdoptions() {
		return adoptions;
	}

	public void setAdoptions(List<AdoptionModel> adoptions) {
		this.adoptions = adoptions;
	}

}
