package com.wjl.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.common.ResultJson;
import com.wjl.system.entity.SystemRole;
import com.wjl.system.entity.SystemUser;
import com.wjl.system.entity.extend.SystemUserExt;
import com.wjl.system.entity.extend.UserRoleAuth;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
public interface ISystemUserService extends IService<SystemUser> {

    String getAuthorityInfo(int userId);

    SystemUser getSystemUserByUsername(String username);

    List<UserRoleAuth> listUserAuthMenus();

    /**
     * 列表
     * @param page
     * @param username
     * @return
     */
    IPage<SystemUserExt> page(Page<SystemUser> page, String username);

    /**
     * 新增用户
     * @param systemUser
     */
    ResultJson add(SystemUser systemUser);

    /**
     * 通过用户id查询角色信息
     * @param userId 用户id
     * @return
     */
    List<Integer> getRolesByUserId(Integer userId);

    /**
     * 给用户赋值权限
     * 先将用户的原来权限删除
     * 然后在重新插入数据
     * @param userId 用户id
     * @param roleId 角色id
     * @return
     */
    @Transactional
    ResultJson setRole(Integer userId, Integer [] roleId);
}
