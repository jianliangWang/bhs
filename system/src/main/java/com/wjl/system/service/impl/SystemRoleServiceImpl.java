package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjl.system.entity.SystemRole;
import com.wjl.system.mapper.SystemRoleMapper;
import com.wjl.system.service.ISystemRoleService;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "systemRolePage", unless = "#result == null")
    @Override
    public IPage<SystemRole> page(Page<SystemRole> page, Wrapper<SystemRole> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @CacheEvict(value = "systemRolePage", allEntries = true)
    @Override
    public boolean save(SystemRole entity) {
        return super.save(entity);
    }

    @CacheEvict(value = {"systemRolePage", "listRoleByUserId"}, allEntries = true)
    @Override
    public boolean updateById(SystemRole entity) {
        return super.updateById(entity);
    }

    @CacheEvict(value = {"systemRolePage","listRoleByUserId"}, allEntries = true)
    @Override
    public boolean removeById(SystemRole entity) {
        return super.removeById(entity);
    }

    @Cacheable(value = "listRoleByUserId", unless = "#result == null")
    @Override
    public List<SystemRole> listRoleByUserId(Integer userId) {
        List<SystemRole> systemRoles = this.list(new QueryWrapper<SystemRole>().inSql("id", "select role_id from "
            + "system_user_role where user_id = "+ userId));
        return systemRoles;
    }
}
