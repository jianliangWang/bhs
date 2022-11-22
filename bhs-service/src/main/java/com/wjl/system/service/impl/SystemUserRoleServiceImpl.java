package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjl.system.entity.SystemUserRole;
import com.wjl.system.mapper.SystemUserRoleMapper;
import com.wjl.system.service.ISystemUserRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> implements
    ISystemUserRoleService {

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Override
    public boolean removeByUserIds(List<?> userIds) {
        if (userIds == null || userIds.size() == 0) {
            return false;
        }
        return systemUserRoleMapper.delete(new QueryWrapper<SystemUserRole>().in("user_id", userIds)) > 1;
    }
}
