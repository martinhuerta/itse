package com.apba.proas.backend.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JSonStr {
    static boolean DEBUG = true;

    static JSonStr jSonStr = new JSonStr();
    Logger log;
    Gson gson;

    public JSonStr() {
        log = LoggerFactory.getLogger(JSonStr.class);

        GsonBuilder gsb = new GsonBuilder();
        // Date: standard forms ("yyyy-MM-dd'T'HH:mm:ss.SSSX",
        // "yyyy-MM-dd'T'HH:mm:ss.SSS", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"))
        gsb.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        gson = gsb.setPrettyPrinting().create();
    }

    public static JSonStr getJSonStr() {
        return jSonStr;
    }

    public String obj2json(Object o) {
        return obj2json(o, true);
    }

    public String obj2json(Object o, boolean pretty) {
        String jsonOutput = gson.toJson(o);
        if (!pretty) {
            return jsonOutput;
        } else {

            JsonElement je = JsonParser.parseString(jsonOutput);
            String prettyJsonString = gson.toJson(je);
            if (DEBUG)
                log.debug(String.format("------------%s: %s", o.getClass().getName(), prettyJsonString));
            return prettyJsonString;
        }
    }
}
