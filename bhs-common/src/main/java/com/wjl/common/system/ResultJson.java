package com.wjl.common.system;

import java.io.Serializable;

/**
 * 返回给前端的json格式封装
 * 异常错误码 默认400
 * 业务处理失败错误码 默认 600
 * 更多错误码会定义错误码enums
 */
public class ResultJson<T> implements Serializable {

    // 成功code
    private static final int SUCCESS_CODE = 200;

    // 失败code
    private static final int FAIL_CODE = 600;

    // 异常code
    private static final int ERROR_CODE = 400;

    private int code;

    private String msg;

    private T data;

    public static <T> ResultJson<T> success() {
        return success(null);
    }

    public static <T> ResultJson<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> ResultJson<T> success(String msg, T data) {
        return result(SUCCESS_CODE, msg, data);
    }

    public static <T> ResultJson<T> fail(int code, String msg) {
        return result(code, msg, null);
    }

    /**
     * 业务处理失败返回
     * @param msg 失败原因
     * @return
     */
    public static <T> ResultJson<T> fail(String msg) {
        return fail(FAIL_CODE, msg);
    }

    /**
     * 产生异常的时候返回
     * @param msg 异常值
     * @return
     */
    public static <T> ResultJson<T> error(String msg) {
        return error(ERROR_CODE, msg);
    }

    public static <T> ResultJson<T> error(int code, String msg) {
        return result(code, msg, null);
    }

    public static <T> ResultJson<T> result(int code, String msg, T data) {
        ResultJson<T> result = new ResultJson<>();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
