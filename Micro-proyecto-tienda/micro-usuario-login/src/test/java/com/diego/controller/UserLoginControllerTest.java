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



    //Los Test deben ser para Las situaciones que deben salir  bien
    //y las situaciones que salen mal

    @Test
    void cuandoElUsuarioEnviaDatosCorrectosDeSession() throws Exception {
        //Datos inciales
        String jsonBody = "{"
                + "\"correo\": \"ana@ana.com\","
                + "\"contrasena\": \"123456\""
                + "}";


        String jsonBodyResponse = "{"
                + "\"userName\": \"Ana\","
                + "\"rol\": \"USER\""
                + "}";

        //Iniciamos las prueba con el resultado deseado
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



    //Aqui los demas test de modo expecion al parecer
    @Test
    void lanzarExceptions(){}
    @Test
    void cuandoLosCamposNoSonValidosDelLadoCliente(){}
    @Test
    void cuandoElClienteEnviaUnJsonNoValido(){}
    @Test
    void cuandoElUsuarioEnviaDatosErroneosAlServidor(){}
    @Test
    void cuandoExisteUnErrorNoDefinido(){}


}
