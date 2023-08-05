package com.diego.controller;

import com.diego.dto.response.UserReguisterErrorDTO;
import com.diego.exception.UserRegisterNotComplitedException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

public interface UserRegisterExceptionHandlerController {
    //ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorLikeSuccessfull(HttpMessageNotReadableException ex);
    //ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorLikeRedirection(HttpMessageNotReadableException ex);
    ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorLikeCliente(HttpClientErrorException ex);
    ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorLikeServer(HttpServerErrorException ex);


    //Ahora mis expeciones personalizadas segun requerimientos funcionales
    ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorFieldNotValid(MethodArgumentNotValidException ex);
    ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorBadJson(HttpMessageNotReadableException ex);
    ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorUserNotRegisted(UserRegisterNotComplitedException ex);
    ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorSomethingNotDefined(Exception exception);

}
