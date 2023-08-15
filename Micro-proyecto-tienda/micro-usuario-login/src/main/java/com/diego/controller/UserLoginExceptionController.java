package com.diego.controller;

import com.diego.dto.response.UserLoginRegisterDTOError;
import com.diego.exception.UserLoginNotFoundException;
import com.diego.exception.httperrors.ServerErrorHttpException;
import com.diego.service.UserLoginExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserLoginExceptionController {

    @Autowired
    private UserLoginExceptionService userLoginExceptionService;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserLoginRegisterDTOError> handlerHttpErrorFieldNotValid() {
        return userLoginExceptionService.handlerErrorSomeFieldNotValid();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<UserLoginRegisterDTOError> handlerHttpErrorBadJson() {
        return userLoginExceptionService.handlerErrorBadJsonFormed();
    }

    @ExceptionHandler(UserLoginNotFoundException.class) //Esta Exception lo lanzare yo a grede
    public ResponseEntity<UserLoginRegisterDTOError> handlerHttpErrorUserNotLoged() {
        return userLoginExceptionService.handlerErrorUserNotLoged();
    }

    //Para menejar exceptiones no controladas
    @ExceptionHandler( {Exception.class,
            ServerErrorHttpException.class}) //estás diciendo que el método manejará todas las
    // excepciones que sean subclases de Exception, ya que Exception es la superclase de
    // todas las excepciones en Java.                                       //Este argumento es como polimorfismo.
    public ResponseEntity<UserLoginRegisterDTOError> handlerHttpErrorSomethingNotDefined() {
        return userLoginExceptionService.handlerErrorSomethingNotDefined();
    }


}
