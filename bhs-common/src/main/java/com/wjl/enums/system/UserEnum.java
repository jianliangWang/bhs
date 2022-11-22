package com.wjl.enums.system;

public enum UserEnum {

    TOKEN_REDIS_KEY;

    public String getValue(){

        return this.toString().toLowerCase();
    }
}
