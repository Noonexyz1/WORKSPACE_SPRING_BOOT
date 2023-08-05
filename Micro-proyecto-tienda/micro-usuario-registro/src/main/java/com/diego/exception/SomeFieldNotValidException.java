package com.diego.exception;

import com.diego.enums.ErrorGlobalMsg;
import com.diego.exception.httperrors.ClientErrorHttpException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SomeFieldNotValidException extends ClientErrorHttpException {

}
