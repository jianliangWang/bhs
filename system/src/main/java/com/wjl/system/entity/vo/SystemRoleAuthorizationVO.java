package com.wjl.system.entity.vo;

import com.wjl.system.entity.extend.SystemAuthorizationExt;
import java.util.List;

public class SystemRoleAuthorizationVO {

    private List<Integer> roleAuthList;

    private List<SystemAuthorizationExt> authorizationList;

    public List<Integer> getRoleAuthList() {
        return roleAuthList;
    }

    public void setRoleAuthList(List<Integer> roleAuthList) {
        this.roleAuthList = roleAuthList;
    }

    public List<SystemAuthorizationExt> getAuthorizationList() {
        return authorizationList;
    }

    public void setAuthorizationList(List<SystemAuthorizationExt> authorizationList) {
        this.authorizationList = authorizationList;
    }
}
