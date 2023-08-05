package com.diego.dto.response;

import lombok.Builder;

@Builder
public record UserReguisterErrorDTO(
        String code,
        String message,
        String description
) {

}
