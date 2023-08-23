package com.miempresa.service;

import org.springframework.stereotype.Service;

@Service
public class ProcessTax {

    private static final double taxPercentage = 0.3;

    public double calculate(int inCome, int month){
        return inCome * month * taxPercentage;
    }
}
