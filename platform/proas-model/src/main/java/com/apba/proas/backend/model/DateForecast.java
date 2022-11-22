package com.apba.proas.backend.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class DateForecast {
    Timestamp timestamp;
    ArrayList<DatePointForecast> datePointForecasts = new ArrayList<DatePointForecast>();

    public void addDatePointForecast(DatePointForecast dp) {
        if (dp != null) {
            datePointForecasts.add(dp);
            dp.setTimestamp(this.timestamp);
        }
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<DatePointForecast> getDatePointForecasts() {
        return datePointForecasts;
    }

    public void setDatePointForecasts(ArrayList<DatePointForecast> datePointForecasts) {
        this.datePointForecasts = datePointForecasts;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName())
                .append(" { timesamp = '" + timestamp + "' [ ");
        datePointForecasts.stream().forEach(t -> sb.append(t.toString()).append(", "));

        sb.append(" ] }");
        return sb.toString();
    }

}
