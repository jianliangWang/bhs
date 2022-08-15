package com.wjl.system;

import cn.hutool.json.JSON;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.wjl.system.entity.SystemAuthorization;
import java.nio.charset.StandardCharsets;
import org.apache.tomcat.util.http.parser.Authorization;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class SystemAuthorizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY1MDMzMjQzNSwiZXhwIjoxNjUwMzM2MDM1fQ.PAZfCEAY1NxmHMrFhUiD8bRqfa0VRVmDg6513gl2aeks6Eq8ERSbIauNOOeUmbWYkgl-Yq1tCBBPtS5UKSFdCg";

    @Test
    public void authorizationListTest() throws Exception {
        String res = mockMvc.perform(
                MockMvcRequestBuilders.get("/system/authorization/list").header("authorization", token))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
            .getContentAsString(StandardCharsets.UTF_8);
        System.out.println(res);
    }

    @Test
    public void authorizationSaveTest() throws Exception {
        SystemAuthorization authorization = new SystemAuthorization();
        authorization.setCode("system-test");
        authorization.setName("测试");
        authorization.setParentCode("system");
        authorization.setLabel("测试");
        authorization.setType("Menu");
        authorization.setUrl("");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(authorization);
        String res = mockMvc.perform(
                MockMvcRequestBuilders.post("/system/authorization/save").contentType(MediaType.APPLICATION_JSON)
                    .content(jsonStr).header("authorization",
                    token))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
            .getContentAsString(StandardCharsets.UTF_8);
        System.out.println(res);
    }

    @Test
    public void authorizationDeleteTest() throws Exception {
        String res = mockMvc.perform(
            MockMvcRequestBuilders.get("/system/authorization/delete/30").header("authorization", token)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        System.out.println(res);
    }

    @Test
    public void authorizationGetInfoTest() throws Exception {
        String res = mockMvc.perform(
            MockMvcRequestBuilders.get("/system/authorization/getInfo/29").header("authorization", token)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        System.out.println(res);
    }
}
