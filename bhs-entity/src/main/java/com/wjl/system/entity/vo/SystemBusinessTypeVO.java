package com.wjl.system.entity.vo;

import javax.validation.constraints.NotBlank;

public class SystemBusinessTypeVO {

    private Integer id;

    @NotBlank(message = "名字不能为空")
    private String name;

    @NotBlank(message = "编码不能为空")
    private String code;

    private String description;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
