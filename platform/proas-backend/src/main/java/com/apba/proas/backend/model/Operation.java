package com.apba.proas.backend.model;

public interface Operation {
    public String getOperationType();

    public static enum Type {
        SECURITY("security"), TIME("time");

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
