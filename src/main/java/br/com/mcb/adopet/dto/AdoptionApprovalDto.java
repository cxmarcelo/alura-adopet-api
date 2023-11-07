package br.com.mcb.adopet.dto;

import jakarta.validation.constraints.NotNull;

public record AdoptionApprovalDto(@NotNull Long adoptionId) {
}
