package com.diego.controller;

import com.diego.dto.request.UserLoginDTO;
import com.diego.dto.response.UserLoginRegistedDTO;
import com.diego.service.UserLoginServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@SpringBootTest         //Para cargar el contexto de spring para hacer pruebas
@AutoConfigureMockMvc   //para injectar la clase McokMvc SOLO PARA CONTROLADORES
class UserLoginControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserLoginServiceImpl userLoginService;

    private UserLoginDTO userLoginDTORequest;
    private UserLoginRegistedDTO userLoginRegistedDTOResponse;

    @BeforeEach
    void setUp() {
        userLoginDTORequest = UserLoginDTO.builder()
                .correo("ana@ana.com")
                .contrasena("123456")
                .build();

        userLoginRegistedDTOResponse = UserLoginRegistedDTO.builder()
                .userName("Ana")
                .rol("USER")
                .build();

        Mockito.when(userLoginService.logUser(userLoginDTORequest))
                .thenReturn(userLoginRegistedDTOResponse);

    }

    @Test
    void loginUser() throws Exception {
        String requestJsonString = new ObjectMapper().writeValueAsString(userLoginDTORequest);
        String userLoginRegistedDTOString = new ObjectMapper().writeValueAsString(userLoginRegistedDTOResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login/logearse")
                                .content(requestJsonString)
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
                                .json(userLoginRegistedDTOString)
                );

    }

}