package br.com.mcb.adopet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "tb_pets")
public class PetModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull
	private PetTypeEnum type;

	@NotBlank
	private String name;

	@NotBlank
	private String race;

	@NotNull
	private Integer age;

	@NotBlank
	private String color;

	@NotNull
	private Float weight;

	private Boolean adopted;

	@ManyToOne
	private ShelterModel shelter;

	@OneToOne(mappedBy = "pet")
	private AdoptionModel adoption;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PetModel pet = (PetModel) o;
		return Objects.equals(id, pet.id);
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

	public PetTypeEnum getType() {
		return type;
	}

	public void setType(PetTypeEnum type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Boolean getAdopted() {
		return adopted;
	}

	public void setAdopted(Boolean adopted) {
		this.adopted = adopted;
	}

	public ShelterModel getShelter() {
		return shelter;
	}

	public void setShelter(ShelterModel shelter) {
		this.shelter = shelter;
	}

	public AdoptionModel getAdoption() {
		return adoption;
	}

	public void setAdoption(AdoptionModel adoption) {
		this.adoption = adoption;
	}

}
