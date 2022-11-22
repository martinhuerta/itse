package com.apba.proas.backend.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AoiState {
    int aoiId;
    Timestamp fromTime;
    Timestamp toTime;
    int interval;
    int samplesNumber;

    // cada fecha que habrá un conjunto de puntos con estado
    List<DateForecast> dateForecasts = new ArrayList<DateForecast>();

    public void addDateForecast(DateForecast df) {
        dateForecasts.add(df);
    }

    public int getAoiId() {
        return aoiId;
    }

    public void setAoiId(int aoiId) {
        this.aoiId = aoiId;
    }

    public Timestamp getFromTime() {
        return fromTime;
    }

    public void setFromTime(Timestamp fromTime) {
        this.fromTime = fromTime;
    }

    public Timestamp getToTime() {
        return toTime;
    }

    public void setToTime(Timestamp toTime) {
        this.toTime = toTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getSamplesNumber() {
        return samplesNumber;
    }

    public void setSamplesNumber(int samplesNumber) {
        this.samplesNumber = samplesNumber;
    }

    public List<DateForecast> getDateForecasts() {
        return dateForecasts;
    }

    public void setDateForecasts(List<DateForecast> dateForecasts) {
        this.dateForecasts = dateForecasts;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName() + " { " +
                "aoiId = " + aoiId +
                ", fromTime = '" + fromTime.toString() + "'" +
                ", toTime = '" + toTime.toString() + "'" +
                ", interval = " + interval +
                ", samplesNumber = " + samplesNumber +
                ", dateForecasts [ ");
        dateForecasts.stream().forEach(t -> sb.append(":").append(t.toString()).append(", "));
        sb.append(" ] }");
        return sb.toString();
    }

}
