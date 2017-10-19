package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.basic.dto.req.BaseNoticeReqDTO;
import com.dongtong.basic.query.NoticeQuery;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author sunyaping
 * @Package com.dongtong.app
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-15 15:59
 * version V1.0.0
 **/
public class NoticeControllerTest extends JunitBaseMockMvcTest {

    @Test
    public void testIsUnRead()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/notice/isUnRead/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetNoticeList()throws Exception{
        BaseNoticeReqDTO baseNoticeDTO=new BaseNoticeReqDTO();
        baseNoticeDTO.setReceiveType(0);
        baseNoticeDTO.setReceiveId(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/notice/getNoticeList/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(baseNoticeDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testNoticeByTypeList()throws Exception{
        NoticeQuery noticeQuery=new NoticeQuery();
        noticeQuery.setReceiverId(1L);
        noticeQuery.setNotifyType(2);
        noticeQuery.setReceiverType(1);
        noticeQuery.setPageNumber(1);
        noticeQuery.setPageSize(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/notice/getNoticeByTypeList/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(noticeQuery)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


}
