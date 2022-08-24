package com.wjl.system;

import com.wjl.system.service.ISystemUserService;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.wjl.system.utils.SignUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class LoginTests {

    @Autowired
    ISystemUserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testServiceList() {
        int count = userService.list().size();
        Assert.assertEquals(1, count);
    }

    @Test
    public void testGetUserAuthorityInfo() {
        userService.getAuthorityInfo(1);
    }

    @Test
    public void testGetCaptcha() throws Exception {
        String res = mockMvc.perform(MockMvcRequestBuilders.get("/captcha"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString(
                StandardCharsets.UTF_8);
        System.out.println(res);
    }

    @Test
    public void testLogin() throws Exception {
        String username = "admin";
        String password = "111111";
        String verifyCode = "gpxfc";
        String key = "7d445937-e374-4567-9320-3b86590a8c48";
        MockHttpServletResponse res = mockMvc.perform(MockMvcRequestBuilders.post("/login").param("username",
                    username)
                .param("password", password).param("verifyCode", verifyCode)
                .param("key", key))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();
        System.out.println(res.getContentAsString());
        System.out.println(res.getHeader("authorization"));
    }

    @Test
    public void bcryptPassword() {
        String encodePassword = bCryptPasswordEncoder.encode("111111");
        boolean result = bCryptPasswordEncoder.matches("111111", "$2a$10$9boJSs5ejdprhfczWgYgU.HaLWGF.kM09.slelG0HQSc3."
            + ".1bGK8W");
        System.out.println("匹配结果：" + result);
        System.out.println("加密结果" + encodePassword);
    }

    @Test
    public void testLogout() throws Exception {
        String username = "guest";
        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJndWVzdCIsImlhdCI6MTY0OTIzMTY2NSwiZXhwIjoxNjQ5MzAzNjY1fQ.WY-fKXA9QZb-F4nSGASiHwjUxtAgjegn257J76Z-aXbjMhN_hzPmEpLT5-wZGVfJztGvUjWn2bjnc8I-j7WkeA";

        String res = mockMvc.perform(MockMvcRequestBuilders.post("/logout").param("username", username)
            .header("authorization", token))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(res);

        Map<String, String> map = new HashMap<>();
        map.put("username","zhangsan");
        System.out.println(map);

    }
}
