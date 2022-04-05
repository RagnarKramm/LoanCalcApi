package com.example.loancalcapi;

public class User {
    private final String identityCode;
    private final int creditModifier;

    public User(String identityCode, int creditModifier) {
        this.identityCode = identityCode;
        this.creditModifier = creditModifier;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public int getCreditModifier() {
        return creditModifier;
    }
}
