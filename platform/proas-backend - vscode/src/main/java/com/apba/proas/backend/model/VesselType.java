package com.apba.proas.backend.model;

public enum VesselType {
    PETROL("PETROL"),
    GAS("GAS"),
    OTHER("OTHER");

    private final String text;

    VesselType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
