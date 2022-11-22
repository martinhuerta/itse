package com.apba.proas.backend.model;

public enum VariableType {
    WIND("wind"), SURGE("surge"), CURRENT("current"), SEALEVEL("seaLevel"), MULTI_VARIABLE("multiVariable");

    final String text;

    VariableType(String t) {
        text = t;
    }

    @Override
    public String toString() {
        return text;
    }
}
