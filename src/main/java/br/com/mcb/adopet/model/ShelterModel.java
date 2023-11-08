package br.com.mcb.adopet.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.mcb.adopet.dto.ShelterRegisterDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_shelters")
public class ShelterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone;
	private String email;

	@OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference("shelter_pets")
	private List<PetModel> pets;

	public ShelterModel(ShelterRegisterDto dto) {
		this.name = dto.name();
		this.phone = dto.phone();
		this.email = dto.email();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ShelterModel abrigo = (ShelterModel) o;
		return Objects.equals(id, abrigo.id);
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

	public List<PetModel> getPets() {
		return pets;
	}

	public void setPets(List<PetModel> pets) {
		this.pets = pets;
	}

}
