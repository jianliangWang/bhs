package com.wjl.system;

import java.nio.charset.StandardCharsets;
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
public class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    protected final String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ"
        + ".eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY2ODkzODczNSwiZXhwIjoxNjY4OTQyMzM1fQ.Woc40d-6nVK3fSu71jhikiG_wwRRamCdfBC0DcUcF9G8DezsF1kwVRzZGOdpPrOFA8m4ArqucYeH4yO2ULMXuA";

    protected void postJson(String postUrl, String postContent) throws Exception {
        System.out.println(postContent);
        String res =
            mockMvc.perform(MockMvcRequestBuilders.post(postUrl).contentType(MediaType.APPLICATION_JSON)
                    .content(postContent).header("authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        System.out.println(res);
    }

    protected void postNoParam(String postUrl) throws Exception {
        String res =
            mockMvc.perform(MockMvcRequestBuilders.post(postUrl).header("authorization",
                    token))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        System.out.println(res);
    }
}
