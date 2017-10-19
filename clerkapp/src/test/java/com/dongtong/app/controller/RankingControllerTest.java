package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.basic.query.AllRankingQuery;
import com.dongtong.basic.query.MyRankingQuery;
import com.dongtong.basic.query.RankingQuery;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Package com.dongtong.app.controller.RankingControllerTest
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/17 16:23
 * version V1.0.0
 */
public class RankingControllerTest extends JunitBaseMockMvcTest {
    @Test
    public void testAllRankByType() throws Exception{
        AllRankingQuery query=new AllRankingQuery();
        query.setType(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ranking/allRankByType/"+VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


    }

    @Test
    public void testSelectRanking() throws Exception{
        RankingQuery query=new RankingQuery();
        query.setType(1);
        query.setClassify(1);
        query.setPageSize(20);
        query.setPageNumber(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ranking/queryRank/"+VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


    }

    @Test
    public void testMyRanking() throws Exception{
        MyRankingQuery query=new MyRankingQuery();
        query.setType(1);
        query.setClassify(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ranking/myRanking/"+VERSION)
                .header(DEFAULT_TOKEN_NAME, "00A7FBCB57F9E3DC2CF70A44BC2547F0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


    }
}
