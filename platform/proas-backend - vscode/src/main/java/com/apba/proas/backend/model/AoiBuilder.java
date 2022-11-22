package com.apba.proas.backend.model;

import java.util.Random;
import java.util.stream.Collectors;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AoiBuilder {
    static int nextAoiId = 1;
    private static Map<Integer, AOI> aoiMap = new HashMap<Integer, AOI>();
    public static String orientiations[] = { "N", "S", "E", "O", "NE", "NO", "SE", "SO" };

    public static Map<Integer, AOI> getAoiMap() {
        return aoiMap;
    }

    public static List<String> getAois() {
        return aoiMap.values().stream().map(x -> "" + x.id + ":" + x.name).collect(Collectors.toList());
    }

    public static AOI getAoi(int id) {
        return aoiMap.get(id);
    }

    public static AOI getFirstAoi() {
        AOI a = aoiMap.values().stream().findFirst().get();
        return a;
    }

    static void setAoiId(AOI aoi, int id) {
        if (aoiMap.containsKey(id)) {
            aoiMap.remove(id);
        }
        aoi.setId(id);
        aoiMap.put(id, aoi);
    }

    /**
     * @return
     */
    public static AOI buildAoiWind() {
        return buildAOI(0, VariableType.WIND.toString());
    }

    public static AOI buildAOI(int id, String variableType) {
        float latitude = 37.08f;
        float longitude = 6.83f;

        // AOI
        AOI aoi = new AOI();
        if (id > 0)
            setAoiId(aoi, id);
        else
            setAoiId(aoi, nextAoiId++);

        aoi.setOrigin("server-AoiBuilder");
        aoi.name = "myAOI-" + aoi.getId();
        aoi.center = new GpsPoint();
        aoi.center.longitude = longitude++;
        aoi.center.latitude = latitude++;
        aoi.aoiState = new AoiState();
        aoi.points = new ArrayList<GpsPoint>();

        // Vessel
        Vessel vessel = new Vessel();
        vessel.vesselType = VesselType.GAS;
        vessel.DWT = 103755;
        vessel.LOA = 244;
        vessel.displacement = 122142;
        vessel.aoiId = aoi.getId();
        aoi.vessel = vessel;

        // GpsPoint - 4 points del AOI que es una línea 45%s
        for (int i = 0; i < 3; i++) {
            GpsPoint point = new GpsPoint();
            point.aoiId = aoi.id;
            point.latitude = latitude++;
            point.longitude = longitude++;
            aoi.points.add(point);
        }

        // EstadosAOI
        AoiState aoiState = aoi.aoiState = new AoiState();
        long milis = System.currentTimeMillis();
        aoiState.fromTime = new Timestamp(milis);
        aoiState.samplesNumber = 2;
        aoiState.interval = 60; // minutos
        aoiState.aoiId = aoi.id;

        // DateForecast
        long t1 = aoiState.getFromTime().getTime();
        for (int i = 0; i < aoiState.getSamplesNumber(); i++) {
            DateForecast df = new DateForecast();
            aoiState.addDateForecast(df);
            df.setTimestamp(new Timestamp(t1));

            for (GpsPoint p : aoi.points) {
                DatePointForecast dpf = new DatePointForecast(df);
                dpf.gpsPoint = p;
                if (variableType.equals(VariableType.WIND.toString())) {
                    dpf.variable = newRandomWindValue(50);
                } else if (variableType.equals(OperationKpiType.SECURITY.toString())) {
                    dpf.variable = newRandomSecurityOperation(1000);
                }
            }
            t1 += aoiState.interval * 60 * 1000;
        }
        aoiState.toTime = new Timestamp(t1);
        return aoi;
    }

    public static WindForecast newRandomWindValue(int max) {
        Random rnd = new Random();
        WindForecast v = new WindForecast();
        int ang = 0;
        v.angle = ang;
        ang += 5;
        v.force = rnd.nextInt(max);
        int i1 = rnd.nextInt(8);
        v.orientiation = orientiations[i1 >= 8 ? 3 : i1];
        return v;
    }

    public static SecurityOperation newRandomSecurityOperation(int max) {
        Random rnd = new Random();
        SecurityOperation s = new SecurityOperation();
        s.setSecurityLevel(rnd.nextInt(max) / 100);
        return s;
    }

}