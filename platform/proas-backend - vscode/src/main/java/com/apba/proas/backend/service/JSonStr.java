package com.apba.proas.backend.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JSonStr {
    private static final boolean DEBUG = true;
    static JSonStr jSonStr = new JSonStr();
    Logger log = LoggerFactory.getLogger(JSonStr.class);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();;

    public static JSonStr getSonStr() {
        return jSonStr;
    }

    @PostConstruct
    void init() {
        log.info("-------------- JSonStr inicializado OK -------------");
    }

    public String obj2json(Object o) {
        String jsonOutput = gson.toJson(o);
        if (!DEBUG) {
            return jsonOutput;
        } else {
            JsonElement je = JsonParser.parseString(jsonOutput);
            String prettyJsonString = gson.toJson(je);
            log.info(String.format("------------%s: %s", o.getClass().getName(), prettyJsonString));
            return prettyJsonString;
        }
    }
}
