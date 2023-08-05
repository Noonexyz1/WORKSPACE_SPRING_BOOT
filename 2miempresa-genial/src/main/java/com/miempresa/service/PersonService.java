package com.miempresa.service;

import com.miempresa.model.Person;
import com.miempresa.repository.PersonRepository;
import com.miempresa.service.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor //para inyectar lo que se necesite, NO FUNCIONA
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonDTO createPerson(PersonDTO personDTO){
        Person person = Person.builder()
                .id(personDTO.getId())
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .email(personDTO.getEmail())
                .build();

        person = personRepository.save(person);
        PersonDTO personDTOResponse = PersonDTO.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .build();
        return personDTOResponse;
    }


}
