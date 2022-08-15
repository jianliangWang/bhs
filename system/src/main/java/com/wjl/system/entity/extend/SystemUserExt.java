package com.wjl.system.entity.extend;

import com.wjl.system.entity.SystemRole;
import com.wjl.system.entity.SystemUser;
import java.util.List;

public class SystemUserExt extends SystemUser {

    private List<SystemRole> roles;

    public List<SystemRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SystemRole> roles) {
        this.roles = roles;
    }
}
