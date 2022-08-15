package com.wjl.system;

import java.nio.charset.StandardCharsets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    private static String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY1MDMzMjQzNSwiZXhwIjoxNjUwMzM2MDM1fQ.PAZfCEAY1NxmHMrFhUiD8bRqfa0VRVmDg6513gl2aeks6Eq8ERSbIauNOOeUmbWYkgl-Yq1tCBBPtS5UKSFdCg";

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
}
