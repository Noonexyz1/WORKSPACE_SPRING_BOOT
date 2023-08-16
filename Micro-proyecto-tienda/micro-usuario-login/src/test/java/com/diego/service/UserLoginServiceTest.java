package com.diego.service;

import com.diego.dto.request.UserLoginDTO;
import com.diego.dto.response.UserLoginRegistedDTO;
import com.diego.dto.response.UserLoginRegisterDTOError;
import com.diego.enums.UserTypeRoles;
import com.diego.exception.UserLoginNotFoundException;
import com.diego.model.Usuario;
import com.diego.repository.UserLoginRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserLoginServiceTest {

    @Autowired  //Nuestro Servicio
    private UserLoginServiceImpl userLoginService;
    @MockBean   //Nuestro Repositorio MockBean para simular
    private UserLoginRepository userLoginRepository;

    //Metodo auxiliar
    private void mockUser(String email, String password, String name, UserTypeRoles role) {
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .correo(email)
                .contrasena(password)
                .build();

        Usuario usuarioMock = Usuario.builder()
                .contrasena(password)
                .correo(email)
                .nombre(name)
                .rol(role)
                .build();

        Mockito.when(userLoginRepository.findByPassAndEmail(userLoginDTO.contrasena(), userLoginDTO.correo()))
                .thenReturn(usuarioMock);
    }


    @BeforeEach //Para simular que lo trae de la base de datos
    void setUp(){
        mockUser("ana@ana.com", "123456", "Ana", UserTypeRoles.USER);
        mockUser("diego@diego.com", "123456", "Diego", UserTypeRoles.ADMIN);

    }

    @Test
    @DisplayName("Prueba para la obtencion de un UserLoginRegistedDTO con USER")
    void cuandoSiSePudoEncontraElUsuarioParaInciarSesionUSER(){
        //Ghiven datos de incializacion para probar el servicio
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .correo("ana@ana.com")
                .contrasena("123456")
                .build();

        //valor del resultado esperado
        UserLoginRegistedDTO userLoginRegistedFoundDTO = UserLoginRegistedDTO.builder()
                .userName("Ana")
                .rol("USER")
                .build();

        //When
        UserLoginRegistedDTO userLoginRegistedDTO = userLoginService.logUser(userLoginDTO);

        //Then
        //Assertions.assertThat(userLoginRegistedDTO).isEqualTo(userLoginRegistedFoundDTO);
        assertEquals(userLoginRegistedFoundDTO, userLoginRegistedDTO);
    }


    @Test
    @DisplayName("Prueba para la obtencion de un UserLoginRegistedDTO con ADMIN")
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
        assertEquals(userLoginRegistedFoundDTO, userLoginRegistedDTO);
    }

    @Test
    @DisplayName("Prueba cuando no se encuentra ningun tipo de usuario")
    void cuandoNoSePudoEncontraElUsuarioParaInciarSesion(){
        //Ghiven datos de incializacion para probar
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .correo("ana@ana.comZZZZZZZZ")     //Debo poner algun dato erroneo
                .contrasena("123456ZZZZZZZZ") //Aqui tambien
                .build();

        assertThrows(UserLoginNotFoundException.class, () -> {
            userLoginService.logUser(userLoginDTO);
        });

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
