package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjl.system.entity.SystemRole;
import com.wjl.system.entity.SystemUserRole;
import com.wjl.system.mapper.SystemRoleMapper;
import com.wjl.system.service.ISystemRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements ISystemRoleService {


    @Override
    public List<SystemRole> listRoleByUserId(Integer userId) {
        List<SystemRole> systemRoles = this.list(new QueryWrapper<SystemRole>().inSql("id", "select role_id from "
            + "system_user_role where user_id = "+ userId));
        return systemRoles;
    }
}
