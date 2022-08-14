package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjl.common.ResultJson;
import com.wjl.consts.UserConsts;
import com.wjl.system.entity.SystemRole;
import com.wjl.system.entity.SystemUser;
import com.wjl.system.entity.SystemUserRole;
import com.wjl.system.entity.extend.SystemUserExt;
import com.wjl.system.entity.extend.UserRoleAuth;
import com.wjl.system.mapper.SystemUserMapper;
import com.wjl.system.mapper.SystemUserRoleMapper;
import com.wjl.system.service.ISystemRoleService;
import com.wjl.system.service.ISystemUserRoleService;
import com.wjl.system.service.ISystemUserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private ISystemRoleService roleService;

    @Autowired
    private ISystemUserRoleService systemUserRoleService;

    @Override
    public String getAuthorityInfo(int userId) {

        String authorities = "";
        List<UserRoleAuth> authorizationList = systemUserMapper.getNavMenu(userId);
        if (authorizationList == null || authorizationList.size() <= 0) {
            return authorities;
        }
        authorities = authorizationList.stream().map(auth -> "ROLE_" + auth.getRoleCode()).distinct()
            .collect(Collectors.joining(","));
        authorities = authorities.concat(",")
            .concat(authorizationList.stream().map(UserRoleAuth::getCode).collect(Collectors.joining(",")));
        return authorities;
    }

    @Override
    public SystemUser getSystemUserByUsername(String username) {
        QueryWrapper<SystemUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return this.getOne(wrapper);
    }

    @Override
    public List<UserRoleAuth> listUserAuthMenus() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = getSystemUserByUsername(username).getId();
        List<UserRoleAuth> list = systemUserMapper.getNavMenu(userId);
        List<UserRoleAuth> resultList = list.parallelStream().map(item -> {
            List<UserRoleAuth> children =
                list.stream().filter(c -> c.getParentCode().equals(item.getCode())).collect(Collectors.toList());
            item.setChildren(children);
            return item;
        }).filter(rs -> rs.getParentCode().equals(UserConsts.SYSTEM_MENU_ROOT_CODE)).collect(Collectors.toList());
        return resultList;
    }

    @Override
    public IPage<SystemUserExt> page(Page page, String username) {
        Page<SystemUser> userPage = systemUserMapper.selectPage(page,
            new QueryWrapper<SystemUser>().like(StringUtils.hasText(username), "username",
                username));
        IPage<SystemUserExt> pageExt = new Page<>();
        List<SystemUserExt> list = new ArrayList<>();
        userPage.getRecords().forEach(u -> {
            SystemUserExt userExt = new SystemUserExt();
            BeanUtils.copyProperties(u, userExt);
            userExt.setRoles(roleService.listRoleByUserId(u.getId()));
            list.add(userExt);
        });
        BeanUtils.copyProperties(userPage, pageExt, "records");
        pageExt.setRecords(list);
        return pageExt;
    }


    @Override
    public ResultJson add(SystemUser systemUser) {
        systemUser.setCreateDate(LocalDateTime.now());
        systemUser.setLastLoginDate(LocalDateTime.now());
        if (!save(systemUser)) {
            return ResultJson.fail("添加失败");
        }
        return ResultJson.success();
    }

    @Override
    public List<Integer> getRolesByUserId(Integer userId) {
        Wrapper<SystemUserRole> wrapper = new QueryWrapper<SystemUserRole>().eq("user_id", userId);
        return systemUserRoleService.list(wrapper).stream().map(i->i.getRoleId()).collect(Collectors.toList());
    }

    @Override
    public ResultJson setRole(Integer userId, Integer[] roleId) {
        List<SystemUserRole> systemUserRoleList = new LinkedList<>();
        Arrays.asList(roleId).forEach(i->{
            SystemUserRole systemUserRole = new SystemUserRole();
            systemUserRole.setUserId(userId);
            systemUserRole.setRoleId(i);
            systemUserRoleList.add(systemUserRole);
        });
        QueryWrapper<SystemUserRole> wrapper = new QueryWrapper<SystemUserRole>().eq("user_id", userId);
        systemUserRoleService.remove(wrapper);
        systemUserRoleService.saveBatch(systemUserRoleList);
        return ResultJson.success();
    }
}
