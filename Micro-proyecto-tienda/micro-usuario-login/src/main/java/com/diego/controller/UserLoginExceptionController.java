package com.diego.controller;

import com.diego.dto.response.UserLoginRegisterDTOError;
import com.diego.service.UserLoginExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserLoginExceptionController {

    @Autowired
    private UserLoginExceptionService userLoginServiceException;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserLoginRegisterDTOError> mentodo(Exception ex){
        return new ResponseEntity<>(userLoginServiceException.errorException(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
