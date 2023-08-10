package com.diego.dto.response;

import lombok.Builder;

@Builder   //Debe ser un RECORD para serializar y deserealizar
            //Mi error fue hacerlo una clase
public record UserLoginRegistedDTO (
        String userName,
        String rol
){
}
