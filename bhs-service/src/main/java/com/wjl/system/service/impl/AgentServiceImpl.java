package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edron.util.UserUtil;
import com.wjl.enums.system.result.AgentEnum;
import com.wjl.system.entity.Agent;
import com.wjl.system.entity.vo.AgentVO;
import com.wjl.system.mapper.AgentMapper;
import com.wjl.system.service.IAgentService;
import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jay
 * @since 2022-12-05
 */
@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent> implements IAgentService {

    private final AgentMapper agentMapper;

    public AgentServiceImpl(AgentMapper agentMapper) {
        this.agentMapper = agentMapper;
    }

    @Override
    public IPage<Agent> list(Page<Agent> page, Agent agent) {
        return agentMapper.selectPage(page,
            new QueryWrapper<Agent>().like(StringUtils.hasText(agent.getName()), "name",
                agent.getName()).eq(StringUtils.hasText(agent.getAgentNumber()), "agent_number",
                agent.getAgentNumber()));
    }

    @Override
    public AgentEnum add(AgentVO agentVO) {
        // TODO 需要校验代理商名称是否已经存在
        // TODO 证件号码是否已经存在
        Agent agent = new Agent();
        BeanUtils.copyProperties(agentVO, agent);
        agent.setCreateTime(LocalDateTime.now());
        agent.setCreateBy(UserUtil.getCurrentSystemUsername());
        if (agentMapper.insert(agent) < 1) {
            return AgentEnum.FAIL;
        }
        return AgentEnum.SUCCESS;
    }

    @Override
    public AgentEnum update(AgentVO agentVO) {
        // TODO 需要校验代理商名称是否已经存在
        // TODO 证件号码是否已经存在
        assert agentVO.getId() != null;
        Agent agent = new Agent();
        BeanUtils.copyProperties(agentVO, agent);
        agent.setUpdateBy(UserUtil.getCurrentSystemUsername());
        agent.setUpdateTime(LocalDateTime.now());
        if (agentMapper.updateById(agent) < 1) {
            return AgentEnum.FAIL;
        }
        return AgentEnum.SUCCESS;
    }

    @Override
    public Agent getById(Integer id) {
        assert id != null;
        return agentMapper.selectById(id);
    }

    @Override
    public Agent getInfoByNumber(String agentNumber) {
        assert StringUtils.hasText(agentNumber);
        return agentMapper.selectOne(new QueryWrapper<Agent>().eq("agent_number", agentNumber));
    }

    @Override
    public int delete(Integer id) {
        assert id != null;
        return agentMapper.deleteById(id);
    }
}
