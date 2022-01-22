package com.kshired.springbase.common.utils;

public enum ApiStatus {
    SUCCESS("success"),
    FAIL("fail"),
    ERROR("error");

    private final String value;

    ApiStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
