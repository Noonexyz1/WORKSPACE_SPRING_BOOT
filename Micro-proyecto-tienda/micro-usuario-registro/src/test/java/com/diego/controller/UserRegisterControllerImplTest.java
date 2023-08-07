package com.diego.controller;

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
@AutoConfigureMockMvc   //para injectar la clase McokMvc
public class UserRegisterControllerImplTest {

    @Autowired         //para poder crear y enviar peticiones a nuestro servidor para testear
    private MockMvc mockMvc;

    @Test
    public void cuando_LlamoACrearUsuario_entonces_elEstadoES200() throws Exception {
        String jsonBody = "{"
                + "\"nombre\": \"Pablo\","
                + "\"correo\": \"pablo@pablo.com\","
                + "\"contrasena\": \"123456\""
                + "}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/usuario/crearUsuario")
                        .content(jsonBody)
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
                );
    }

}
