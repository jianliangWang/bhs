package com.wjl.common.system;

import java.io.Serializable;

/**
 * 返回给前端的json格式封装
 * 异常错误码 默认400
 * 业务处理失败错误码 默认 600
 * 更多错误码会定义错误码enums
 */
public class ResultJson implements Serializable {

    // 成功code
    private static final int SUCCESS_CODE = 200;

    // 失败code
    private static final int FAIL_CODE = 600;

    // 异常code
    private static final int ERROR_CODE = 400;

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
        return result(SUCCESS_CODE, msg, data);
    }

    public static ResultJson fail(int code, String msg) {
        return result(code, msg, null);
    }

    /**
     * 业务处理失败返回
     * @param msg 失败原因
     * @return
     */
    public static ResultJson fail(String msg) {
        return fail(FAIL_CODE, msg);
    }

    /**
     * 产生异常的时候返回
     * @param msg 异常值
     * @return
     */
    public static ResultJson error(String msg) {
        return error(ERROR_CODE, msg);
    }

    public static ResultJson error(int code, String msg) {
        return result(code, msg, null);
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
