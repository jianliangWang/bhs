package com.wjl.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjl.system.entity.SystemAuthorization;
import com.wjl.system.entity.extend.SystemAuthorizationExt;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
public interface ISystemAuthorizationService extends IService<SystemAuthorization> {

    /**
     * 组装树形菜单格式的list，并返回，因为不能直接将树形结构的数据缓存到redis中，所以和查询数据库分为了两个方法，后面如果找到了合适到方法，这两个方法可以合并
     * @return
     */
    List<SystemAuthorizationExt> menuTreeList(List<SystemAuthorization> systemAuthorizationList);

    /**
     * 通过id获取菜单详细信息
     * @param id
     * @return
     */
    SystemAuthorization getOneById(Integer id);

    boolean updateById(SystemAuthorization systemAuthorization);

    /**
     * 为上面方法服务，这个主要是查询所有到权限信息
     * @return
     */
    List<SystemAuthorization> menuList();

    /**
     * 删除菜单，通过id删除
     * 如果有自子菜单删除失败
     * @param id
     * @return
     */
    boolean removeById(Integer id);

}
