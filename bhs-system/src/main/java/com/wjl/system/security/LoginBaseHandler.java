package com.wjl.system.security;

import com.alibaba.fastjson2.JSON;
import com.wjl.common.system.ResultJson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class LoginBaseHandler {

    protected void responseJson(HttpServletResponse response, ResultJson result) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream servletOutputStream = response.getOutputStream();

        servletOutputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));

        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
