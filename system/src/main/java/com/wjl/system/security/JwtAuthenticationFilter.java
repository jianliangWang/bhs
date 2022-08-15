package com.wjl.system.security;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.wjl.common.ResultJson;
import com.wjl.consts.CorsConsts;
import com.wjl.consts.UserConsts;
import com.wjl.system.entity.SystemUser;
import com.wjl.system.service.ISystemUserService;
import com.wjl.system.utils.JWTUtil;
import com.wjl.system.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetail;

    @Autowired
    private ISystemUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        String jwt = request.getHeader(CorsConsts.EXPOSED_HEADER);
        if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request, response);
            return;
        }
        Claims claims = jwtUtil.getClaimsByToken(jwt);
        if (claims == null) {
            throw new JwtException("token 异常");
        }

        String username = claims.getSubject();
        if (!redisUtil.hasKey(UserConsts.USER_TOKEN_REDIS_KEY_PRE + username)) {
            this.responseMsg(response, ResultJson.fail("token过期，请重新登录"));
            return;
        }
        redisUtil.set(UserConsts.USER_TOKEN_REDIS_KEY_PRE + username, jwt, jwtUtil.getExpire());

        // 通过username获取user
        SystemUser systemUser = userService.getSystemUserByUsername(username);
        if (ObjectUtils.isNull(systemUser)) {
            throw new RuntimeException("用户未登录");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null,
            userDetail.getUserGrantedAuthority(systemUser.getId()));
        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }

    public void responseMsg(HttpServletResponse response, ResultJson result) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream servletOutputStream = response.getOutputStream();

        servletOutputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));

        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
