package com.diego.service;

import com.diego.dto.request.UserLoginDTO;
import com.diego.dto.response.UserLoginRegistedDTO;
import com.diego.enums.UserTypeRoles;
import com.diego.model.Usuario;
import com.diego.repository.UserLoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserLoginServiceImplTest {

    @Autowired
    private UserLoginServiceImpl userLoginService;
    @MockBean   //Esto es un mockeador
    private UserLoginRepository userLoginRepository;

    private UserLoginRegistedDTO userLoginRegistedDTO;
    private UserLoginDTO userLoginDTO;

    @BeforeEach
    void setUp() {
        //metodo solo para simular los usuarios que necesito
        initRequest("ana@ana.com", "123456");
        mockUser("ana@ana.com", "123456", "Ana", UserTypeRoles.USER);
        initResponse("Ana", "USER");

        initRequest("diego@diego.com", "123456");
        mockUser("diego@diego.com", "123456", "Diego", UserTypeRoles.ADMIN);
        initResponse("Diego", "ADMIN");
    }

    @Test
    void logUser() {
        assertEquals(userLoginRegistedDTO, userLoginService.logUser(userLoginDTO));
    }

    private void initRequest(String mail, String password) {
        userLoginDTO = UserLoginDTO.builder()
                .correo(mail)
                .contrasena(password)
                .build();
    }
    private void initResponse(String nombre, String role) {
        userLoginRegistedDTO = UserLoginRegistedDTO.builder()
                .userName(nombre)
                .rol(role)
                .build();
    }
    private void mockUser(String email, String password, String name, UserTypeRoles role) {
        Usuario usuarioMock = Usuario.builder()
                .contrasena(password)
                .correo(email)
                .nombre(name)
                .rol(role)
                .build();

        Mockito.when(userLoginRepository.findByPassAndEmail(password, email))
                .thenReturn(usuarioMock);
    }

}