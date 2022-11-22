package com.wjl.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjl.system.entity.vo.SystemBusinessTypeVO;
import org.junit.Test;

public class BusinessTypeTest extends BaseTest {

    @Test
    public void testLists() throws Exception {
        postNoParam("/system/business/type/list");
    }

    @Test
    public void testAdd() throws Exception {
        SystemBusinessTypeVO businessTypeVO = new SystemBusinessTypeVO();
        businessTypeVO.setCode("001");
        businessTypeVO.setName("这是一个类型");
        businessTypeVO.setRemark("这是备注");
        businessTypeVO.setDescription("这是描述");
        String content = new ObjectMapper().writeValueAsString(businessTypeVO);
        postJson("/system/business/type/add", content);
    }

    @Test
    public void testQueryById() throws Exception {
        postNoParam("/system/business/type/getById/1");
    }

    @Test
    public void testUpdate() throws Exception {
        SystemBusinessTypeVO businessTypeVO = new SystemBusinessTypeVO();
        businessTypeVO.setId(2);
        businessTypeVO.setCode("");
        businessTypeVO.setName("第二个分类");
        businessTypeVO.setRemark("这是备注");
        businessTypeVO.setDescription("这是描述");
        String content = new ObjectMapper().writeValueAsString(businessTypeVO);
        postJson("/system/business/type/update", content);
    }

    @Test
    public void testDelete() throws Exception {
        postNoParam("/system/business/type/delete/2");
    }
}
