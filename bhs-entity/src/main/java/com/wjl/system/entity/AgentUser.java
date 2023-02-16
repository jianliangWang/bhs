package com.wjl.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 代理商用户;
 * </p>
 *
 * @author jay
 * @since 2022-12-05
 */
@TableName("agent_user")
@ApiModel(value = "AgentUser对象", description = "代理商用户;")
public class AgentUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("代理商编号")
    private String agentNumber;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("总金额")
    private Long amount;

    @ApiModelProperty("账户余额;单位（分）")
    private Integer balance;

    @ApiModelProperty("加密余额")
    private String encryptionBalance;

    @ApiModelProperty("签名密钥")
    private String signKey;

    @ApiModelProperty("登录失败次数3次锁定")
    private Integer loginFailCount;

    @ApiModelProperty("最后一次登录IP")
    private String lastLoginIp;

    @ApiModelProperty("最后一次登录时间")
    private LocalDateTime lastLoginTime;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getEncryptionBalance() {
        return encryptionBalance;
    }

    public void setEncryptionBalance(String encryptionBalance) {
        this.encryptionBalance = encryptionBalance;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public Integer getLoginFailCount() {
        return loginFailCount;
    }

    public void setLoginFailCount(Integer loginFailCount) {
        this.loginFailCount = loginFailCount;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
        return "AgentUser{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", agentNumber=" + agentNumber +
        ", status=" + status +
        ", phoneNumber=" + phoneNumber +
        ", amount=" + amount +
        ", balance=" + balance +
        ", encryptionBalance=" + encryptionBalance +
        ", signKey=" + signKey +
        ", loginFailCount=" + loginFailCount +
        ", lastLoginIp=" + lastLoginIp +
        ", lastLoginTime=" + lastLoginTime +
        ", description=" + description +
        ", remark=" + remark +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        "}";
    }
}
