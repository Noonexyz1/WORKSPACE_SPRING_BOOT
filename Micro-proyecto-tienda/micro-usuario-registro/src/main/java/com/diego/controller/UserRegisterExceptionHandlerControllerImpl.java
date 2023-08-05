package com.diego.controller;

import com.diego.dto.response.UserReguisterErrorDTO;
import com.diego.exception.UserRegisterNotComplitedException;
import com.diego.exception.httperrors.ServerErrorHttpException;
import com.diego.service.UserRegisterExceptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class UserRegisterExceptionHandlerControllerImpl implements UserRegisterExceptionHandlerController{

    //Inyeccion Exitosa
    @Autowired
    private UserRegisterExceptionServiceImpl userRegisterExceptionService;


    /* cuando tu aplicación realiza llamadas HTTP a servicios externos (como otros
    microservicios o APIs de terceros) y deseas manejar errores específicos de comunicación
    HTTP en tu controlador.
    * */
    @ExceptionHandler(HttpClientErrorException.class)
    @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorLikeCliente(HttpClientErrorException ex) {
        return userRegisterExceptionService.handlerErrorHttpClient(ex);
    }

    /*pero para manejar errores específicos de comunicación HTTP que se originan en el
    servidor remoto al que estás haciendo la llamada. Si tu aplicación realiza llamadas
    HTTP a otros servicios y recibe respuestas con códigos de estado 5xx (Errores del
    servidor), puedes capturar y manejar la HttpServerErrorException
    * */
    @ExceptionHandler(HttpServerErrorException.class)
    @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorLikeServer(HttpServerErrorException ex) {
        return userRegisterExceptionService.handlerErrorHttpServer(ex);
    }



    //Metodos Exceptions Especificas

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorFieldNotValid(MethodArgumentNotValidException ex) {
        return userRegisterExceptionService.handlerErrorSomeFieldNotValid(ex);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorBadJson(HttpMessageNotReadableException ex) {
        return userRegisterExceptionService.handlerErrorBadJsonFormed(ex);
    }

    @ExceptionHandler(UserRegisterNotComplitedException.class) //Esta Exception lo lanzare yo a grede
    @Override
    public ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorUserNotRegisted(UserRegisterNotComplitedException ex) {
        return userRegisterExceptionService.handlerErrorUserNotRegisted(ex);
    }

    //Para menejar exceptiones no controladas
    @ExceptionHandler( {Exception.class,
                        ServerErrorHttpException.class}) //estás diciendo que el método manejará todas las
    // excepciones que sean subclases de Exception, ya que Exception es la superclase de
    // todas las excepciones en Java.                                       //Este argumento es como polimorfismo.
    @Override                                                                        //Todas las clases de la etiqueta caben en este parametro por ser herencia
    public ResponseEntity<UserReguisterErrorDTO> handlerHttpErrorSomethingNotDefined(Exception exception) {
        return userRegisterExceptionService.handlerErrorSomethingNotDefined(exception);
    }


    /*Falta implementar los Logs para cada metodo

    * Escalabilidad: Si en el futuro necesitas agregar más tipos de excepciones específicas
    * con lógica de manejo diferente, tener métodos específicos ya establecidos puede
    * facilitar la expansión de la funcionalidad sin cambiar la estructura general del
    * controlador de excepciones.*/

}
