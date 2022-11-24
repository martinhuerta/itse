package com.apba.proas.backend.model;

public class WindForecast implements Variable {
    String variableType = Variable.Type.WIND.toString();
    float force;
    int angle;
    String orientiation;

    @Override
    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String s) {
    }

    public float getForce() {
        return force;
    }

    public void setForce(float force) {
        this.force = force;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public String getOrientiation() {
        return orientiation;
    }

    public void setOrientiation(String orientiation) {
        this.orientiation = orientiation;
    }

    @Override
    public String toString() {
        return "WindForecast { " +
                "variableType= '" + variableType + "'" +
                ", force= " + force +
                ", angle= " + angle +
                ", orientiation='" + orientiation + "'" +
                "}";
    }
}