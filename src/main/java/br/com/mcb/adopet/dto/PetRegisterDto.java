package br.com.mcb.adopet.dto;

import br.com.mcb.adopet.model.PetTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetRegisterDto(
		@NotNull
		PetTypeEnum type, 

		@NotBlank
		String name, 

		@NotBlank
		String race,

		@NotNull
		Integer age,

		@NotBlank
		String color,

		@NotNull
		Float weight
		) {

}
