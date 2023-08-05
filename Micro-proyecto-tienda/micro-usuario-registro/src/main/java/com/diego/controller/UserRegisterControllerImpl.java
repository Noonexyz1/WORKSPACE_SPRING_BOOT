package com.diego.controller;

import com.diego.dto.request.UserReguisterDTO;
import com.diego.dto.response.UserReguisteredDTO;
import com.diego.service.UserRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 86400)
@RestController
@RequestMapping(path = "/usuario")
public class UserRegisterControllerImpl implements UserRegisterController{
    @Autowired
    private UserRegisterService userRegisterService;

    @Override
    @PostMapping(   path = "/crearUsuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}    )
    public ResponseEntity<UserReguisteredDTO> registerNewUser(@RequestBody @Valid UserReguisterDTO userReguisterDTO) {
        return new ResponseEntity<>(userRegisterService.registerNewUser(userReguisterDTO), HttpStatus.OK);
    }

}
