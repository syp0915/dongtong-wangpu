package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.topic.query.TopicListQuery;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @description 生意圈相关
 * @package com.dongtong.app.controller
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/5/15 17:59
 * @version v1.0.0
 */
public class TopicControllerTest extends JunitBaseMockMvcTest {

    /**
     * @description 发布帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:12
     */
    @Test
    public void testPublishTopic0() throws Exception {
        String params = "{\"publishType\": \"1\",\"publisherType\": \"0\",\"title\": \"测试商户发帖\",\"content\": \"666666\",\"hyperLinkUrl\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/publishTopic/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 发布带图片帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:12
     */
    @Test
    public void testPublishTopic1() throws Exception {
        String params = "{\"publishType\": \"1\",\"publisherType\": \"1\",\"title\": \"测试商户发帖\",\"content\": \"666666\",\"hyperLinkUrl\":\"www.baidu.com\",\"imageList\":[\"www.baidu.com\",\"www.google.com.hk\"]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/publishTopic/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 置顶帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:13
     */
    @Test
    public void testStickTopic() throws Exception {
        String params = "{\"topicId\":23}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/stickTopic/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 取消置顶帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:13
     */
    @Test
    public void testUnStickTopic() throws Exception {
        String params = "{\"topicId\": 23}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/unStickTopic/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 点赞帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:13
     */
    @Test
    public void testLikeTopic() throws Exception {
        String params = "{\"topicId\": 24}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/likeTopic/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    public void testTopicList() throws Exception {
        TopicListQuery query = new TopicListQuery();
        query.setPageNumber(1);
        query.setPageSize(20);
        query.setType(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/topicList/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 取消点赞帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:13
     */
    @Test
    public void testUnLikeTopic() throws Exception {
        String params = "{\"likeId\": 8}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/unLikeTopic/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 删除帖子
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:13
     */
    @Test
    public void testDeleteTopic() throws Exception {
        String params = "{\"topicId\": 22}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/deleteTopic/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 发布评论
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:12
     */
    @Test
    public void testPublishComment() throws Exception {
        String params = "{\"topicId\":\"23\",\"content\":\"发布的评论\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/publishComment/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 删除评论
     * @package com.dongtong.app.controller
     * @author chenxs
     * @date 2017/5/16 9:13
     */
    @Test
    public void testDeleteComment() throws Exception {
        String params = "{\"commentId\": \"11\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/deleteComment/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    
    @Test
    public void testTopicDetail() throws Exception {
        JSONObject query = new JSONObject();
        query.put("topicId","2");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/topicDetail/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * @description 
     * @package com.dongtong.app.controller
     * @company dongtong
     * @copyright Copyright (c) 2016
     * @author chenxs
     * @date 2017/5/23 9:36
     * @version v1.0.0
     */
    @Test
    public void testCommentList() throws Exception {
        String query = "{\"pageNumber\":1,\"pageSize\":20,\"topicId\":1}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/topic/topicCommentList/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, "0CB9F815528983E3707F944A9113AADD")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(query))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
