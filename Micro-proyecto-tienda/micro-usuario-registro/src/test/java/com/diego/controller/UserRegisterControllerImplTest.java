package com.diego.controller;

import com.diego.dto.request.UserReguisterDTO;
import com.diego.dto.response.UserReguisteredDTO;
import com.diego.enums.UserTypeRoles;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//PRUEBAS DE INTEGRACION
@SpringBootTest         //Para cargar el contexto de spring para hacer pruebas
@AutoConfigureMockMvc   //para injectar la clase McokMvc SOLO PARA CONTROLADORES
public class UserRegisterControllerImplTest {

    @Autowired          //para poder crear y enviar peticiones a nuestro servidor para testear
                        //SOLO PARA CONTROLADORES
    private MockMvc mockMvc;


    private UserReguisterDTO userReguisterDTO;
    private UserReguisteredDTO userReguisteredDTO;

    @BeforeEach
    void setUp(){
        userReguisterDTO = UserReguisterDTO.builder()
                .nombre("Jose")
                .correo("jose@jose.com")
                .contrasena("123456")
                .role(UserTypeRoles.USER)
                .build();

        userReguisteredDTO = UserReguisteredDTO.builder()
                .nombre("Jose")
                .correo("jose@jose.com")
                .build();
    }

    @Test
    public void cuando_LlamoACrearUsuario_entonces_elEstadoES200() throws Exception {
        String jsonReques = new ObjectMapper().writeValueAsString(userReguisterDTO);
        String jsonResponse = new ObjectMapper().writeValueAsString(userReguisteredDTO);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/usuario/crearUsuario")
                        .content(jsonReques)
                        .contentType(MediaType.APPLICATION_JSON)
                  //    .content()
                //      .cookie()
              //        .requestAttr()
                )
                .andExpect(
                        MockMvcResultMatchers
                                .status()
                                .isOk()
                )
                .andExpect(
                        MockMvcResultMatchers
                                .content()
                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.content()
                                .json(jsonResponse)
                );

    }

}
