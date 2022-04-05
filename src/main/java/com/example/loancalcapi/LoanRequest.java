package com.example.loancalcapi;

public class LoanRequest {
    private final int requestedAmount;
    private final int requestedPeriod;
    private int availableAmount;
    private int availablePeriod;
    private String message;
    private String userIdentity;

    public LoanRequest(int requestedAmount, int requestedPeriod){
        this.requestedAmount = requestedAmount;
        this.availableAmount = requestedAmount;
        this.requestedPeriod = requestedPeriod;
        this.availablePeriod = requestedPeriod;
    }

    public int getRequestedPeriod() {
        return requestedPeriod;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public int getAvailablePeriod() {
        return availablePeriod;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public void setAvailableAmount(int availableAmount){
        this.availableAmount = availableAmount;
    }
    public void setAvailablePeriod(int availablePeriod){
        this.availablePeriod = availablePeriod;
    }
}
