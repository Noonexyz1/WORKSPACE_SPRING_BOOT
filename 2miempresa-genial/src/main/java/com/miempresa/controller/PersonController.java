package com.miempresa.controller;

import com.miempresa.service.PersonService;
import com.miempresa.service.dto.PersonDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/person")
//@RequiredArgsConstructor //para las injecciones necesarias YA NO FUNCIONA
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public PersonDTO createPerson(@RequestBody PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    //El metodo dice VOID asi que no es necesario que se haga una respuesta
    //de tipo DTO

}

