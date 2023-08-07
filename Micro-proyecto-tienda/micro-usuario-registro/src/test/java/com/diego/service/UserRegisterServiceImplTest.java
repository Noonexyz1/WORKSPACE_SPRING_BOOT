package com.diego.service;

import com.diego.dto.request.UserReguisterDTO;
import com.diego.dto.response.UserReguisteredDTO;
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

    @BeforeEach
    public void setUp(){
        //Preparando el laboratorio
        MockitoAnnotations.openMocks(this);
        userRegisterService = new UserRegisterServiceImpl(userRegisterRepository);
    }

    @Test
    public void testRegisterNewUser(){
        UserReguisterDTO userReguisterDTO = UserReguisterDTO.builder()
                .nombre("John")
                .correo("john@john.com")
                .contrasena("123456")
                .build();
        Usuario usuario = Usuario.builder()
                .nombre(userReguisterDTO.nombre())
                .correo(userReguisterDTO.correo())
                .contrasena(userReguisterDTO.contrasena())
                .build();

        //Mocking Comportamiento
        Mockito.when(userRegisterRepository.save(ArgumentMatchers.any(Usuario.class)))
                .thenReturn(usuario);

        UserReguisteredDTO userReguisteredDTO = userRegisterService.registerNewUser(userReguisterDTO);

        Assertions.assertEquals("John", userReguisteredDTO.nombre());
        Assertions.assertEquals("john@john.com", userReguisteredDTO.correo());

    }

}
