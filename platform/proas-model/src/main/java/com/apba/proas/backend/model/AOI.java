package com.apba.proas.backend.model;

import java.util.ArrayList;
import java.util.List;

public class AOI {

    int id;
    String name;
    GpsPoint center;
    Vessel vessel;
    List<GpsPoint> points = new ArrayList<GpsPoint>();
    AoiState aoiState;
    String origin = "local";

    public AoiState getAoiState() {
        return aoiState;
    }

    public void setAoiState(AoiState aoiState) {
        this.aoiState = aoiState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GpsPoint getCenter() {
        return center;
    }

    public void setCenter(GpsPoint center) {
        this.center = center;
    }

    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }

    public List<GpsPoint> getPoints() {
        return points;
    }

    public void setPoints(List<GpsPoint> points) {
        this.points = points;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName() + JSonStr.getJSonStr().obj2json(this, true));
        return sb.toString();
    }
}
