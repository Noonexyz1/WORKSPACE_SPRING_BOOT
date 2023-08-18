package com.diego.controller;

import com.diego.dto.request.UserLoginDTO;
import com.diego.dto.response.UserLoginRegistedDTO;
import com.diego.dto.response.UserLoginRegisterDTOError;
import com.diego.enums.ErrorGlobalMsg;
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
class UserLoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserLoginServiceImpl userLoginServiceImpl;

    private UserLoginRegistedDTO userLoginRegistedDTO;
    private UserLoginRegisterDTOError userLoginRegisterDTOError;


    //TODO: revisar si esta prueba debe ir a la categoria de Excepciones o
    //TODO: si es mejor dejarlo aqui, quiza deba mockear ExceptionControler
    private UserLoginRegisterDTOError userLoginRegisterDTOErrorUserNotFound;

    @BeforeEach
    void setUp(){
        userLoginRegistedDTO = UserLoginRegistedDTO.builder()
                .userName("Ana")
                .rol("USER")
                .build();

        userLoginRegisterDTOError = UserLoginRegisterDTOError.builder()
                .code(ErrorGlobalMsg.SOME_FIELD_NOT_VALID.getCode())
                .message(ErrorGlobalMsg.SOME_FIELD_NOT_VALID.getMessage())
                .build();

        userLoginRegisterDTOErrorUserNotFound = UserLoginRegisterDTOError.builder()
                .code(ErrorGlobalMsg.USER_NOT_FOUND.getCode())
                .message(ErrorGlobalMsg.USER_NOT_FOUND.getMessage())
                .build();
    }

    //Los Test deben ser para Las situaciones que deben salir  bien
    //y las situaciones que salen mal
    @Test
    void cuandoElUsuarioEnviaDatosCorrectosDeSession() throws Exception {
        UserLoginDTO requestJson = UserLoginDTO.builder()
                .correo("ana@ana.com")
                .contrasena("123456")
                .build();

        /*
        * Por defecto, Spring Boot usa Jackson para serializar y deserializar objetos
        * de solicitud y respuesta en APIs REST
        * */
        String requestJsonString = new ObjectMapper().writeValueAsString(requestJson);
        String userLoginRegistedDTOString = new ObjectMapper().writeValueAsString(userLoginRegistedDTO);

        Mockito.when(userLoginServiceImpl.logUser(requestJson))
                .thenReturn(userLoginRegistedDTO);

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



    //Aqui los demas test de modo expecion al parecer
    @Test
    void cuandoLosCamposNoSonValidosDelLadoCliente() throws Exception {
        //Given
        UserLoginDTO requestJson = UserLoginDTO.builder()
                .correo("")
                .contrasena("")
                .build();

        String jsonBody = new ObjectMapper().writeValueAsString(requestJson);
        String jsonResponse = new ObjectMapper().writeValueAsString(userLoginRegisterDTOError);

        //When
        mockMvc.perform(
                    MockMvcRequestBuilders.post("/login/logearse")
                            .content(jsonBody)
                            .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isBadRequest()
                )
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.content().json(jsonResponse)
                );
    }

    @Test
    void cuandoElClienteEnviaUnJsonNoValido() throws Exception {
        String jsonBody = "{"
                + "\"correo\": ,"
                + "\"contrasena\": \"\""
                + "}";

        String jsonBodyResponse = "{"
                + "\"code\": \"T-901\","
                + "\"message\": \"Json mal formado\""
                + "}";

        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login/logearse")
                                .content(jsonBody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status().isBadRequest()
                )
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.content().json(jsonBodyResponse)
                );
    }

    @Test
    void cuandoElUsuarioEnviaDatosErroneosAlServidorYNoEncuentraElUsuario() throws Exception {
        UserLoginDTO requestJson = UserLoginDTO.builder()
                .correo("ana@ana.com>>>>>")
                .contrasena("123456qqqqqqqq")
                .build();

        String jsonRequest = new ObjectMapper().writeValueAsString(requestJson);
        String jsonExpected = new ObjectMapper().writeValueAsString(userLoginRegisterDTOErrorUserNotFound);

        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login/logearse")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.status().is5xxServerError()
                )
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        MockMvcResultMatchers.content().json(jsonExpected)
                );
    }


    //@Test
    //void cuandoExisteUnErrorNoDefinido() throws Exception {}


}
