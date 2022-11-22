package com.apba.proas.backend.model;

public class SecurityOperation implements Operation {

    float securityLevel;

    @Override
    public String getOperationType() {
        return Operation.Type.SECURITY.toString();
    }

    public float getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(float securityLevel) {
        this.securityLevel = securityLevel;
    }

    @Override
    public String toString() {
        return getClass().getName() + " { " +
                ", securityLevel = " + securityLevel +
                " }";
    }
}