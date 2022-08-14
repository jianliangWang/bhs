package com.wjl.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjl.common.ResultJson;
import com.wjl.system.entity.SystemAuthorization;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanUtils;
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
 *  前端控制器
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
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


    @PostMapping("list")
    public ResultJson list(String roleCode) {

        IPage<SystemRole> rolePageList = systemRoleService.page(getPage(),
            new QueryWrapper<SystemRole>().like(StringUtils.hasText(roleCode), "code", roleCode));

        return ResultJson.success(rolePageList);
    }

    @PostMapping("add")
    public ResultJson add(@Validated @RequestBody SystemRolePO systemRolePO) {
        SystemRole systemRole = new SystemRole();
        BeanUtils.copyProperties(systemRolePO, systemRole);
        systemRoleService.save(systemRole);
        return ResultJson.success();
    }

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

    @GetMapping("getInfo/{id}")
    public ResultJson getInfo(@PathVariable int id) {
        SystemRole systemRole = systemRoleService.getById(id);
        Assert.notNull(systemRole, "找不到该用户");
        return ResultJson.success(systemRole);
    }

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

    @GetMapping("getAuth/{id}")
    public ResultJson getAuth(@PathVariable String id) {
        List<SystemAuthorizationExt> systemAuthorizationList = authorizationService.menuTreeList();
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

    @Transactional
    @PostMapping("setAuth/{id}")
    public ResultJson setAuth(@PathVariable Integer id, @RequestBody Integer[] authIds){
        roleAuthorizationService.remove(new QueryWrapper<SystemRoleAuthorization>().eq("role_id", id));
        List<SystemRoleAuthorization> roleAuthorizationList = new ArrayList<>();
        for (int i = 0; i < authIds.length; i++) {
            SystemRoleAuthorization systemRoleAuthorization = new SystemRoleAuthorization();
            systemRoleAuthorization.setRoleId(id);
            systemRoleAuthorization.setAuthorizationId(authIds[i]);
            roleAuthorizationList.add(systemRoleAuthorization);
        }
        roleAuthorizationService.saveBatch(roleAuthorizationList);
        return ResultJson.success();
    }
}
