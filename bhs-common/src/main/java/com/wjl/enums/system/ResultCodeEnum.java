package com.wjl.enums.system;

/**
 * 返回Code
 */
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功"),
    FAIL(601, "code已经存在");

    private int code;

    private String msg;

    ResultCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    int getCode(){
        return code;
    }

    String getMsg(){
        return msg;
    }
}
