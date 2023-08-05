package com.diego.exception;

import com.diego.exception.httperrors.ServerErrorHttpException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRegisterNotComplitedException extends ServerErrorHttpException {

}
