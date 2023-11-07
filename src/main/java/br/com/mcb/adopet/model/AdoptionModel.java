package br.com.mcb.adopet.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_adoptions")
public class AdoptionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime date;

	@ManyToOne(fetch = FetchType.LAZY)
	private TutorModel tutor;

	@OneToOne(fetch = FetchType.LAZY)
	private PetModel pet;

	private String reason;

	@Enumerated(EnumType.STRING)
	private AdoptionStatusEnum status;

	private String justificationStatus;

	public AdoptionModel(TutorModel tutor, PetModel pet, String reason) {
		this.tutor = tutor;
		this.pet = pet;
		this.reason = reason;
		this.status = AdoptionStatusEnum.AWAITING_EVALUATION;
		this.date = LocalDateTime.now();
	}

	public AdoptionModel(){}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AdoptionModel adocao = (AdoptionModel) o;
		return Objects.equals(id, adocao.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public TutorModel getTutor() {
		return tutor;
	}

	public PetModel getPet() {
		return pet;
	}

	public void setPet(PetModel pet) {
		this.pet = pet;
	}

	public String getReason() {
		return reason;
	}

	public AdoptionStatusEnum getStatus() {
		return status;
	}

	public String getJustificationStatus() {
		return justificationStatus;
	}

	public void approve() {
		this.status = AdoptionStatusEnum.APPROVED;
	}

	public void disapprove(String justificationStatus) {
		this.status = AdoptionStatusEnum.DISAPPROVED;
		this.justificationStatus = justificationStatus;
	}

}
