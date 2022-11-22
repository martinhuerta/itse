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

}
