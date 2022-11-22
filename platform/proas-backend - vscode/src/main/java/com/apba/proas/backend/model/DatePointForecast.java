package com.apba.proas.backend.model;

import java.sql.Timestamp;

public class DatePointForecast {
    GpsPoint gpsPoint;
    Timestamp timestamp;
    Variable variable;

    public DatePointForecast() {
        super();
    }

    public DatePointForecast(DateForecast df) {
        timestamp = df.getTimestamp();
        df.addDatePointForecast(this);
    }

    public GpsPoint getGpsPoint() {
        return gpsPoint;
    }

    public void setGpsPoint(GpsPoint gpsPoint) {
        this.gpsPoint = gpsPoint;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    @Override
    public String toString() {
        return getClass().getName() + " { " +
                "gpsPoint = " + gpsPoint.toString() +
                ", timestamp = '" + timestamp.toString() + "'" +
                ", variable = " + variable.toString() +
                " }";
    }

}
