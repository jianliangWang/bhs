package com.wjl.enums.system.result;

/**
 * 数据字典返回编码和信息
 */
public enum SystemBusinessEnum {

    SUCCESS(0, "操作成功"),
    TYPE_CODE_REPEAT_ERROR(641,"添加类型失败，Code已经存在"),
    TYPE_ID_ERROR(642, "数据字典类型ID错误"),
    TYPE_DELETE_ERROR(651, "删除失败，有数据不能删除分类，清先删除数据"),
    TYPE_CODE_ERROR(654, "数据字典分类CODE错误"),
    ID_NULL(655, "ID为空"), FAIL(999, "操作失败");

    private Integer code;

    private String msg;

    SystemBusinessEnum(Integer code, String msg) {
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
