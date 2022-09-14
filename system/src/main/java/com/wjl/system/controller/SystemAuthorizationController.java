package com.wjl.system.controller;

import com.wjl.common.ResultJson;
import com.wjl.system.entity.SystemAuthorization;
import com.wjl.system.entity.extend.SystemAuthorizationExt;
import com.wjl.system.service.ISystemAuthorizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(tags = "系统权限管理", value = "authorization")
@RestController
@RequestMapping("/system/authorization")
public class SystemAuthorizationController {

    private final ISystemAuthorizationService systemAuthorizationService;

    public SystemAuthorizationController(ISystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    @ApiOperation(value = "权限列表", notes = "查询所有权限，组装成树形结构返回", httpMethod = "GET")
    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('system-authorization-list')")
    public ResultJson list() {
        List<SystemAuthorizationExt> treeList = systemAuthorizationService.menuTreeList(
            systemAuthorizationService.menuList());
        return ResultJson.success(treeList);
    }

    @ApiOperation(value = "获取权限信息", notes = "获取单个权限信息", httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "权限编号", required = true, dataType = "int")
    })
    @GetMapping("getInfo/{id}")
    @PreAuthorize("hasAnyAuthority('system-authorization-list')")
    public ResultJson getInfo(@PathVariable("id") Integer id) {
        SystemAuthorization systemAuthorization = systemAuthorizationService.getOneById(id);
        return ResultJson.success(systemAuthorization);
    }

    @ApiOperation(value = "新增权限信息保存", notes = "新增权限信息保存")
    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('system-authorization-save')")
    public ResultJson save(@RequestBody SystemAuthorization authorization) {
        authorization.setId(null);
        if (systemAuthorizationService.save(authorization)) {
            return ResultJson.success();
        }
        return ResultJson.fail("新增失败！");
    }

    @ApiOperation(value = "权限信息修改", notes = "通过id修改权限信息")
    @PostMapping("update")
    @PreAuthorize("hasAnyAuthority('system-authorization-update')")
    public ResultJson update(@RequestBody SystemAuthorization authorization) {
        if (systemAuthorizationService.updateById(authorization)) {
            return ResultJson.success();
        }
        return ResultJson.fail("修改失败！");
    }

    @ApiOperation(value = "权限信息删除", notes = "通过id删除权限信息，如果存在子菜单不能删除")
    @GetMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('system-authorization-delete')")
    public ResultJson delete(@PathVariable("id") Integer id) {
        if (systemAuthorizationService.removeById(id)) {
            return ResultJson.success();
        }
        return ResultJson.fail("删除失败，ID为空或有子菜单");
    }
}
