package com.diego.service;

import com.diego.dto.response.UserReguisterErrorDTO;
import com.diego.exception.BadJsonFormedException;
import com.diego.exception.SomethingNotDefinedException;
import com.diego.exception.SomeFieldNotValidException;
import com.diego.exception.UserRegisterNotComplitedException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

public interface UserRegisterExceptionService {

    ResponseEntity<UserReguisterErrorDTO> handlerErrorHttpClient(HttpClientErrorException ex);
    ResponseEntity<UserReguisterErrorDTO> handlerErrorHttpServer(HttpServerErrorException ex);




    //Primeramente implementare las exceptiones funcionales
    ResponseEntity<UserReguisterErrorDTO> handlerErrorBadJsonFormed(HttpMessageNotReadableException ex);
    ResponseEntity<UserReguisterErrorDTO> handlerErrorSomethingNotDefined(Exception exception);
    ResponseEntity<UserReguisterErrorDTO> handlerErrorSomeFieldNotValid(MethodArgumentNotValidException ex);
    ResponseEntity<UserReguisterErrorDTO> handlerErrorUserNotRegisted(UserRegisterNotComplitedException ex);

}
