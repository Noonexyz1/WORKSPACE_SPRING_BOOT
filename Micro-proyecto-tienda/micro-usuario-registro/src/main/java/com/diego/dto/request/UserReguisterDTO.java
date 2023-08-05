package com.diego.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserReguisterDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String correo,
        @NotBlank
        String contrasena
) {
}
