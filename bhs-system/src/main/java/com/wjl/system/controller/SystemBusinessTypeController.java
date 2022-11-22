package com.wjl.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjl.common.system.ResultJson;
import com.wjl.enums.system.result.SystemBusinessEnum;
import com.wjl.system.entity.SystemBusinessType;
import com.wjl.system.entity.vo.SystemBusinessTypeVO;
import com.wjl.system.service.ISystemBusinessTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2022-08-24
 */
@Api(tags = "字典分类管理")
@RestController
@RequestMapping("/system/business/type")
public class SystemBusinessTypeController extends BaseController<SystemBusinessType> {

    @Autowired
    private ISystemBusinessTypeService systemBusinessTypeService;

    @ApiOperation("字典分类列表")
    @PostMapping("list")
    public ResultJson list(@RequestBody SystemBusinessTypeVO businessTypeVO) {
        IPage<SystemBusinessType> pageList = systemBusinessTypeService.page(getPage(), businessTypeVO);
        return ResultJson.success(pageList);
    }

    @ApiOperation("字典分类添加")
    @PostMapping("add")
    public ResultJson add(@RequestBody @Valid SystemBusinessTypeVO businessTypeVO) {
        return getResultJson(systemBusinessTypeService.add(businessTypeVO));
    }

    @ApiOperation("通过id获取字典分类")
    @PostMapping("getById/{id}")
    public ResultJson getById(@PathVariable Integer id) {
        SystemBusinessType businessType = systemBusinessTypeService.getById(id);
        return ResultJson.success(businessType);
    }

    @ApiOperation("通过id更新字典分类")
    @PostMapping("update")
    public ResultJson update(@RequestBody @Valid SystemBusinessTypeVO businessTypeVO) {
        return getResultJson(systemBusinessTypeService.updateById(businessTypeVO));
    }

    @ApiOperation("通过id删除字典分类")
    @GetMapping("delete/{id}")
    public ResultJson delete(@PathVariable Integer id) {
        return getResultJson(systemBusinessTypeService.deleteById(id));
    }

    private ResultJson getResultJson(SystemBusinessEnum systemBusinessDataEnum) {
        return ResultJson.result(systemBusinessDataEnum.getCode(), systemBusinessDataEnum.getMsg(), null);
    }
}
