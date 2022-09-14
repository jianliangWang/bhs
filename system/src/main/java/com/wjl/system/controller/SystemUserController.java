package com.wjl.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjl.common.ResultJson;
import com.wjl.consts.UserConsts;
import com.wjl.system.entity.SystemRole;
import com.wjl.system.entity.SystemUser;
import com.wjl.system.entity.extend.SystemUserExt;
import com.wjl.system.entity.extend.UserRoleAuth;
import com.wjl.system.entity.vo.SystemUserRoleVO;
import com.wjl.system.entity.vo.SystemUserVO;
import com.wjl.system.service.ISystemRoleService;
import com.wjl.system.service.ISystemUserRoleService;
import com.wjl.system.service.ISystemUserService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
@RestController
@RequestMapping("/system/user")
public class SystemUserController extends BaseController<SystemUser> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ISystemUserRoleService userRoleService;

    private final ISystemUserService userService;

    private final ISystemRoleService roleService;

    public SystemUserController(ISystemUserService userService, ISystemUserRoleService userRoleService,
        ISystemRoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * 获取用户的菜单权限
     * @return
     */
    @PostMapping("getLeftMenus")
    public ResultJson getLeftMenus() {
        List<UserRoleAuth> leftMenus = userService.listUserAuthMenus();
        return ResultJson.success(leftMenus);
    }

    @PreAuthorize("hasAuthority('system-user')")
    @PostMapping("list")
    public ResultJson list(String username) {
        IPage<SystemUserExt> pageList = userService.page(getPage(), username);
        return ResultJson.success(pageList);
    }

    @PreAuthorize("hasAuthority('system-user-add')")
    @PostMapping("add")
    public ResultJson add(@Validated @RequestBody SystemUserVO systemUserVO) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserVO, systemUser);
        systemUser.setPassword(bCryptPasswordEncoder.encode(UserConsts.DEFAULT_PASSWORD));
        return userService.add(systemUser);
    }

    @PreAuthorize("hasAuthority('system-user-update')")
    @PostMapping("update")
    public ResultJson update(@RequestBody SystemUserVO systemUserVO) {
        if (systemUserVO.getId() == null) {
            return ResultJson.fail("修改失败，id不能为空");
        }
        SystemUser systemUser = new SystemUserExt();
        BeanUtils.copyProperties(systemUserVO, systemUser);
        userService.updateById(systemUser);
        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('system-user-list')")
    @GetMapping("getInfo/{id}")
    public ResultJson getInfo(@PathVariable int id) {
        SystemUser systemUser = userService.getById(id);
        Assert.notNull(systemUser, "找不到该用户");
        return ResultJson.success(systemUser);
    }

    @PreAuthorize("hasAuthority('system-user-delete')")
    @Transactional
    @PostMapping("delete")
    public ResultJson delete(@RequestBody Integer[] ids) {
        List<Integer> userIds = Arrays.asList(ids);
        userService.removeByIds(userIds);
        userRoleService.removeByUserIds(userIds);
        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('system-user-batchEnable')")
    @PostMapping("updateStatus/{status}")
    public ResultJson updateStatus(@RequestBody Integer[] ids, @PathVariable("status") String status) {
        if(userService.updateStatus(Arrays.asList(ids), status)){
            return ResultJson.success();
        }
        return ResultJson.fail("更新状态失败");
    }

    @PreAuthorize("hasAuthority('system-user-setRole')")
    @GetMapping("/getUserRole/{userId}")
    public ResultJson getUserRole(@PathVariable("userId") Integer userId) {
        List<Integer> roleIds = userService.getRolesByUserId(userId);
        List<SystemRole> roleList = roleService.list();
        SystemUserRoleVO systemUserRoleVO = new SystemUserRoleVO();
        systemUserRoleVO.setCkRoleIds(roleIds);
        systemUserRoleVO.setRoleList(roleList);
        return ResultJson.success(systemUserRoleVO);
    }

    @PreAuthorize("hasAuthority('system-user-setRole-save')")
    @PostMapping("/setRole/{userId}")
    public ResultJson setRole(@PathVariable("userId") Integer userId, @RequestBody Integer[] roles) {
        return userService.setRole(userId, roles);
    }

    @PostMapping("/resetPassword")
    public ResultJson resetPassword(Integer id) {
        userService.resetPassword(id,bCryptPasswordEncoder.encode(UserConsts.DEFAULT_PASSWORD));
        return ResultJson.success();
    }
}
