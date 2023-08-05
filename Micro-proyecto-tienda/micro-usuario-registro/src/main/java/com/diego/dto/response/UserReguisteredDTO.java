package com.diego.dto.response;

import lombok.Builder;

@Builder
public record UserReguisteredDTO(
        String nombre,
        String correo
) {
}
