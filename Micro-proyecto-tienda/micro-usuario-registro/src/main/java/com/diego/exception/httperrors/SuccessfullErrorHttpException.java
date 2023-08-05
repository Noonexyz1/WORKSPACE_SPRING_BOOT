package com.diego.exception.httperrors;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SuccessfullErrorHttpException extends RuntimeException {
}
