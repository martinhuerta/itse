package com.apba.proas.backend.model;

public class CurrentForecast implements Variable {
    int angle;
    String orientacion;
    float velocidad;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public static String TIPO_ESTADO_CORRIENTE = "CORRIENTE";

    @Override
    public String getVariableType() {
        return TIPO_ESTADO_CORRIENTE;
    }

    @Override
    public String toString() {
        return getClass().getName() + " { " +
                "angle = " + angle +
                ", orientacion = '" + orientacion + "'" +
                ", velocidad = " + velocidad +
                " }";
    }

}