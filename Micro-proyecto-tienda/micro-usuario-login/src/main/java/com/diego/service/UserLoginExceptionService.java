package com.diego.service;

import com.diego.dto.response.UserLoginRegisterDTOError;
import org.springframework.stereotype.Service;

@Service
public class UserLoginExceptionService {

    public UserLoginRegisterDTOError errorException(Exception ex){
        return UserLoginRegisterDTOError.builder()
                .code("Codigo: ······")
                .message(ex.getMessage())
                .build();
    }

}
