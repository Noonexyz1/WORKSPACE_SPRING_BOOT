package com.diego.exception;

import com.diego.exception.httperrors.ServerErrorHttpException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginNotFoundException extends ServerErrorHttpException {

    public UserLoginNotFoundException(String message){
        super(message);
    }

}
