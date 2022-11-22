package com.wjl.system.entity.vo;

import com.wjl.system.entity.SystemRole;
import java.util.List;

public class SystemUserRoleVO {

    private List<Integer> ckRoleIds;

    private List<SystemRole> roleList;

    public List<Integer> getCkRoleIds() {
        return ckRoleIds;
    }

    public void setCkRoleIds(List<Integer> ckRoleIds) {
        this.ckRoleIds = ckRoleIds;
    }

    public List<SystemRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SystemRole> roleList) {
        this.roleList = roleList;
    }
}
