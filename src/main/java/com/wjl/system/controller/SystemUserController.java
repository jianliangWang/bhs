package com.wjl.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjl.common.ResultJson;
import com.wjl.consts.UserConsts;
import com.wjl.system.entity.SystemRole;
import com.wjl.system.entity.SystemUser;
import com.wjl.system.entity.SystemUserRole;
import com.wjl.system.entity.extend.SystemUserExt;
import com.wjl.system.entity.extend.UserRoleAuth;
import com.wjl.system.entity.vo.SystemUserRoleVO;
import com.wjl.system.entity.vo.SystemUserVO;
import com.wjl.system.service.ISystemRoleService;
import com.wjl.system.service.ISystemUserRoleService;
import com.wjl.system.service.ISystemUserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
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

    public SystemUserController(ISystemUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,
        ISystemUserRoleService userRoleService, ISystemRoleService roleService) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
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

    @PostMapping("add")
    public ResultJson add(@Validated @RequestBody SystemUserVO systemUserVO) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserVO, systemUser);
        systemUser.setPassword(bCryptPasswordEncoder.encode(UserConsts.DEFAULT_PASSWORD));
        return userService.add(systemUser);
    }

    @PostMapping("update")
    public ResultJson update(@RequestBody SystemUserVO systemUserVO) {
        if(systemUserVO.getId() == null){
            return ResultJson.fail("修改失败，id不能为空");
        }
        SystemUser systemUser = new SystemUserExt();
        BeanUtils.copyProperties(systemUserVO, systemUser);
        userService.updateById(systemUser);
        return ResultJson.success();
    }

    @GetMapping("getInfo/{id}")
    public ResultJson getInfo(@PathVariable int id) {
        SystemUser systemUser = userService.getById(id);
        Assert.notNull(systemUser, "找不到该用户");
        return ResultJson.success(systemUser);
    }

    @Transactional
    @PostMapping("delete")
    public ResultJson delete(@RequestBody String[] ids){
        userService.removeByIds(Arrays.asList(ids));
        userRoleService.remove(new QueryWrapper<SystemUserRole>().in("user_id", ids));
        return ResultJson.success();
    }

    @PostMapping("updateStatus/{status}")
    public ResultJson updateStatus(@RequestBody Integer[] ids, @PathVariable("status") String status){
        List<SystemUser> updateList = new ArrayList<>();
        for (Integer id : ids) {
            SystemUser systemUser = new SystemUser();
            systemUser.setStatus(status);
            systemUser.setId(id);
            updateList.add(systemUser);
        }
        userService.updateBatchById(updateList);
        return ResultJson.success();
    }

    @GetMapping("/getUserRole/{userId}")
    public ResultJson getUserRole(@PathVariable("userId") Integer userId) {
        List<Integer> roleIds = userService.getRolesByUserId(userId);
        List<SystemRole> roleList = roleService.list();
        SystemUserRoleVO systemUserRoleVO = new SystemUserRoleVO();
        systemUserRoleVO.setCkRoleIds(roleIds);
        systemUserRoleVO.setRoleList(roleList);
        return ResultJson.success(systemUserRoleVO);
    }

    @PostMapping("/setRole/{userId}")
    public ResultJson setRole(@PathVariable("userId") Integer userId, @RequestBody Integer [] roles){
        return userService.setRole(userId, roles);
    }

    @PostMapping("/resetPassword")
    public ResultJson resetPassword(@PathVariable int id){
        SystemUser systemUser = new SystemUser();
        systemUser.setPassword(bCryptPasswordEncoder.encode(UserConsts.DEFAULT_PASSWORD));
        userService.updateById(systemUser);
        return ResultJson.success();
    }
}
