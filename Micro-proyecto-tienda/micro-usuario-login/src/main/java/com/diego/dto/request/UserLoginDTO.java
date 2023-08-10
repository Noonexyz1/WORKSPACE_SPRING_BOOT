package com.diego.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(
        @NotBlank
        String correo,
        @NotBlank
        String contrasena
) {
}
