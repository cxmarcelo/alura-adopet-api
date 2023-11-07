package br.com.mcb.adopet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdoptionRejectionDto(@NotNull Long adoptionId, @NotBlank String justification) {
}
