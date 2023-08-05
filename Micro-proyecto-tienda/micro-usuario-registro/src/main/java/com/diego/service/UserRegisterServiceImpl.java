package com.diego.service;

import com.diego.dto.request.UserReguisterDTO;
import com.diego.dto.response.UserReguisteredDTO;
import com.diego.model.Usuario;
import com.diego.repository.UserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private UserRegisterRepository userRegisterRepository;

    @Override
    public UserReguisteredDTO registerNewUser(UserReguisterDTO userReguisterDTO) {
        //Creamos mi usuario
        Usuario usuario = Usuario.builder()
                .nombre(userReguisterDTO.nombre())
                .correo(userReguisterDTO.correo())
                .contrasena(userReguisterDTO.contrasena())
                .fechaRegistro(null)
                .build();

        Usuario userResponse = userRegisterRepository.save(usuario);

        return UserReguisteredDTO.builder()
                .nombre(userResponse.getNombre())
                .correo(userResponse.getCorreo())
                .build();

    }

}
