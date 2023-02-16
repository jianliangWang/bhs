package com.wjl.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author jay
 * @since 2022-12-05
 */
@ApiModel(value = "Agent对象", description = "代理商")
public class Agent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("代理商编号")
    private String agentNumber;

    @ApiModelProperty("代理商名称")
    private String name;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("代理商级别1，2，3")
    private Integer level;

    @ApiModelProperty("上级代理商id")
    private String parentNumber;

    @ApiModelProperty("余额")
    private Integer balance;

    @ApiModelProperty("押金")
    private Integer deposit;

    @ApiModelProperty("代理省份")
    private String province;

    @ApiModelProperty("代理城市")
    private String city;

    @ApiModelProperty("具体地址")
    private String address;

    @ApiModelProperty("证件类型")
    private String certificateType;

    @ApiModelProperty("证件号码")
    private String certificateId;

    @ApiModelProperty("密钥")
    private String signKey;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("更新人")
    private String updateBy;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Agent{" +
        "id=" + id +
        ", agentNumber=" + agentNumber +
        ", name=" + name +
        ", status=" + status +
        ", level=" + level +
        ", parentNumber=" + parentNumber +
        ", balance=" + balance +
        ", deposit=" + deposit +
        ", province=" + province +
        ", city=" + city +
        ", address=" + address +
        ", certificateType=" + certificateType +
        ", certificateId=" + certificateId +
        ", signKey=" + signKey +
        ", description=" + description +
        ", remark=" + remark +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        "}";
    }
}
