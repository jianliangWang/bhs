package com.wjl.system.security;

import com.wjl.common.ResultJson;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler extends LoginBaseHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException {

        this.responseJson(response, ResultJson.fail(HttpServletResponse.SC_FORBIDDEN, "未授权，请联系管理员"));
    }
}
