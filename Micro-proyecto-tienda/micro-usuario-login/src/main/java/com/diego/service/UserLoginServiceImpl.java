package com.diego.service;

import com.diego.dto.request.UserLoginDTO;
import com.diego.dto.response.UserLoginRegistedDTO;
import com.diego.exception.UserLoginNotFoundException;
import com.diego.model.Usuario;
import com.diego.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl {
    @Autowired
    private UserLoginRepository userLoginRepository;

    public UserLoginRegistedDTO logUser(UserLoginDTO user){
        /*NOTA IMPORTANTE:
        *Cuando se le aplica las dependencias de Spring Security, todas las clases anotadas y usadas como Controller,
        * Service, Repository y demas, se encuentran protegidas por defecto, lo que siginifica que si no existe una configuraicon
        * previa, nadie podra hacer uso de estas clases o componentes de ningun tipo.
        * El problema que presentaba al momento de hacer el request, fue que por mas bien que estubiera, me salia error
        * por el motivo que se menciona anteriormente
        * */

                                    //Con Spring Security, este metodo no tendra acceso al repositorio, OJO
                                    //Si no, me devolvera un error
        Usuario usuarioEncontrado = userLoginRepository.findByPassAndEmail(user.contrasena(), user.correo());

        if (usuarioEncontrado == null) {
            throw new UserLoginNotFoundException("Este error es porque no se encontro el usuario");
        }

        return UserLoginRegistedDTO.builder()
                .userName(usuarioEncontrado.getNombre())
                .rol(usuarioEncontrado.getRol().toString())
                .build();
    }

}
