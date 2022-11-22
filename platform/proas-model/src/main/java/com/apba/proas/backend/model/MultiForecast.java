package com.apba.proas.backend.model;

import java.util.HashMap;
import java.util.Map;

public class MultiForecast implements Variable {
    // WindForecast wind;
    // SurgeForecast surge;
    // CurrentForecast current;
    // SeaLevelForecast seaLevel;

    String variableType = VariableType.MULTI_VARIABLE.toString();
    private final Map<String, Variable> map = new HashMap<String, Variable>();

    @Override
    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String s) {
    }

    public void settVariable(Variable forecastVariable) {
        assert forecastVariable != null;
        map.put(forecastVariable.getVariableType(), forecastVariable);
    }

    public Variable getVariable(String types) {
        return map.get(types);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName())
                .append(" { variableType = '" + variableType + "'");
        map.keySet().stream().forEach(t -> sb.append("'").append(t).append("' = ").append(map.get(t).toString()));
        sb.append(" }");
        return sb.toString();
    }
}