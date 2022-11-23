package com.apba.proas.backend.model;

import java.util.ArrayList;

public class PointPeriodForecast {
    GpsPoint gpsPoint;
    ArrayList<Forecast> forecastsList = new ArrayList<Forecast>();

    public ArrayList<Forecast> getForecastsList() {
        return forecastsList;
    }

    public void setForecastsList(ArrayList<Forecast> forecastsList) {
        this.forecastsList = forecastsList;
    }

    public void addForecast(Forecast f) {
        forecastsList.add(f);
    }

    public GpsPoint getGpsPoint() {
        return gpsPoint;
    }

    public void setGpsPoint(GpsPoint gpsPoint) {
        this.gpsPoint = gpsPoint;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName() + JSonStr.getJSonStr().obj2json(this, true));
        return sb.toString();
    }

}
