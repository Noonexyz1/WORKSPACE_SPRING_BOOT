package com.diego.service;

import com.diego.dto.request.UserLoginDTO;
import com.diego.dto.response.UserLoginRegistedDTO;
import com.diego.dto.response.UserLoginRegisterDTOError;
import com.diego.enums.UserTypeRoles;
import com.diego.model.Usuario;
import com.diego.repository.UserLoginRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserLoginServiceTest {

    @Autowired
    private UserLoginServiceImpl userLoginService;
    @MockBean
    private UserLoginRepository userLoginRepository;


    @Test
    void cuandoSiSePudoEncontraElUsuarioParaInciarSesionUSER(){
        //Ghiven datos de incializacion para probar
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .correo("ana@ana.com")
                .contrasena("123456")
                .build();
        //valor del resultado esperado
        UserLoginRegistedDTO userLoginRegistedFoundDTO = UserLoginRegistedDTO.builder()
                .userName("Ana")
                .rol("USER")
                .build();

        // Simulando el comportamiento del repositorio
        Usuario usuarioMock = Usuario.builder()
                .id(10)
                .contrasena("123456")
                .correo("ana@ana.com")
                .nombre("Ana")
                .rol(UserTypeRoles.USER)
                .build();

        Mockito.when(userLoginRepository.findByPassAndEmail(userLoginDTO.contrasena(), userLoginDTO.correo()))
                .thenReturn(usuarioMock);

        //When
        UserLoginRegistedDTO userLoginRegistedDTO = userLoginService.logUser(userLoginDTO);

        //Then
        //Assertions.assertThat(userLoginRegistedDTO).isEqualTo(userLoginRegistedFoundDTO);
        assertEquals(userLoginRegistedFoundDTO, userLoginRegistedDTO);
    }


    @Test
    void cuandoSiSePudoEncontraElUsuarioParaInciarSesionADMIN(){
        //Ghiven datos de incializacion para probar
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .correo("diego@diego.com")
                .contrasena("123456")
                .build();
        //valor del resultado esperado
        UserLoginRegistedDTO userLoginRegistedFoundDTO = UserLoginRegistedDTO.builder()
                .userName("Diego")
                .rol("ADMIN")
                .build();

        //When
        UserLoginRegistedDTO userLoginRegistedDTO = userLoginService.logUser(userLoginDTO);

        //Then
        Assertions.assertThat(userLoginRegistedDTO).isEqualTo(userLoginRegistedFoundDTO);
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


    //Aqui mis test de exceptions
    @Test
    void cuandoLosCamposNoSonValidosDelLadoController(){
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .correo("")     //Debo poner algun dato erroneo
                .contrasena("") //Aqui tambien
                .build();
        //valor del resultado esperado
        UserLoginRegisterDTOError userLoginRegisterDTOError = UserLoginRegisterDTOError.builder()
                .code("ERROR-TYPE-400")
                .message("Los campos no son validos")
                .build();

        //When
        //UserLoginRegistedDTO userLoginRegistedDTO = userLoginService.logUser(userLoginDTO);

        Assertions.assertThatThrownBy(() -> userLoginService.logUser(userLoginDTO))
                .isInstanceOf(MethodArgumentNotValidException.class)
                        .hasMessage(userLoginRegisterDTOError.toString());


    }

    @Test
    void cuandoElClienteEnviaUnJsonNoValidoController(){}
    @Test
    void cuandoElUsuarioEnviaDatosErroneosAlServidorController(){}
    @Test
    void errorNoDefinidoDelServidorController(){}


}
