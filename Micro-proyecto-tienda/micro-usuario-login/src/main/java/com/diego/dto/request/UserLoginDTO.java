package com.diego.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserLoginDTO(
        @NotBlank
        String correo,
        @NotBlank
        String contrasena
) {
}
