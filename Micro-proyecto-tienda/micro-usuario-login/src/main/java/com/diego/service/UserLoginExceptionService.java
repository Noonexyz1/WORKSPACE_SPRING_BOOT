package com.diego.service;

import com.diego.dto.response.UserLoginRegisterDTOError;
import com.diego.enums.ErrorGlobalMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserLoginExceptionService {


    public ResponseEntity<UserLoginRegisterDTOError> handlerErrorSomeFieldNotValid() {
        UserLoginRegisterDTOError userLoginRegisterDTOError = UserLoginRegisterDTOError.builder()
                .code(ErrorGlobalMsg.SOME_FIELD_NOT_VALID.getCode())
                .message(ErrorGlobalMsg.SOME_FIELD_NOT_VALID.getMessage())
                .build();
        return new ResponseEntity<>(userLoginRegisterDTOError, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<UserLoginRegisterDTOError> handlerErrorBadJsonFormed() {
        UserLoginRegisterDTOError userLoginRegisterDTOError = UserLoginRegisterDTOError.builder()
                .code(ErrorGlobalMsg.BAD_JSON_FORMED.getCode())
                .message(ErrorGlobalMsg.BAD_JSON_FORMED.getMessage())
                .build();
        return new ResponseEntity<>(userLoginRegisterDTOError, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<UserLoginRegisterDTOError> handlerErrorUserNotLoged() {
        UserLoginRegisterDTOError userLoginRegisterDTOError = UserLoginRegisterDTOError.builder()
                .code(ErrorGlobalMsg.USER_NOT_FOUND.getCode())
                .message(ErrorGlobalMsg.USER_NOT_FOUND.getMessage())
                .build();
        return new ResponseEntity<>(userLoginRegisterDTOError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<UserLoginRegisterDTOError> handlerErrorSomethingNotDefined() {
        UserLoginRegisterDTOError userLoginRegisterDTOError = UserLoginRegisterDTOError.builder()
                .code(ErrorGlobalMsg.ERROR_TYPE_50X.getCode())
                .message(ErrorGlobalMsg.ERROR_TYPE_50X.getMessage())
                .build();
        return new ResponseEntity<>(userLoginRegisterDTOError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
