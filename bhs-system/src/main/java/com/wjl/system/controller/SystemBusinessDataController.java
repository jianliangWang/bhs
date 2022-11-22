package com.wjl.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjl.common.system.ResultJson;
import com.wjl.enums.system.result.SystemBusinessEnum;
import com.wjl.system.entity.SystemBusinessData;
import com.wjl.system.entity.vo.SystemBusinessDataVO;
import com.wjl.system.service.ISystemBusinessDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  数据字典 前端控制器
 * </p>
 *
 * @author jay
 * @since 2022-11-20
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("/system/business/data")
public class SystemBusinessDataController extends BaseController<SystemBusinessData> {

    private final ISystemBusinessDataService systemBusinessDataService;

    public SystemBusinessDataController(
        ISystemBusinessDataService systemBusinessDataService) {
        this.systemBusinessDataService = systemBusinessDataService;
    }

    @ApiOperation("数据字典列表")
    @PostMapping("list")
    public ResultJson list(@RequestBody SystemBusinessDataVO systemBusinessDataVO) {
        IPage<SystemBusinessData> pageList = systemBusinessDataService.list(getPage(), systemBusinessDataVO);
        return ResultJson.success(pageList);
    }

    @ApiOperation("数据字典添加")
    @PostMapping("add")
    public ResultJson add(@RequestBody @Valid SystemBusinessDataVO systemBusinessDataVO) {
        return getResultJson(systemBusinessDataService.add(systemBusinessDataVO));
    }

    @ApiOperation("数据字典修改")
    @PostMapping("update")
    public ResultJson update(@RequestBody @Valid SystemBusinessDataVO systemBusinessDataVO) {
        return getResultJson(systemBusinessDataService.update(systemBusinessDataVO));
    }

    @GetMapping("getById/{id}")
    public ResultJson getById(@PathVariable Integer id) {
        return ResultJson.success(systemBusinessDataService.getById(id));
    }

    @PostMapping("getByTypeCode")
    public ResultJson getTypeCode(String typeCode){
        return ResultJson.success(systemBusinessDataService.getListByTypeCode(typeCode));
    }

    @ApiOperation("数据字典删除")
    @GetMapping("delete/{id}")
    public ResultJson delete(@PathVariable Integer id) {
        return getResultJson(systemBusinessDataService.delete(id));
    }

    private ResultJson getResultJson(SystemBusinessEnum systemBusinessDataEnum) {
        return ResultJson.result(systemBusinessDataEnum.getCode(), systemBusinessDataEnum.getMsg(), null);
    }
}
