package com.wjl.system.entity.extend;

import com.wjl.system.entity.SystemAuthorization;
import java.util.List;

public class SystemAuthorizationExt extends SystemAuthorization {

    private List<SystemAuthorizationExt> children;

    public List<SystemAuthorizationExt> getChildren() {
        return children;
    }

    public void setChildren(List<SystemAuthorizationExt> children) {
        this.children = children;
    }
}
