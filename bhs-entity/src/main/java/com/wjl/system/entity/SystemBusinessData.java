package com.wjl.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jay
 * @since 2022-11-20
 */
@TableName("system_business_data")
@ApiModel(value = "SystemBusinessData对象", description = "")
public class SystemBusinessData implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("编码，不能为空")
      private String code;

      @ApiModelProperty("名字")
      private String name;

      @ApiModelProperty("值")
      private String value;

      @ApiModelProperty("序号")
      private Integer sortId;

      @ApiModelProperty("状态")
      private String status;

      @ApiModelProperty("状态码")
      private String typeCode;

      @ApiModelProperty("描述")
      private String description;

      @ApiModelProperty("备注")
      private String remark;

      @ApiModelProperty("最后更新日期")
      private LocalDateTime updateDate;

      @ApiModelProperty("创建日期")
      private LocalDateTime createDate;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getCode() {
        return code;
    }

      public void setCode(String code) {
          this.code = code;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public String getValue() {
        return value;
    }

      public void setValue(String value) {
          this.value = value;
      }
    
    public Integer getSortId() {
        return sortId;
    }

      public void setSortId(Integer sortId) {
          this.sortId = sortId;
      }
    
    public String getStatus() {
        return status;
    }

      public void setStatus(String status) {
          this.status = status;
      }
    
    public String getTypeCode() {
        return typeCode;
    }

      public void setTypeCode(String typeCode) {
          this.typeCode = typeCode;
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
    
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

      public void setUpdateDate(LocalDateTime updateDate) {
          this.updateDate = updateDate;
      }
    
    public LocalDateTime getCreateDate() {
        return createDate;
    }

      public void setCreateDate(LocalDateTime createDate) {
          this.createDate = createDate;
      }

    @Override
    public String toString() {
        return "SystemBusinessData{" +
              "id=" + id +
                  ", code=" + code +
                  ", name=" + name +
                  ", value=" + value +
                  ", sortId=" + sortId +
                  ", status=" + status +
                  ", typeCode=" + typeCode +
                  ", description=" + description +
                  ", remark=" + remark +
                  ", updateDate=" + updateDate +
                  ", createDate=" + createDate +
              "}";
    }
}
