package com.wjl.enums.system.result;

public enum AgentEnum {

    SUCCESS(0, "操作成功"),
    CERTIFICATE_ID_ERROR(700, "证件号码错误"),
    PHONE_NUMBER_ERROR(701, "手机号码错误"),
    CERTIFICATE_ID_ALREADY_EXIT(703, "证件号码已经存在"),
    PHONE_NUMBER_ALREADY_EXIT(704, "手机号已经存在"),
    ID_NOT_NULL(710, "ID不能为空"),
    USER_NAME_ALREADY_EXIT(720, "用户名已经存在"),
    AGENT_NAME_ALREADY_EXIT(721, "代理商名称已经存在"),
    PASSWORD_NOT_ALLOW_UPDATE(730, "密码不允许修改"),
    FAIL(999, "操作失败");

    private Integer code;

    private String msg;

    AgentEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
