package br.com.nursingcalculator.user.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticateInputDto(
    @NotBlank
    @Email
    String email,
    @NotBlank
    String password) {
}
