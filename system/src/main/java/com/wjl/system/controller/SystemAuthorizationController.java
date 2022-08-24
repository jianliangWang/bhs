package com.wjl.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjl.common.ResultJson;
import com.wjl.system.entity.SystemAuthorization;
import com.wjl.system.entity.extend.SystemAuthorizationExt;
import com.wjl.system.service.ISystemAuthorizationService;
import io.jsonwebtoken.lang.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/system/authorization")
public class SystemAuthorizationController {

    private final ISystemAuthorizationService systemAuthorizationService;

    public SystemAuthorizationController(ISystemAuthorizationService systemAuthorizationService) {
        this.systemAuthorizationService = systemAuthorizationService;
    }

    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('system-authorization-list')")
    public ResultJson list() {
        List<SystemAuthorizationExt> treeList = systemAuthorizationService.menuTreeList();
        return ResultJson.success(treeList);
    }

    @GetMapping("getInfo/{id}")
    @PreAuthorize("hasAnyAuthority('system-authorization-list')")
    public ResultJson getInfo(@PathVariable("id") Integer id) {
        SystemAuthorization systemAuthorization =
            systemAuthorizationService.getOne(new QueryWrapper<SystemAuthorization>().eq("id", id));
        return ResultJson.success(systemAuthorization);
    }

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('system-authorization-save')")
    public ResultJson save(@RequestBody SystemAuthorization authorization) {
        authorization.setId(null);
        systemAuthorizationService.save(authorization);
        return ResultJson.success();
    }

    @PostMapping("update")
    @PreAuthorize("hasAnyAuthority('system-authorization-update')")
    public ResultJson update(@RequestBody SystemAuthorization authorization) {
        systemAuthorizationService.updateById(authorization);
        return ResultJson.success();
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('system-authorization-delete')")
    public ResultJson delete(@PathVariable("id") Integer id) {
        /*查询是否有子菜单，有子菜单不允许删除*/
        String code = systemAuthorizationService.getById(id).getCode();
        List<SystemAuthorization> list = systemAuthorizationService.list(
            new QueryWrapper<SystemAuthorization>().eq("parent_code",
                code));
        if (Collections.isEmpty(list)) {
            systemAuthorizationService.removeById(id);
            return ResultJson.success();
        }
        return ResultJson.fail("有子菜单，请先删除子菜单");
    }
}
