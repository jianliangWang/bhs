package com.wjl.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.system.entity.SystemRoleAuthorization;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jay
 * @since 2022-06-30
 */
public interface ISystemRoleAuthorizationService extends IService<SystemRoleAuthorization> {

    /**
     * 给角色授权
     * @param id 角色id
     * @param authIds 权限id
     */
    @CacheEvict(value = {"listUserAuthMenus", "getAuthorityInfo"}, allEntries = true)
    void setRoleAuth(Integer id, Integer[] authIds);

    /**
     * 通过用户id查询用户权限信息
     * @param userId
     * @return
     */
    @Cacheable(value = "getAuthorityInfo")
    String getAuthorityInfo(int userId);
}
