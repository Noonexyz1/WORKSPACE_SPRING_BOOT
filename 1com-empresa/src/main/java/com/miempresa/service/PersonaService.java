package com.miempresa.service;

import com.miempresa.model.Persona;
import com.miempresa.repository.PersonaRepository;

import com.miempresa.service.dtos.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    //Servicio se puede comunicar con UNO O MAS REPOSITORIOS
    @Autowired
    private PersonaRepository personaRepository;

    public Persona createPerson(Persona person){
        return  personaRepository.save(person);
    }
    public void deletePerson(Long id){
        personaRepository.deleteById(id);
    }
    public List<Persona> listPersons(){
        return personaRepository.findAll();
    }
    public Persona findPersonById(Long id){
        return personaRepository.findById(id).orElse(null);
    }

    public Persona modifyPerson(Persona persona){
        return null; //no yet implementaded
    }

    public PersonaDTO loginPerson(String user, String pass){
        Persona personData = personaRepository.findByPasswordAndUserNameNativeQuery(user, pass);
        PersonaDTO personaDTO =  PersonaDTO.builder()
                .id(personData.getId())
                .ubicacion(personData.getUbicacion())
                .position(personData.getUbicacion())
                .fullName(personData.getFullName())
                .image(personData.getImage())
                .build();
        return personaDTO;

        //return personaRepository.findByPasswordAndUserName(user, pass);
        //return personaRepository.findByPasswordAndUserNameJPQL(user, pass);
    }

}
