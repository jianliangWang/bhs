package com.wjl.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edron.util.UserUtil;
import com.wjl.common.system.ResultJson;
import com.wjl.system.entity.vo.AgentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjl.system.entity.Agent;
import com.wjl.system.service.IAgentService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jay
 * @since 2022-12-05
 */
@Api(tags = "代理商")
@RestController
@RequestMapping("/system/agent")
public class AgentController extends  BaseController<Agent>  {

    private final Logger logger = LoggerFactory.getLogger(AgentController.class);

    private final IAgentService agentService;

    public AgentController(IAgentService agentService) {
        this.agentService = agentService;
    }

    @ApiOperation("列表")
    @PostMapping("list")
    @PreAuthorize("hasAnyAuthority('system-agent-list')")
    public ResultJson list(@RequestBody Agent agent){
        logger.debug(UserUtil.getCurrentSystemUsername());
        IPage<Agent> pageList = agentService.list(getPage(), agent);
        return ResultJson.success(pageList);
    }

    @ApiOperation("新增")
    @PostMapping("add")
    @PreAuthorize("hasAnyAuthority('system-agent-add')")
    public ResultJson add(@Validated @RequestBody AgentVO agentVO){
        agentService.add(agentVO);
        return ResultJson.success();
    }

    @ApiOperation("通过id获取信息")
    @PostMapping("/getInfo/{id}")
    @PreAuthorize("hasAnyAuthority('system-add-getInfo')")
    public ResultJson getInfoById(@PathVariable("id") Integer id){

        Agent returnAgent = agentService.getById(id);
        return ResultJson.success(returnAgent);
    }

    @ApiOperation("修改")
    @PostMapping("update")
    @PreAuthorize("hasAnyAuthority('system-agent-udpate')")
    public ResultJson update(@Validated @RequestBody Agent agent){
        agentService.updateById(agent);
        return ResultJson.success();
    }

    @ApiOperation("删除")
    @PostMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('system-agent-delete')")
    public ResultJson delete(@PathVariable("id") Integer id){
        agentService.removeById(id);
        return ResultJson.success();
    }
}
