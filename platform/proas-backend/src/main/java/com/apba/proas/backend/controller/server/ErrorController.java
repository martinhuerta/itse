package com.apba.proas.backend.controller.server;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

// @RestController
public class ErrorController {

    // @RequestMapping(path = "/error")
    public Map<String, Object> handle(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", request.getAttribute("jakarta.servlet.error.status_code"));
        map.put("reason", request.getAttribute("jakarta.servlet.error.message"));
        return map;
    }
}