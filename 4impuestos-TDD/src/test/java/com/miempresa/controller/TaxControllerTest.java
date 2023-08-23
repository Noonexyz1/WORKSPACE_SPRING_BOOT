package com.miempresa.controller;

import com.miempresa.service.ProcessTax;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class TaxControllerTest {

    /*processTax es una simulación (mock) de la dependencia ProcessTax que es utilizada por
     el ProcessTaxController*/

    @Autowired
    private ProcessTaxController processTaxController;



    @MockBean
    private ProcessTax processTax;
    @BeforeEach
    void init(){
        Mockito.when(processTax.calculate(10, 5))
                .thenReturn(15.0);
    }




    @Test
    void calculateTax(){
        int inCome = 10;
        int months = 5;
        ResponseEntity<Double> response = processTaxController.calculateTax(inCome, months);
        Assertions.assertEquals(ResponseEntity.ok().body(15.0), response);

        /*Para no hacer comaparaciones de objetos complejos como ResponseEntity<>*/
        Assertions.assertEquals(15.0, response.getBody());
    }

}
