package com.miempresa.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private String firstName;
    private String lastName;
    private String email;

}
