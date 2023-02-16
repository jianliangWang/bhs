package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edron.util.UserUtil;
import com.wjl.enums.system.result.AgentEnum;
import com.wjl.system.entity.AgentUser;
import com.wjl.system.entity.vo.AgentUserVO;
import com.wjl.system.mapper.AgentUserMapper;
import com.wjl.system.service.IAgentUserService;
import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 代理商用户; 服务实现类
 * </p>
 *
 * @author jay
 * @since 2022-12-05
 */
@Service
public class AgentUserServiceImpl extends ServiceImpl<AgentUserMapper, AgentUser> implements IAgentUserService {

    private final AgentUserMapper agentUserMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AgentUserServiceImpl(AgentUserMapper agentUserMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.agentUserMapper = agentUserMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public IPage<AgentUser> list(Page<AgentUser> page, AgentUser agentUser) {
        return agentUserMapper.selectPage(page,
            new QueryWrapper<AgentUser>().eq(StringUtils.hasText(agentUser.getAgentNumber()), "agent_number",
                agentUser.getAgentNumber()).eq(StringUtils.hasText(agentUser.getUsername()), "username",
                agentUser.getUsername()).eq(StringUtils.hasText(agentUser.getStatus()), "status",
                agentUser.getStatus()));
    }

    @Override
    public AgentEnum add(AgentUserVO agentUserVO) {
        String password = agentUserVO.getPassword();
        // TODO 这里需要对密码进行rsa进行解密
        // password = RSA.decode();
        agentUserVO.setPassword(bCryptPasswordEncoder.encode(password));
        // TODO 需要校验用户名是否已经存在
        // TODO 证件号码是否已经存在
        // TODO 手机号是否已经存在
        AgentUser agentUser = new AgentUser();
        BeanUtils.copyProperties(agentUserVO, agentUser);
        agentUser.setAmount(0L);
        agentUser.setBalance(0);
        // TODO 加密的余额
        //agentUser.setEncryptionBalance();
        agentUser.setCreateBy(UserUtil.getCurrentSystemUsername());
        agentUser.setCreateTime(LocalDateTime.now());
        if (agentUserMapper.insert(agentUser) < 1) {
            return AgentEnum.FAIL;
        }
        return AgentEnum.SUCCESS;
    }

    @Override
    public AgentEnum update(AgentUserVO agentUserVO) {
        // 这里不可以对密码进行修改
        if(agentUserVO.getPassword()!=null){
            log.error(AgentEnum.PASSWORD_NOT_ALLOW_UPDATE.getMsg());
            return AgentEnum.PASSWORD_NOT_ALLOW_UPDATE;
        }
        AgentUser agentUser = new AgentUser();
        BeanUtils.copyProperties(agentUserVO, agentUser);
        agentUser.setUpdateBy(UserUtil.getCurrentSystemUsername());
        agentUser.setUpdateTime(LocalDateTime.now());
        if (agentUserMapper.updateById(agentUser) < 1) {
            return AgentEnum.FAIL;
        }
        return AgentEnum.SUCCESS;
    }

    @Override
    public AgentUser getById(Integer id) {
        return agentUserMapper.selectById(id);
    }

    @Override
    public int delete(Integer id) {
        return agentUserMapper.deleteById(id);
    }
}
