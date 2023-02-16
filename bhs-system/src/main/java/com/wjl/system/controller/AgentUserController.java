package com.wjl.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjl.common.system.ResultJson;
import com.wjl.enums.system.result.AgentEnum;
import com.wjl.enums.system.result.SystemBusinessEnum;
import com.wjl.system.entity.vo.AgentUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjl.system.entity.AgentUser;
import com.wjl.system.service.IAgentUserService;

/**
 * <p>
 * 代理商用户; 前端控制器
 * </p>
 *
 * @author jay
 * @since 2022-12-05
 */
@Api(tags = "代理商用户")
@RestController
@RequestMapping("/system/agentUser")
public class AgentUserController extends  BaseController<AgentUser>  {

    private final Logger logger = LoggerFactory.getLogger(AgentUserController.class);

    private final IAgentUserService agentUserService;

    public AgentUserController(IAgentUserService agentUserService) {
        this.agentUserService = agentUserService;
    }

    @ApiOperation("列表")
    @PostMapping("list")
    @PreAuthorize("hasAnyAuthority('system-agent-user-list')")
    public ResultJson list(){
        IPage<AgentUser> pageList = agentUserService.page(getPage());
        return ResultJson.success(pageList);
    }

    @ApiOperation("新增")
    @PostMapping("add")
    @PreAuthorize("hasAnyAuthority('system-agent-user-add')")
    public ResultJson add(AgentUserVO agentUserVO){
        agentUserService.add(agentUserVO);
        return ResultJson.success();
    }

    @ApiOperation("修改")
    @PostMapping("update")
    @PreAuthorize("hasAnyAuthority('system-agent-user-udpate')")
    public ResultJson update(AgentUserVO agentUserVO){
        agentUserService.update(agentUserVO);
        return ResultJson.success();
    }

    @ApiOperation("删除")
    @PostMapping("delete")
    @PreAuthorize("hasAnyAuthority('system-agent-user-delete')")
    public ResultJson delete(Integer id){
        agentUserService.removeById(id);
        return ResultJson.success();
    }

    private ResultJson getResultJson(AgentEnum agentEnum) {
        return ResultJson.result(agentEnum.getCode(), agentEnum.getMsg(), null);
    }
}
