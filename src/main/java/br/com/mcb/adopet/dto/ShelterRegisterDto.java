package br.com.mcb.adopet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ShelterRegisterDto(

		@NotBlank
		String name,

		@NotBlank
		@Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
		String phone,

		@NotBlank
		@Email
		String email) {

}
