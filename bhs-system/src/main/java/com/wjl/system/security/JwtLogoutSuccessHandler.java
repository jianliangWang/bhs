package com.wjl.system.security;

import com.wjl.common.system.ResultJson;
import com.wjl.consts.system.UserConsts;
import com.wjl.system.utils.JWTUtil;
import com.wjl.system.utils.RedisUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtLogoutSuccessHandler extends LoginBaseHandler implements LogoutSuccessHandler {

    private final JWTUtil jwtUtil;

    private final RedisUtil redisUtil;

    public JwtLogoutSuccessHandler(JWTUtil jwtUtil, RedisUtil redisUtil) {
        this.jwtUtil = jwtUtil;
        this.redisUtil = redisUtil;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        // 将缓存的权限删除
        response.setHeader(jwtUtil.getHeader(), null);
        redisUtil.del(UserConsts.USER_TOKEN_REDIS_KEY_PRE + authentication);
        this.responseJson(response, ResultJson.success());
    }
}
