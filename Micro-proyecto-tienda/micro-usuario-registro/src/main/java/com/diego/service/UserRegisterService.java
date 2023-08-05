package com.diego.service;

import com.diego.dto.request.UserReguisterDTO;
import com.diego.dto.response.UserReguisteredDTO;

public interface UserRegisterService {
    UserReguisteredDTO registerNewUser(UserReguisterDTO userReguisterDTO);
}
