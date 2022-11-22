package com.apba.proas.backend.model;

public enum OperationKpiType {
    SECURITY("security"), TIME("time");

    final String text;

    OperationKpiType(String t) {
        text = t;
    }

    @Override
    public String toString() {
        return text;
    }
}
