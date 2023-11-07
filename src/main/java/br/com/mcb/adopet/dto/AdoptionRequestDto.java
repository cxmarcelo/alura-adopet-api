package br.com.mcb.adopet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdoptionRequestDto(@NotNull Long petId, @NotNull Long tutorId, @NotBlank String reason) {
}
