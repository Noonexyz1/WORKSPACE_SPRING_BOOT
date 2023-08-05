package com.miempresa.service.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
