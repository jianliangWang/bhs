package com.wjl.system.mapper;

import com.wjl.system.entity.SystemAuthorization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jay
 * @since 2022-04-02
 */
@Component
public interface SystemAuthorizationMapper extends BaseMapper<SystemAuthorization> {

    @Select("select count(1) from system_authorization WHERE parent_code = (SELECT `code` FROM system_authorization "
        + "WHERE id=${id})")
    int getChildCountByParentId(int id);
}
