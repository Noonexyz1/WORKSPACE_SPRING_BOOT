package com.diego.controller;

import com.diego.dto.request.UserReguisterDTO;
import com.diego.dto.response.UserReguisteredDTO;
import org.springframework.http.ResponseEntity;

public interface UserRegisterController {
    ResponseEntity<UserReguisteredDTO> registerNewUser(UserReguisterDTO userReguisterDTO);
}
