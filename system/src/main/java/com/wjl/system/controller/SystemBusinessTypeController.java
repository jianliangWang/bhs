package com.wjl.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjl.common.ResultJson;
import com.wjl.system.entity.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjl.system.entity.SystemBusinessType;
import com.wjl.system.service.ISystemBusinessTypeService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jay
 * @since 2022-08-24
 */
@RestController
@RequestMapping("/system/systemBusinessType")
public class SystemBusinessTypeController extends BaseController<SystemBusinessType> {
    @Autowired
    private ISystemBusinessTypeService systemBusinessTypeService;

    @PostMapping("list")
    public ResultJson list(){
        IPage<SystemBusinessType> pageList = systemBusinessTypeService.page(getPage());
        return ResultJson.success(pageList);
    }

    @PostMapping("add")
    public ResultJson add(SystemBusinessType SystemBusinessType){
        systemBusinessTypeService.save(SystemBusinessType);
        return ResultJson.success();
    }

    @PostMapping("update")
    public ResultJson update(SystemBusinessType SystemBusinessType){
        systemBusinessTypeService.updateById(SystemBusinessType);
        return ResultJson.success();
    }

    @PostMapping("delete")
    public ResultJson delete(Integer id){
        systemBusinessTypeService.removeById(id);
        return ResultJson.success();
    }
}
