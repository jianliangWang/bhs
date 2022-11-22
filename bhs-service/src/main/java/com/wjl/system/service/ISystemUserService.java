package com.wjl.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.common.system.ResultJson;
import com.wjl.system.entity.SystemUser;
import com.wjl.system.entity.extend.SystemUserExt;
import com.wjl.system.entity.extend.UserRoleAuth;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
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

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    SystemUser getSystemUserByUsername(String username);

    /**
     * 用户权限菜单
     * @return
     */
    @Cacheable(value = "listUserAuthMenus")
    List<UserRoleAuth> listUserAuthMenus();

    /**
     * 列表
     * @param page
     * @param username
     * @return
     */
    @Cacheable(value = "userPage", unless = "#result == null")
    IPage<SystemUserExt> page(Page<SystemUser> page, String username);

    /**
     * 新增用户
     * @param systemUser
     */
    @CacheEvict(value = "userPage", allEntries = true)
    ResultJson add(SystemUser systemUser);

    /**
     * 更新用户信息，用户名不能更新
     * @param systemUser
     * @return
     */
    @CacheEvict(value = "userPage", allEntries = true)
    boolean updateById(SystemUser systemUser);

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
    @CacheEvict(value = {"getRolesByUserId", "userPage", "listUserAuthMenus", "getAuthorityInfo", "listRoleByUserId"}, allEntries = true)
    @Transactional
    ResultJson setRole(Integer userId, Integer [] roleId);

    /**
     * 通过id重置密码
     * @param id
     * @return
     */
    @CacheEvict(value = "userPage", allEntries = true)
    boolean resetPassword(Integer id, String password);

    /**
     * 删除用户，先将用户删除，再将用户角色关系删除
     * @param ids
     * @return
     */
    @CacheEvict(value = "userPage", allEntries = true)
    boolean removeByIds(List<Integer> ids);

    /**
     * 批量更新用户状态
     * @param ids 用户ids
     * @param status 状态
     * @return
     */
    @CacheEvict(value = "userPage", allEntries = true)
    boolean updateStatus(List<Integer> ids, String status);

}
