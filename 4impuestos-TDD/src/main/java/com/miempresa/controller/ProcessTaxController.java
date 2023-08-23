package com.miempresa.controller;

import com.miempresa.service.ProcessTax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessTaxController {
    @Autowired
    private ProcessTax processTax;

    public ResponseEntity<Double> calculateTax(int inCome, int months){
        return ResponseEntity.ok().body(processTax.calculate(inCome, months));
    }

}
