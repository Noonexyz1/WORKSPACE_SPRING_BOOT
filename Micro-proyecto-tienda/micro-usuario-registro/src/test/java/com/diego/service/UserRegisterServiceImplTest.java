package com.diego.service;

import com.diego.dto.request.UserReguisterDTO;
import com.diego.dto.response.UserReguisteredDTO;
import com.diego.enums.UserTypeRoles;
import com.diego.model.Usuario;
import com.diego.repository.UserRegisterRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest         //Esto es una PRUEBA UNITARIA
public class UserRegisterServiceImplTest {

    @Autowired
    private UserRegisterService userRegisterService;

    @Mock           //crear una versión simulada o falsa de una dependencia para que puedas
                    // controlar su comportamiento durante las pruebas
                    //estás "mockeando" esta dependencia
    private UserRegisterRepository userRegisterRepository;


    private UserReguisterDTO userReguisterDTO;      //Datos de entrada
    private UserReguisteredDTO userReguisteredDTO;  //Datos de salida esperada

    @BeforeEach
    public void setUp(){
        //Preparando el laboratorio
        //MockitoAnnotations.openMocks(this);
        userReguisterDTO = UserReguisterDTO.builder()
                .nombre("John")
                .correo("john@john.com")
                .contrasena("123456")
                .role(UserTypeRoles.USER)
                .build();

        userReguisteredDTO = UserReguisteredDTO.builder()
                .nombre("John")
                .correo("john@john.com")
                .build();

        Usuario usuario = Usuario.builder()
                .nombre(userReguisterDTO.nombre())
                .correo(userReguisterDTO.correo())
                .contrasena(userReguisterDTO.contrasena())
                .rol(userReguisterDTO.role())
                .build();

        /*No hace falta injectar, porque ya lo tenemos anotado, el hara todo por nosotros*/
        //userRegisterService = new UserRegisterServiceImpl(userRegisterRepository);

        //Mocking Comportamiento
        Mockito.when(userRegisterRepository.save(ArgumentMatchers.any(Usuario.class)))
                .thenReturn(usuario);
    }

    @Test
    public void testRegisterNewUser(){
        UserReguisteredDTO userReguisteredDTOResultado = userRegisterService.registerNewUser(userReguisterDTO);

        //Assertions.assertEquals("John", userReguisteredDTO.nombre());
        //Assertions.assertEquals("john@john.com", userReguisteredDTO.correo());
        Assertions.assertEquals(userReguisteredDTO, userReguisteredDTOResultado);

    }

}
