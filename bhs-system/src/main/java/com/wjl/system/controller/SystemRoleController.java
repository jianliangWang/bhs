package com.wjl.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjl.common.system.ResultJson;
import com.wjl.system.entity.SystemRole;
import com.wjl.system.entity.SystemRoleAuthorization;
import com.wjl.system.entity.SystemUserRole;
import com.wjl.system.entity.extend.SystemAuthorizationExt;
import com.wjl.system.entity.po.SystemRolePO;
import com.wjl.system.entity.vo.SystemRoleAuthorizationVO;
import com.wjl.system.service.ISystemAuthorizationService;
import com.wjl.system.service.ISystemRoleAuthorizationService;
import com.wjl.system.service.ISystemRoleService;
import com.wjl.system.service.ISystemUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  角色控制器
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
@Api(tags= "角色管理")
@RestController
@RequestMapping("/system/role")
public class SystemRoleController extends BaseController<SystemRole> {

    private final ISystemRoleService systemRoleService;

    private final ISystemUserRoleService systemUserRoleService;

    private final ISystemAuthorizationService authorizationService;

    private final ISystemRoleAuthorizationService roleAuthorizationService;


    public SystemRoleController(
        ISystemRoleService systemRoleService, ISystemUserRoleService systemUserRoleService,
        ISystemAuthorizationService authorizationService, ISystemRoleAuthorizationService roleAuthorizationService) {
        this.systemRoleService = systemRoleService;
        this.systemUserRoleService = systemUserRoleService;
        this.authorizationService = authorizationService;
        this.roleAuthorizationService = roleAuthorizationService;
    }

    @ApiOperation("角色分页列表，查询条件角色编码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "roleCode", value = "角色编码", required = false, dataType = "String")
    })
    @PreAuthorize("hasAuthority('system-role-list')")
    @PostMapping("list")
    public ResultJson list(String roleCode) {

        IPage<SystemRole> rolePageList = systemRoleService.page(getPage(),
            new QueryWrapper<SystemRole>().like(StringUtils.hasText(roleCode), "code", roleCode));

        return ResultJson.success(rolePageList);
    }

    @PreAuthorize("hasAuthority('system-role-add')")
    @PostMapping("add")
    public ResultJson add(@Validated @RequestBody SystemRolePO systemRolePO) {
        SystemRole systemRole = new SystemRole();
        BeanUtils.copyProperties(systemRolePO, systemRole);
        systemRoleService.save(systemRole);
        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('system-role-update')")
    @PostMapping("update")
    public ResultJson update(@RequestBody SystemRolePO systemRolePO) {
        if (systemRolePO.getId() == null) {
            return ResultJson.fail("修改失败，id不能为空");
        }
        SystemRole systemRole = new SystemRole();
        BeanUtils.copyProperties(systemRolePO, systemRole);
        systemRoleService.updateById(systemRole);
        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('system-role-list')")
    @GetMapping("getInfo/{id}")
    public ResultJson getInfo(@PathVariable int id) {
        SystemRole systemRole = systemRoleService.getById(id);
        Assert.notNull(systemRole, "找不到该用户");
        return ResultJson.success(systemRole);
    }

    @PreAuthorize("hasAuthority('system-role-delete')")
    @Transactional
    @PostMapping("delete")
    public ResultJson delete(@RequestBody String[] ids) {
        if (ids.length <= 0) {
            return ResultJson.fail("请选择要删除的对象");
        }
        systemRoleService.removeByIds(Arrays.asList(ids));
        systemUserRoleService.remove(new QueryWrapper<SystemUserRole>().in("role_id", Arrays.asList(ids)));
        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('system-role-update')")
    @PostMapping("updateStatus/{status}")
    public ResultJson updateStatus(@RequestBody Integer[] ids, @PathVariable("status") String status) {
        List<SystemRole> updateList = new ArrayList<>();
        for (Integer id : ids) {
            SystemRole systemRole = new SystemRole();
            systemRole.setStatus(status);
            systemRole.setId(id);
            updateList.add(systemRole);
        }
        systemRoleService.updateBatchById(updateList);
        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('system-role-setAuth')")
    @GetMapping("getAuth/{id}")
    public ResultJson getAuth(@PathVariable String id) {
        List<SystemAuthorizationExt> systemAuthorizationList =
            authorizationService.menuTreeList(authorizationService.menuList());
        List<SystemRoleAuthorization> systemRoleAuthorizationList =
            roleAuthorizationService.list(new QueryWrapper<SystemRoleAuthorization>().eq(
                "role_id", id));
        List<Integer> roleAuthorIds = systemRoleAuthorizationList.stream().map(
            SystemRoleAuthorization::getAuthorizationId).toList();
        SystemRoleAuthorizationVO systemRoleAuthorizationVO = new SystemRoleAuthorizationVO();
        systemRoleAuthorizationVO.setRoleAuthList(roleAuthorIds);
        systemRoleAuthorizationVO.setAuthorizationList(systemAuthorizationList);
        return ResultJson.success(systemRoleAuthorizationVO);
    }

    @PreAuthorize("hasAuthority('system-role-setAuth-save')")
    @Transactional
    @PostMapping("setAuth/{id}")
    public ResultJson setAuth(@PathVariable Integer id, @RequestBody Integer[] authIds){
        roleAuthorizationService.setRoleAuth(id, authIds);
        return ResultJson.success();
    }
}
