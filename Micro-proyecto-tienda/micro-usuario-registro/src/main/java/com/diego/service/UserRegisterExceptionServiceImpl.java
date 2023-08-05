package com.diego.service;

import com.diego.dto.response.UserReguisterErrorDTO;
import com.diego.enums.ErrorGlobalMsg;
import com.diego.exception.UserRegisterNotComplitedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class UserRegisterExceptionServiceImpl implements UserRegisterExceptionService{

    //Excepciones Genericas
    //Ocurre un error en la comunicación HTTP debido a un error del cliente
    // (por ejemplo, una solicitud incorrecta o una autenticación fallida).
    @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerErrorHttpClient(HttpClientErrorException ex) {
        UserReguisterErrorDTO errorDTO = UserReguisterErrorDTO.builder()
                .code(ErrorGlobalMsg.ERROR_TYPE_40X.getCode())
                .message(ErrorGlobalMsg.ERROR_TYPE_40X.getMessage())
                .description(ex.getMessage()) //Debo tener cuidado aqui
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

     //Esto sucede cuando ocurre un error en la comunicación HTTP debido a un
     // error en el servidor (por ejemplo, un error interno del servidor).
     @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerErrorHttpServer(HttpServerErrorException ex) {
        UserReguisterErrorDTO errorDTO = UserReguisterErrorDTO.builder()
                .code(ErrorGlobalMsg.ERROR_TYPE_50X.getCode())
                .message(ErrorGlobalMsg.ERROR_TYPE_50X.getMessage())
                .description(ex.getMessage()) //Debo tener cuidado aqui
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    //Excepciones Funcionales
    //Error Cliente
    @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerErrorSomeFieldNotValid(MethodArgumentNotValidException ex) {
        UserReguisterErrorDTO errorDTO = UserReguisterErrorDTO.builder()
                .code(ErrorGlobalMsg.SOME_FIELD_NOT_VALID.getCode())
                .message(ErrorGlobalMsg.SOME_FIELD_NOT_VALID.getMessage())
                .description(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }


    //Errores Servidores
    @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerErrorBadJsonFormed(HttpMessageNotReadableException ex) {
        UserReguisterErrorDTO errorDTO = UserReguisterErrorDTO.builder()
                .code(ErrorGlobalMsg.BAD_JSON_FORMED.getCode())
                .message(ErrorGlobalMsg.BAD_JSON_FORMED.getMessage())
                .description(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerErrorUserNotRegisted(UserRegisterNotComplitedException ex) {
        UserReguisterErrorDTO errorDTO = UserReguisterErrorDTO.builder()
                .code(ErrorGlobalMsg.USER_REGISTER_FAILED.getCode())
                .message(ErrorGlobalMsg.USER_REGISTER_FAILED.getMessage())
                .description(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //Este metodo esta pendiente
    @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerErrorSomethingNotDefined(Exception ex) {
        UserReguisterErrorDTO errorDTO = UserReguisterErrorDTO.builder()
                .code(ErrorGlobalMsg.SOMETHING_NOT_DEFINED_BY_SERVER.getCode())
                .message(ErrorGlobalMsg.SOMETHING_NOT_DEFINED_BY_SERVER.getMessage())
                .description(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
