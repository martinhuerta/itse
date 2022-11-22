package com.apba.proas.backend.model;

public class SeaLevelForecast implements Variable {
    float seaLevel;

    String variableType = VariableType.SEALEVEL.toString();

    @Override
    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String s) {
    }

    public float getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(float seaLevel) {
        this.seaLevel = seaLevel;
    }
}