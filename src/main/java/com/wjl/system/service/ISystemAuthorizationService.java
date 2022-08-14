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

    List<SystemAuthorizationExt> menuTreeList();

}
