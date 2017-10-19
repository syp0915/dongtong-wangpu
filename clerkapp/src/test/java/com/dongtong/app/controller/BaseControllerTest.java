package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.basic.query.AreaQuery;
import com.dongtong.basic.query.TagQuery;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-17 13:30
 **/
public class BaseControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testTagList() throws Exception{
        TagQuery query=new TagQuery();
        query.setTagType(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/base/tagList/"+VERSION)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


    }

    @Test
    public void testQueryArea() throws Exception{
        AreaQuery query=new AreaQuery();
        query.setCityId("310000");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/base/area/"+VERSION)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


    }

    @Test
    public void testIndustryList() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/base/industryList/"+VERSION)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString("{}")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


    }
}
