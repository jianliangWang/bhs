package com.wjl.system.security;

import com.wjl.common.ResultJson;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler extends LoginBaseHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException {
        if(exception.getMessage().equals("Bad credentials")){
            this.responseJson(response, ResultJson.fail("用户名或密码错误"));
        }else {
            this.responseJson(response, ResultJson.fail(exception.getMessage()));
        }
    }
}
