package com.apba.proas.backend.model;

public interface Variable {
    String getVariableType();

    public static enum Type {
        WIND("wind"), SURGE("surge"), CURRENT("current"), SEALEVEL("seaLevel"), MULTI_VARIABLE("multiVariable");

        final String text;

        Type(String t) {
            text = t;
        }

        @Override
        public String toString() {
            return text;
        }
    }

}
