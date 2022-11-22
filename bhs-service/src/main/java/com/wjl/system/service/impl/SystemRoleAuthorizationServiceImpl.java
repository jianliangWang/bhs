package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjl.system.entity.SystemRoleAuthorization;
import com.wjl.system.entity.extend.UserRoleAuth;
import com.wjl.system.mapper.SystemRoleAuthorizationMapper;
import com.wjl.system.mapper.SystemUserMapper;
import com.wjl.system.service.ISystemRoleAuthorizationService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jay
 * @since 2022-06-30
 */
@Service
public class SystemRoleAuthorizationServiceImpl extends ServiceImpl<SystemRoleAuthorizationMapper, SystemRoleAuthorization> implements ISystemRoleAuthorizationService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public void setRoleAuth(Integer id, Integer[] authIds) {
        this.remove(new QueryWrapper<SystemRoleAuthorization>().eq("role_id", id));
        List<SystemRoleAuthorization> roleAuthorizationList = new ArrayList<>();
        for (int i = 0; i < authIds.length; i++) {
            SystemRoleAuthorization systemRoleAuthorization = new SystemRoleAuthorization();
            systemRoleAuthorization.setRoleId(id);
            systemRoleAuthorization.setAuthorizationId(authIds[i]);
            roleAuthorizationList.add(systemRoleAuthorization);
        }
        this.saveBatch(roleAuthorizationList);
    }

    @Override
    public String getAuthorityInfo(int userId) {

        String authorities = "";
        List<UserRoleAuth> authorizationList = systemUserMapper.getNavMenu(userId);
        if (authorizationList == null || authorizationList.size() <= 0) {
            return authorities;
        }
        authorities = authorizationList.stream().map(auth -> "ROLE_" + auth.getRoleCode()).distinct()
            .collect(Collectors.joining(","));
        authorities = authorities.concat(",")
            .concat(authorizationList.stream().map(UserRoleAuth::getCode).collect(Collectors.joining(",")));
        return authorities;
    }
}
