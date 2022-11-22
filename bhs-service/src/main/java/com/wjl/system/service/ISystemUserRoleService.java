package com.wjl.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.system.entity.SystemUserRole;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;

public interface ISystemUserRoleService extends IService<SystemUserRole> {

    /**
     * 删除用户角色
     * @param userIds
     * @return
     */
    @CacheEvict(value = {"listRoleByUserId"}, allEntries = true)
    boolean removeByUserIds(List<?> userIds);
}
