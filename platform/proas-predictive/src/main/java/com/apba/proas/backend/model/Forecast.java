package com.apba.proas.backend.model;

import java.sql.Timestamp;

public class Forecast {
    GpsPoint gpsPoint;
    Timestamp timestamp;

    WindForecast windForecast;
    SurgeForecast surgeForecast;
    CurrentForecast currentForecast;
    SeaLevelForecast seaLevelForecast;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName())
                .append(" { variableType = ");
        sb.append(" }");
        return sb.toString();
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

    public WindForecast getWindForecast() {
        return windForecast;
    }

    public void setWindForecast(WindForecast windForecast) {
        this.windForecast = windForecast;
    }

    public SurgeForecast getSurgeForecast() {
        return surgeForecast;
    }

    public void setSurgeForecast(SurgeForecast surgeForecast) {
        this.surgeForecast = surgeForecast;
    }

    public CurrentForecast getCurrentForecast() {
        return currentForecast;
    }

    public void setCurrentForecast(CurrentForecast currentForecast) {
        this.currentForecast = currentForecast;
    }

    public SeaLevelForecast getSeaLevelForecast() {
        return seaLevelForecast;
    }

    public void setSeaLevelForecast(SeaLevelForecast seaLevelForecast) {
        this.seaLevelForecast = seaLevelForecast;
    }
}
