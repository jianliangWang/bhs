package com.wjl.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjl.system.entity.vo.SystemBusinessDataVO;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BusinessDateTest extends BaseTest{

    @Test
    public void testList() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name","用户状态");
        paramMap.put("typeCode","ceshi");
        String content = new ObjectMapper().writeValueAsString(paramMap);
        postJson("/system/business/data/list", content);
    }

    @Test
    public void testAdd() throws Exception {
        SystemBusinessDataVO businessDataVO = new SystemBusinessDataVO();
        businessDataVO.setCode("stop");
        businessDataVO.setName("停用");
        businessDataVO.setTypeCode("ceshi");
        businessDataVO.setValue("0");
        businessDataVO.setSortId(1);
        businessDataVO.setStatus("1");
        businessDataVO.setRemark("这是备注");
        businessDataVO.setDescription("这是描述");
        String content = new ObjectMapper().writeValueAsString(businessDataVO);
        postJson("/system/business/data/add", content);
    }


    @Test
    public void testUpdate() throws Exception{
        SystemBusinessDataVO businessDataVO = new SystemBusinessDataVO();
        businessDataVO.setId(6);
        businessDataVO.setCode("qqqqq");
        businessDataVO.setName("不停也不启用");
        businessDataVO.setTypeCode("ceshi");
        businessDataVO.setValue("0");
        businessDataVO.setSortId(1);
        businessDataVO.setStatus("1");
        businessDataVO.setRemark("这是备注");
        businessDataVO.setDescription("这是描述");
        String content = new ObjectMapper().writeValueAsString(businessDataVO);
        postJson("/system/business/data/update", content);
    }

    @Test
    public void testDelete() throws Exception {

        getNoParam("/system/business/data/delete/8");
    }

    @Test
    public void testQueryById() throws Exception{
        String res =
            mockMvc.perform(MockMvcRequestBuilders.post("/system/business/data/getByTypeCode").param("typeCode",
                    "ceshi").header(
                "authorization",
                    token))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        System.out.println(res);
    }
}
