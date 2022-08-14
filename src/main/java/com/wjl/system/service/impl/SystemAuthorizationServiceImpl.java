package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjl.consts.UserConsts;
import com.wjl.system.entity.SystemAuthorization;
import com.wjl.system.entity.extend.SystemAuthorizationExt;
import com.wjl.system.mapper.SystemAuthorizationMapper;
import com.wjl.system.service.ISystemAuthorizationService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
@Service
public class SystemAuthorizationServiceImpl extends
    ServiceImpl<SystemAuthorizationMapper, SystemAuthorization> implements ISystemAuthorizationService {

    @Autowired
    private SystemAuthorizationMapper systemAuthorizationMapper;

    @Override
    public List<SystemAuthorizationExt> menuTreeList() {
        List<SystemAuthorization> authorizationList = systemAuthorizationMapper.selectList(new QueryWrapper<>());

        List<SystemAuthorizationExt> authorizationExtList = authorizationList.stream().map(item -> {
            SystemAuthorizationExt authorizationExt = new SystemAuthorizationExt();
            BeanUtils.copyProperties(item, authorizationExt);
            return authorizationExt;
        }).toList();
        return authorizationExtList.parallelStream()
            .map(item -> {
                List<SystemAuthorizationExt> children =
                    authorizationExtList.stream().filter(ext -> item.getCode().equals(ext.getParentCode())).toList();
                item.setChildren(children);
                return item;
            }).filter(item -> item.getParentCode().equals(UserConsts.SYSTEM_MENU_ROOT_CODE)).toList();
    }
}
