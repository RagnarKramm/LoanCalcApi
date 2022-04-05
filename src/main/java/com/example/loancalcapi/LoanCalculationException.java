package com.example.loancalcapi;

public class LoanCalculationException extends RuntimeException {

    public LoanCalculationException(String faultyComponent) {
        super("Could not calculate loan because: " + faultyComponent);
    }
}
