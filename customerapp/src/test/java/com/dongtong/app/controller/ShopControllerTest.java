package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.shop.dto.ShopUndoDTO;
import com.dongtong.shop.query.BaseQuery;
import com.dongtong.shop.query.ShopCustomerQuery;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Package com.dongtong.app.controller.ShopControllerTest
 * @Description: ShopController
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/10 13:06
 * version V1.0.0
 */
public class ShopControllerTest extends JunitBaseMockMvcTest{

    @Test
    public void testQueryShop() throws Exception {
        ShopCustomerQuery query = new ShopCustomerQuery();
        query.setQueryType(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/queryShop/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testQueryMyShopScanCount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/queryMyShopScanCount/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testQueryMyPublishShop() throws Exception {
        BaseQuery query = new BaseQuery();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/queryMyPublishShop/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testUnDoShop() throws Exception {
        ShopUndoDTO dto = new ShopUndoDTO();
        dto.setShopId(1L);
        dto.setType(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/unDoShop/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testCountShopFromClient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/countShopFromClient/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testQueryShopDetail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/queryShopDetail/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"shopId\":\"184\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testCreateCorrect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/createCorrect/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"shopId\":\"3\",\"tagId\":\"14\",\"contacter\":\"zm\",\"contactMobile\":\"\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /*@Test
    public void testCreateVisit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/createVisit/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"shopId\":\"11\",\"visitTime\":\"1394508077\",\"linkmanName\":\"haha\",\"linkmanPhone\":\"17612164078\",\"province\":\"上海省\",\"city\":\"上海市\",\"district\":\"杨浦区\",\"address\":\"江湾镇\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }*/

    @Test
    public void testCreateCollected() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/createCollected/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"shopId\":\"4\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testCreateLiaisonRecord() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/createLiaisonRecord/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"shopId\":\"4\",\"phone\":\"17612164078\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testQueryShopImg() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/queryShopImg/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"shopId\":\"47\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 我收藏的商铺-排名
     * @throws Exception
     */
    @Test
    public void testMyCollectStatistic() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/myCollectStatistic/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 我收藏的商铺-商铺列表
     * @throws Exception
     */
    @Test
    public void testShopCollectedList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/shopCollectedList/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"rentList\":[1,2,6],\"transferList\":[1,2],\"areaList\":[1,2]}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    /**
     * 我收藏的商铺-取消收藏
     * @throws Exception
     */
    @Test
    public void testCancelShopCollected() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/cancelShopCollected/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"collectedId\":2}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    /**
     * 我浏览的商铺-排名
     * @throws Exception
     */
    @Test
    public void testMyBrowseStatistic() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/myBrowseStatistic/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 我浏览的商铺-商铺列表
     * @throws Exception
     */
    @Test
    public void testShopBrowseList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/shopBrowseList/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"rentList\":[1,2,6],\"transferList\":[1,2],\"areaList\":[1,2]}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    /**
     * 我浏览的商铺-删除浏览
     * @throws Exception
     */
    @Test
    public void testDeleteShopBrowse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/deleteShopBrowse/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"browseId\":2}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testgetShopId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/getShopId/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"shopCode\":\"shop002\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
