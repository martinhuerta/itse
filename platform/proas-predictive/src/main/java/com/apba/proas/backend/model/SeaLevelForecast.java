package com.apba.proas.backend.model;

public class SeaLevelForecast implements Variable {
    float seaLevel;

    @Override
    public String getVariableType() {
        return Variable.Type.SEALEVEL.toString();
    }

    public float getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(float seaLevel) {
        this.seaLevel = seaLevel;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName() + JSonStr.getJSonStr().obj2json(this, true));
        return sb.toString();
    }

}