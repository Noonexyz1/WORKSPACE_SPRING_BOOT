package com.diego.service;

import com.diego.dto.request.UserLoginDTO;
import com.diego.dto.response.UserLoginRegistedDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserLoginServiceTest {

    private UserLoginServiceImpl userLoginService;

    @BeforeEach
    public void startUp(){
        userLoginService = new UserLoginServiceImpl();
    }

    @Test
    void cuandoNoSePudoEncontraElUsuarioParaInciarSesion(){
        //Ghiven datos de incializacion para probar
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .correo("ana@ana.comZZZZZZZZ")     //Debo poner algun dato erroneo
                .contrasena("123456ZZZZZZZZ") //Aqui tambien
                .build();
        //valor del resultado esperado
        UserLoginRegistedDTO userLoginRegistedNotFoundDTO = UserLoginRegistedDTO.builder()
                .userName("Usuario no encontrado")
                .rol("Rol no encontrado")
                .build();

        //When
        UserLoginRegistedDTO userLoginRegistedDTO = userLoginService.logUser(userLoginDTO);

        //Then
        Assertions.assertThat(userLoginRegistedDTO).isEqualTo(userLoginRegistedNotFoundDTO);

    }

    @Test
    void cuandoSeLanzaUnaExcepcionDelLadoServidor(){



    }

}
