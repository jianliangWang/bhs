package com.wjl.common;

import java.io.Serializable;

public class ResultJson implements Serializable {

    private int code;

    private String msg;

    private Object data;

    public static ResultJson success() {
        return success(null);
    }

    public static ResultJson success(Object data) {
        return success("操作成功", data);
    }

    public static ResultJson success(String msg, Object data) {
        return result(200, msg, data);
    }

    public static ResultJson fail(int code, String msg) {
        return result(code, msg, null);
    }

    public static ResultJson fail(String msg) {
        return fail(400, msg);
    }

    public static ResultJson result(int code, String msg, Object data) {
        ResultJson result = new ResultJson();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
