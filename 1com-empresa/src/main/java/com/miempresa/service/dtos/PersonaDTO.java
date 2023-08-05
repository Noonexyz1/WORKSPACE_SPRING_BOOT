package com.miempresa.service.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonaDTO {
    private Long id;
    private String ubicacion;
    private String position;
    private String fullName;
    private String image;
}
