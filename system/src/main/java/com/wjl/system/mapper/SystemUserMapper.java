package com.wjl.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjl.system.entity.SystemUser;
import com.wjl.system.entity.extend.UserRoleAuth;
import java.util.List;
import org.apache.ibatis.annotations.Update;
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

    /**
     * 批量修改状态
     * @param ids
     * @param status
     * @return
     */
    @Update("<script>"
        + "update system_user set status=#{status} where id in "
        + "<foreach collection='ids' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach>"
        + "</script>")
    int updateStatusByIds(List<Integer> ids, String status);
}
