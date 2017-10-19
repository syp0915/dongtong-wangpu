package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.customer.query.*;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/12 下午1:51.
 */
public class ScheduleControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testGetScheduleListByUserId() throws Exception {
        ScheduleListReqQuery query = new ScheduleListReqQuery();
        query.setType(0);
        query.setPageNumber(1);
        query.setPageSize(10);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/schedule/list/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetScheduleInfoById() throws Exception {
        ScheduleDetailReqQuery query = new ScheduleDetailReqQuery();
        query.setScheduleId(2L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/schedule/detail/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetCalendarScheduleList() throws Exception {
        CalendarScheduleReqQuery query = new CalendarScheduleReqQuery();
        query.setPreDayCount(3);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/schedule/calendar/list/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testRevokeSchedule() throws Exception {
        ScheduleRevokeQuery query = new ScheduleRevokeQuery();
        query.setScheduleId(2L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/schedule/revoke/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testEnsureSchedule() throws Exception {
        ScheduleEnsureQuery query = new ScheduleEnsureQuery();
        query.setScheduleId(2L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/schedule/ensure/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


}
