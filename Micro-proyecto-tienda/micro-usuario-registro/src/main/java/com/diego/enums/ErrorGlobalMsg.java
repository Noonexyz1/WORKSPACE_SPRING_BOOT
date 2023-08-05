package com.diego.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorGlobalMsg {
    /*ERROR_TYPE_20X("T-20X", "Error tipo SUCCESSFUL"),
    ERROR_TYPE_30X("T-30X", "Error tipo REDIRECTION"),*/

    ERROR_TYPE_40X("T-40X", "Error tipo CLIENT_ERROR"),
    ERROR_TYPE_50X("T-50X", "Error tipo SERVER_ERROR"),


    //Y aqui van otros errores de requerimiento funcional
    BAD_JSON_FORMED("T-901", "Json mal formado"),
    SOME_FIELD_NOT_VALID("T-902", "Los campos no son validos"),
    SOMETHING_NOT_DEFINED_BY_CLIENT("T-903", "Error desconcido del lado Cliente"),
    SOMETHING_NOT_DEFINED_BY_SERVER("T-904", "Implementacion incompleta del Servidor"),
    USER_REGISTER_FAILED("T-905", "Registro de usuario fallido");


    private final String code;
    private final String message;

}
