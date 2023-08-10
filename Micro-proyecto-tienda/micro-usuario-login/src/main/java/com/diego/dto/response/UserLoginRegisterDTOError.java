package com.diego.dto.response;

import lombok.Builder;

@Builder
public record UserLoginRegisterDTOError(
        String code,
        String message
) {
}
