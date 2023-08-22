package com.diego.controller;

import com.diego.dto.request.UserLoginDTO;
import com.diego.dto.response.UserLoginRegisterDTOError;
import com.diego.enums.ErrorGlobalMsg;
import com.diego.service.UserLoginExceptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest         //Para cargar el contexto de spring para hacer pruebas
@AutoConfigureMockMvc   //para injectar la clase McokMvc SOLO PARA CONTROLADORES
class UserLoginExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserLoginExceptionService userLoginExceptionService;

    private UserLoginDTO userLoginDTORequest1;
    private String userLoginDTORequest2;
    private UserLoginDTO userLoginDTORequest3;
    private UserLoginDTO userLoginDTORequest4;
    private UserLoginRegisterDTOError userLoginRegisterDTOError1;
    private UserLoginRegisterDTOError userLoginRegisterDTOError2;
    private UserLoginRegisterDTOError userLoginRegisterDTOError3;
    private UserLoginRegisterDTOError userLoginRegisterDTOError4;

    @BeforeEach
    void setUp() {
        userLoginDTORequest1 = UserLoginDTO.builder()
                .correo("ana@ana.com>>>>>")
                .contrasena("123456qqqqqqqq")
                .build();
        userLoginDTORequest2 = "{"
                + "\"correo\": ,"
                + "\"contrasena\": \"\""
                + "}";
        userLoginDTORequest3 = UserLoginDTO.builder()
                .correo("")
                .contrasena("")
                .build();
        userLoginDTORequest4 = UserLoginDTO.builder()
                .correo("ana@ana.com")
                .contrasena("123456")
                .build();

        userLoginRegisterDTOError1 = UserLoginRegisterDTOError.builder()
                .code(ErrorGlobalMsg.USER_NOT_FOUND.getCode())
                .message(ErrorGlobalMsg.USER_NOT_FOUND.getMessage())
                .build();
        userLoginRegisterDTOError2 = UserLoginRegisterDTOError.builder()
                .code(ErrorGlobalMsg.BAD_JSON_FORMED.getCode())
                .message(ErrorGlobalMsg.BAD_JSON_FORMED.getMessage())
                .build();
        userLoginRegisterDTOError3 = UserLoginRegisterDTOError.builder()
                .code(ErrorGlobalMsg.SOME_FIELD_NOT_VALID.getCode())
                .message(ErrorGlobalMsg.SOME_FIELD_NOT_VALID.getMessage())
                .build();
        userLoginRegisterDTOError4 = UserLoginRegisterDTOError.builder()
                .code(ErrorGlobalMsg.SOMETHING_NOT_DEFINED_BY_SERVER.getCode())
                .message(ErrorGlobalMsg.SOMETHING_NOT_DEFINED_BY_SERVER.getMessage())
                .build();

        Mockito.when(userLoginExceptionService.handlerErrorUserNotLoged())
                .thenReturn(new ResponseEntity<>(userLoginRegisterDTOError1, HttpStatus.INTERNAL_SERVER_ERROR));
        Mockito.when(userLoginExceptionService.handlerErrorBadJsonFormed())
                .thenReturn(new ResponseEntity<>(userLoginRegisterDTOError2, HttpStatus.BAD_REQUEST));
        Mockito.when(userLoginExceptionService.handlerErrorSomeFieldNotValid())
                .thenReturn(new ResponseEntity<>(userLoginRegisterDTOError3, HttpStatus.BAD_REQUEST));
        Mockito.when(userLoginExceptionService.handlerErrorSomethingNotDefined())
                .thenReturn(new ResponseEntity<>(userLoginRegisterDTOError4, HttpStatus.INTERNAL_SERVER_ERROR));

    }

    @Test
    void handlerHttpErrorUserNotLoged() throws Exception {
        String requestJsonString = new ObjectMapper().writeValueAsString(userLoginDTORequest1);
        String userLoginRegistedDTOString = new ObjectMapper().writeValueAsString(userLoginRegisterDTOError1);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login/logearse")
                                .content(requestJsonString)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isInternalServerError()
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

    @Test
    void handlerHttpErrorBadJson() throws Exception {
        String requestJsonString = new ObjectMapper().writeValueAsString(userLoginDTORequest2);
        String userLoginRegistedDTOString = new ObjectMapper().writeValueAsString(userLoginRegisterDTOError2);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login/logearse")
                                .content(requestJsonString)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isBadRequest()
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

    @Test
    void handlerHttpErrorFieldNotValid() throws Exception {
        String requestJsonString = new ObjectMapper().writeValueAsString(userLoginDTORequest3);
        String userLoginRegistedDTOString = new ObjectMapper().writeValueAsString(userLoginRegisterDTOError3);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login/logearse")
                                .content(requestJsonString)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isBadRequest()
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

    @Test
    void handlerHttpErrorSomethingNotDefined() throws Exception {
        String requestJsonString = new ObjectMapper().writeValueAsString(userLoginDTORequest4);
        String userLoginRegistedDTOString = new ObjectMapper().writeValueAsString(userLoginRegisterDTOError4);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login/logearse")
                                .content(requestJsonString)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status()
                                .isInternalServerError()
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