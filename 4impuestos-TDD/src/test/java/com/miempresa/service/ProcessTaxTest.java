package com.miempresa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class ProcessTaxTest {
    @InjectMocks
    ProcessTax processTax;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTaxCalculation(){
        int inCome = 10;
        int months = 5;

        double totalTax = processTax.calculate(inCome, months);

        Assertions.assertEquals(15.0, totalTax);
    }
}
