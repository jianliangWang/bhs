package com.wjl.system.service;

import com.wjl.system.entity.SystemRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.system.entity.SystemUserRole;
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

    List<SystemRole> listRoleByUserId(Integer id);
}
