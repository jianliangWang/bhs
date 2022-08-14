package com.wjl.system.security;

import cn.hutool.json.JSONUtil;
import com.wjl.common.ResultJson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class LoginBaseHandler {

    protected void responseJson(HttpServletResponse response, ResultJson result) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream servletOutputStream = response.getOutputStream();

        servletOutputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));

        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
