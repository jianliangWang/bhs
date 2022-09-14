package com.wjl.system.security;

import com.wjl.common.CaptchaException;
import com.wjl.consts.CaptchaConsts;
import com.wjl.system.utils.RedisUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String url = request.getRequestURI();
        if (url.equals("/login") && request.getMethod().equals("POST")) {
            try {
                validateCaptcha(request);
            } catch (CaptchaException e) {
                loginFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validateCaptcha(HttpServletRequest request) throws CaptchaException, IOException {
        String key = request.getParameter(CaptchaConsts.CAPTCHA_KEY);
        String code = request.getParameter(CaptchaConsts.CAPTCHA_VALUE);
        if (!StringUtils.hasText(code) || !StringUtils.hasText(key)) {
            redisUtil.del(key);
            throw new CaptchaException("验证码错误");
        }
        if (!code.equals(redisUtil.get(key))) {
            redisUtil.del(key);
            throw new CaptchaException("验证码错误");
        }
        redisUtil.del(key);
    }

}
