package com.apba.proas.backend.model;

public class SecurityOperation implements PredictionKpi {

    String variableType = OperationKpiType.SECURITY.toString();
    float securityLevel;

    @Override
    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String s) {
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
                "variableType = '" + variableType + "'" +
                ", securityLevel = " + securityLevel +
                " }";
    }
}