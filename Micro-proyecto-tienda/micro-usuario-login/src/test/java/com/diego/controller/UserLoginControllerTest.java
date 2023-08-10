package com.diego.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void cuandoElUsuarioEnviaDatosCorrectosDeSession() throws Exception {
        String jsonBody = "{"
                + "\"correo\": \"ana@ana.com\","
                + "\"contrasena\": \"123456\""
                + "}";


        String jsonBodyResponse = "{"
                + "\"userName\": \"Ana\","
                + "\"rol\": \"USER\""
                + "}";

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login/logearse")
                                .content(jsonBody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status()
                                .isOk()
                )
                .andExpect(
                        MockMvcResultMatchers.content()
                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.content()
                                .json(jsonBodyResponse)
                );

    }


}
