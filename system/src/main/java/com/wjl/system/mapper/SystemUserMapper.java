package com.wjl.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjl.system.entity.SystemUser;
import com.wjl.system.entity.extend.UserRoleAuth;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    List<UserRoleAuth> getNavMenu(int userId);
}
