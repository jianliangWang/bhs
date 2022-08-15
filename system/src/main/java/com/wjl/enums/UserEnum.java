package com.wjl.enums;

public enum UserEnum {

    TOKEN_REDIS_KEY;

    public String getValue(){

        return this.toString().toLowerCase();
    }
}
