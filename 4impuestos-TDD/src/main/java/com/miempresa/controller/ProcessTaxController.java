package com.miempresa.controller;

import com.miempresa.service.ProcessTax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessTaxController {
    @Autowired
    private ProcessTax processTax;

    @GetMapping("/getTax")
    public ResponseEntity<Double> calculateTax(@RequestParam int inCome, @RequestParam int months){
        return ResponseEntity.ok().body(processTax.calculate(inCome, months));
    }

}
