package com.wjl.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjl.enums.system.result.AgentEnum;
import com.wjl.system.entity.AgentUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.system.entity.vo.AgentUserVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 * 代理商用户; 服务类
 * </p>
 *
 * @author jay
 * @since 2022-12-05
 */
public interface IAgentUserService extends IService<AgentUser> {

    @Cacheable(value = "agentUser", unless = "#result == null")
    IPage<AgentUser> list(Page<AgentUser>page, AgentUser agentuser);

    @CacheEvict(value = "agentUser", allEntries = true)
    AgentEnum add(AgentUserVO agentUserVO);

    @CacheEvict(value = "agentUser", allEntries = true)
    AgentEnum update(AgentUserVO agentUserVO);

    AgentUser getById(Integer id);

    @CacheEvict(value = "agentUser", allEntries = true)
    int delete(Integer id);

}
