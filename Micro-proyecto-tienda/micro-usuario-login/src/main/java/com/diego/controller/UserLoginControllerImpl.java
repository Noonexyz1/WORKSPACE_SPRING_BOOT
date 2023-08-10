package com.diego.controller;

import com.diego.dto.request.UserLoginDTO;
import com.diego.dto.response.UserLoginRegistedDTO;
import com.diego.service.UserLoginServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 86400)
@RestController
@RequestMapping(path = "/login")
public class UserLoginControllerImpl {
    @Autowired
    private UserLoginServiceImpl userLoginService;

    @PostMapping(   path = "/logearse",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}    )
    public ResponseEntity<UserLoginRegistedDTO> loginUser(@RequestBody @Valid UserLoginDTO userLoginDTO){
        UserLoginRegistedDTO userLoginRegistedDTO = userLoginService.logUser(userLoginDTO);
        return new ResponseEntity<>(userLoginRegistedDTO, HttpStatus.OK);
    }

}
