package com.miempresa.controller;

import com.miempresa.service.ProcessTax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc   //Esto es una prueba de integracion directamente
class TaxControllerTestINTEGRACION {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProcessTax processTax;


    @BeforeEach
    void setUp(){
        Mockito.when(processTax.calculate(10, 5)).thenReturn(15.0);
    }


    @Test
    void probarMetodoControler() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/getTax")
                                .param("income", "10")
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
