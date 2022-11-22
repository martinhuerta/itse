package com.apba.proas.backend.model;

import java.util.ArrayList;

public class PointPeriodForecast {
    GpsPoint gpsPoint;
    ArrayList<Forecast> pointPeriodForecast = new ArrayList<Forecast>();

    public GpsPoint getGpsPoint() {
        return gpsPoint;
    }

    public void setGpsPoint(GpsPoint gpsPoint) {
        this.gpsPoint = gpsPoint;
    }

    public ArrayList<Forecast> getPointPeriodForecast() {
        return pointPeriodForecast;
    }

    public void setPointPeriodForecast(ArrayList<Forecast> pointPeriodForecast) {
        this.pointPeriodForecast = pointPeriodForecast;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName() + JSonStr.getJSonStr().obj2json(this, true));
        return sb.toString();
    }

}
