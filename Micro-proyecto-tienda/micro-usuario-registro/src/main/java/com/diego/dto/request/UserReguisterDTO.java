package com.diego.dto.request;

import com.diego.enums.UserTypeRoles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserReguisterDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String correo,
        @NotBlank
        String contrasena,
        @NotNull
        UserTypeRoles role
) {
}
