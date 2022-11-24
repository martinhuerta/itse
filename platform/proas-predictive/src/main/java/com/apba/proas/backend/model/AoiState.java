package com.apba.proas.backend.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AoiState {
    int aoiId;

    public int getAoiId() {
        return aoiId;
    }

    public void setAoiId(int aoiId) {
        this.aoiId = aoiId;
    }

    Timestamp fromTime;
    Timestamp toTime;
    int interval;
    int samplesNumber;
    List<PointPeriodForecast> pointsPeriodForecast = new ArrayList<PointPeriodForecast>();
    List<OperationKpi> operationKpis = new ArrayList<OperationKpi>();

    public void addPointPeriodForecast(PointPeriodForecast p) {
        pointsPeriodForecast.add(p);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName() + JSonStr.getJSonStr().obj2json(this, true));
        return sb.toString();
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

    public List<PointPeriodForecast> getPointsPeriodForecast() {
        return pointsPeriodForecast;
    }

    public void setPointsPeriodForecast(List<PointPeriodForecast> pointsPeriodForecast) {
        this.pointsPeriodForecast = pointsPeriodForecast;
    }

    public List<OperationKpi> getOperationKpis() {
        return operationKpis;
    }

    public void setOperationKpis(List<OperationKpi> operationKpis) {
        this.operationKpis = operationKpis;
    }

}
