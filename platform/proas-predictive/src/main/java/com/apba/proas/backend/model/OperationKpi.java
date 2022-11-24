package com.apba.proas.backend.model;

import java.sql.Timestamp;

public class OperationKpi {
    Timestamp timestamp;
    SecurityOperation securityOperation;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public SecurityOperation getSecurityOperation() {
        return securityOperation;
    }

    public void setSecurityOperation(SecurityOperation securityOperation) {
        this.securityOperation = securityOperation;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName() + JSonStr.getJSonStr().obj2json(this, true));
        return sb.toString();
    }

}
