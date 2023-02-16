package com.wjl.system.security;

import com.wjl.common.system.ResultJson;
import com.wjl.consts.system.CorsConsts;
import com.wjl.consts.system.UserConsts;
import com.wjl.system.utils.JWTUtil;
import com.wjl.system.utils.RedisUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends LoginBaseHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {
        String username = authentication.getName();
        String token = jwtUtil.generateToken(username);
        redisUtil.set(UserConsts.USER_TOKEN_REDIS_KEY_PRE + username, token, jwtUtil.getExpire());
        response.setHeader(CorsConsts.EXPOSED_HEADER, token);
        this.responseJson(response, ResultJson.success("登录成功", null));
    }
}
