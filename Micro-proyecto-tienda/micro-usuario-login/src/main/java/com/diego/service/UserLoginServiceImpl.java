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
