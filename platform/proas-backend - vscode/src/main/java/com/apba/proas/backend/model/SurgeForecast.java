package com.apba.proas.backend.model;

public class SurgeForecast implements Variable {
    String variableType = VariableType.SURGE.toString();
    float heigh;
    float peakPeriod;
    float averagePeriod;
    int meanDirectionPropagation;

    @Override
    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String s) {
    }

    public float getHeigh() {
        return heigh;
    }

    public void setHeigh(float heigh) {
        this.heigh = heigh;
    }

    public float getPeakPeriod() {
        return peakPeriod;
    }

    public void setPeakPeriod(float peakPeriod) {
        this.peakPeriod = peakPeriod;
    }

    public float getAveragePeriod() {
        return averagePeriod;
    }

    public void setAveragePeriod(float averagePeriod) {
        this.averagePeriod = averagePeriod;
    }

    public int getMeanDirectionPropagation() {
        return meanDirectionPropagation;
    }

    public void setMeanDirectionPropagation(int meanDirectionPropagation) {
        this.meanDirectionPropagation = meanDirectionPropagation;
    }

    @Override
    public String toString() {
        return getClass().getName() + " { " +
                "variableType = '" + variableType + "'" +
                ", heigh = " + heigh +
                ", peakPeriod = " + peakPeriod +
                ", averagePeriod = " + averagePeriod +
                ", meanDirectionPropagation = " + meanDirectionPropagation +
                " }";
    }
}