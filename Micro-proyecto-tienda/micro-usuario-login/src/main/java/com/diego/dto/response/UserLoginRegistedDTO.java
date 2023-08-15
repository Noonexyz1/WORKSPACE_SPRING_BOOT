package com.diego.dto.response;

import lombok.Builder;
import lombok.Getter;

//@Getter Prohibido poner Getters aui, solamente para clases, enums o campos
@Builder   //Debe ser un RECORD para serializar y deserealizar
            //Mi error fue hacerlo una clase
public record UserLoginRegistedDTO (
        String userName,
        String rol
){
}
