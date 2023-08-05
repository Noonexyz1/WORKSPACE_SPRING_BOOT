package com.miempresa.controller;

import com.miempresa.model.Persona;
import com.miempresa.service.PersonaService;

import java.util.List;

import com.miempresa.service.dtos.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/persona")
    @ResponseBody
    public List<Persona> listAllPersons(){
        return personaService.listPersons();
    }
    @PostMapping("/persona")
    @ResponseBody
    public Persona createPerson(@RequestBody Persona person){
        return personaService.createPerson(person);
    }
    @DeleteMapping("/persona/{id}")
    public void deletePersonById(@PathVariable Long id){
        personaService.deletePerson(id);
    }
    @GetMapping("/persona/{id}")
    public Persona finPersonById(@PathVariable Long id){
        return personaService.findPersonById(id);
    }
    @PutMapping("/persona") //Hay que enviar todos los campos mas el id
    public void modifiePerson(@RequestBody Persona person){
        personaService.modifyPerson(person);
    }
    @PostMapping("/login")
    public PersonaDTO loginPerson(@RequestBody Persona person){
        return personaService.loginPerson(person.getUserName(), person.getPassword());

        /* Consulta para Post para loginPerson()
        {
            "userName": "admin",
            "password": "admin"
        } */
    }

        /*
        * Que es DTO, para crear un objeto a partir de una serie de atributos que seran
        * enviados o recuperados por el servidor en un solo momento.
        * Estos objetos atributos pueden venir de una sola fuente o trabla o de ultimas
        * tablas, COMPLETAMENTE DISTINTO QUE LAS ENTIDADES MODELS.
        * Los DTO hace que nos adaptemos al fronted y no estar enviando datos innecesarios,
        * La Logica siempre es preferible trabajar en el Servicio, revisar la firma del metodo
        * loginPerson()
        *
        * */

}
