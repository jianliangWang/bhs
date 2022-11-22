package com.wjl.system.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjl.system.entity.SystemUser;
import com.wjl.system.service.ISystemRoleAuthorizationService;
import com.wjl.system.service.ISystemUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISystemUserService userService;

    @Autowired
    private ISystemRoleAuthorizationService roleAuthorizationService;

    // 认证
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user= userService.getOne(new QueryWrapper<SystemUser>().eq("username", username));
        if(null == user){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new User(username, user.getPassword(), getUserGrantedAuthority(user.getId()));
    }

    // 鉴权
    public List<GrantedAuthority> getUserGrantedAuthority(int userId){
        String commaAuthority = roleAuthorizationService.getAuthorityInfo(userId);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(commaAuthority);
    }
}
