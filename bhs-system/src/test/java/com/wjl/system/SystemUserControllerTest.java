package com.wjl.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
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
public class SystemUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY2ODkzODczNSwiZXhwIjoxNjY4OTQyMzM1fQ.Woc40d-6nVK3fSu71jhikiG_wwRRamCdfBC0DcUcF9G8DezsF1kwVRzZGOdpPrOFA8m4ArqucYeH4yO2ULMXuA";

    @Test
    public void getLeftMenusTest() throws Exception {
        String res = mockMvc.perform(MockMvcRequestBuilders.get("/system/systemUser/getLeftMenus").header("authorization",
                token))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
            .getContentAsString(StandardCharsets.UTF_8);
        System.out.println(res);
    }

    @Test
    public void userListTest() throws Exception {
        String res = mockMvc.perform(
                MockMvcRequestBuilders.get("/system/user/list").header("authorization", token))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
            .getContentAsString(StandardCharsets.UTF_8);
        System.out.println(res);
    }

    @Test
    public void userAddTest() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("username", "zhangsan");
        String content = new ObjectMapper().writeValueAsString(map);
        String res =
            mockMvc.perform(MockMvcRequestBuilders.post("/system/user/add").contentType(MediaType.APPLICATION_JSON)
                    .content(content).header("authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        System.out.println(res);
    }
}
