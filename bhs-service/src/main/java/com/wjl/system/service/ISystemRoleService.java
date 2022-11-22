package com.wjl.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.system.entity.SystemRole;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
public interface ISystemRoleService extends IService<SystemRole> {

    /**
     * 分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<SystemRole> page(Page<SystemRole> page, Wrapper<SystemRole> queryWrapper);

    /**
     * 通过用户id获取角色信息
     * @param id
     * @return
     */
    List<SystemRole> listRoleByUserId(Integer id);
}
