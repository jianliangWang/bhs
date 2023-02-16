package com.wjl.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjl.enums.system.result.AgentEnum;
import com.wjl.system.entity.Agent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.system.entity.vo.AgentVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jay
 * @since 2022-12-05
 */
public interface IAgentService extends IService<Agent> {

    /**
     * 分页列表
     * @param page
     * @param agent
     * @return
     */
    @Cacheable(value = "agent", unless = "#result == null")
    IPage<Agent> list(Page<Agent>page, Agent agent);

    /**
     * 新增代理商
     * @param agentVO
     * @return
     */
    @CacheEvict(value = "agent", allEntries = true)
    AgentEnum add(AgentVO agentVO);

    /**
     * 修改代理商
     * @param agentVO
     * @return 是否修改成功
     */
    @CacheEvict(value = "agent", allEntries = true)
    AgentEnum update(AgentVO agentVO);

    /**
     * 通过id获取代理商
     * @param id 代理商id
     * @return 代理商详细信息
     */
    Agent getById(Integer id);

    /**
     * 通过代理商编号获取代理商信息
     * @param agentNumber 代理商编号
     * @return 代理商详细信息
     */
    Agent getInfoByNumber(String agentNumber);

    /**
     * 删除代理商
     * @param id
     * @return
     */
    @CacheEvict(value = "agent", allEntries = true)
    int delete(Integer id);

}
