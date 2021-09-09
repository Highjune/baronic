package com.hospital.baronic.define;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseCode {
    SUCCESS("0", "SUCCESS"),
    FAIL("100", "FAIL"),
    NODATA("99", "NODATA"),

    ERROR_INVALID_PARAMETER("103", "ERROR_INVALID_PARAMETER"),
    ;

    final private String statuscode;
    final private String message;

    private ResponseCode(String statuscode, String message) {
        this.statuscode = statuscode;
        this.message = message;
    }

    @JsonValue
    public String getStatusCode() {
        return statuscode;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseCode findByCode(String statuscode) {
        for(ResponseCode v : values()) {
            if(v.getStatusCode().equals(statuscode)) {
                return v;
            }
        }

        return null;
    }
    public static String findByValue(String statuscode) {
        for(ResponseCode v : values()) {
            if(v.getStatusCode().equals(statuscode)) {
                return v.getMessage();
            }
        }

        return null;
    }

    }
