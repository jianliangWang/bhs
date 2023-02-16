package com.edron.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    /**
     * 获取当前登录用户的用户名，一般用于记录当前新增人和修改人
     * @return 当前登录用户的用户名
     */
    public static String getCurrentSystemUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
