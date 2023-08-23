package com.miempresa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc   //Esto es una prueba de integracion directamente
class TaxControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void probarMetodoControler() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/getTax")
                                .param("inCome", "10")
                                .param("months", "5")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                )
                .andExpect(
                        MockMvcResultMatchers.content().json(String.valueOf(15.0))
                );

    }

}
