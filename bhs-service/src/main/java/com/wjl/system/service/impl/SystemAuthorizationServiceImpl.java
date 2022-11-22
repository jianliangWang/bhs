package com.wjl.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjl.consts.system.UserConsts;
import com.wjl.system.entity.SystemAuthorization;
import com.wjl.system.entity.extend.SystemAuthorizationExt;
import com.wjl.system.mapper.SystemAuthorizationMapper;
import com.wjl.system.service.ISystemAuthorizationService;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    private final Logger logger = LoggerFactory.getLogger(SystemAuthorizationServiceImpl.class);

    private final SystemAuthorizationMapper systemAuthorizationMapper;

    public SystemAuthorizationServiceImpl(SystemAuthorizationMapper systemAuthorizationMapper) {
        this.systemAuthorizationMapper = systemAuthorizationMapper;
    }

    @Override
    public List<SystemAuthorizationExt> menuTreeList(List<SystemAuthorization> systemAuthorizationList) {
        List<SystemAuthorizationExt> authorizationExtList = systemAuthorizationList.stream().map(item -> {
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

    @Override
    public SystemAuthorization getOneById(Integer id) {
        if (id == null || id == 0) {
            logger.error("通过id查询菜单信息出错，id为空！");
            return null;
        }
        return systemAuthorizationMapper.selectOne(new QueryWrapper<SystemAuthorization>().eq("id", id));
    }

    @Cacheable(value = "menuList", unless = "#result == null")
    @Override
    public List<SystemAuthorization> menuList() {
        return systemAuthorizationMapper.selectList(new QueryWrapper<>());
    }

    @CacheEvict(value = {"menuList", "listUserAuthMenus", "getAuthorityInfo"}, allEntries = true)
    @Override
    public boolean save(SystemAuthorization entity) {
        entity.setUpdateDate(LocalDateTime.now());
        entity.setCreateDate(LocalDateTime.now());
        return super.save(entity);
    }

    @CacheEvict(value = {"listUserAuthMenus", "menuList","getAuthorityInfo"}, allEntries = true)
    @Override
    public boolean updateById(SystemAuthorization entity) {
        entity.setUpdateDate(LocalDateTime.now());
        return super.updateById(entity);
    }

    @CacheEvict(value = {"menuList", "listUserAuthMenus", "getAuthorityInfo"}, allEntries = true)
    @Override
    public boolean removeById(Integer id) {
        if (id == null || id == 0) {
            logger.error("删除权限，id不能为空");
            return false;
        }
        /*查询是否有子菜单，有子菜单不允许删除*/
        int count = systemAuthorizationMapper.getChildCountByParentId(id);
        if (count > 0) {
            logger.error("删除权限，该权限存在子类不允许删除！");
            return false;
        }
        systemAuthorizationMapper.deleteById(id);
        return true;
    }
}
