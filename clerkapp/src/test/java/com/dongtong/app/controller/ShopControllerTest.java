package com.dongtong.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.JunitBaseMockMvcTest;
import com.dongtong.shop.dto.IssueShopDTO;
import com.dongtong.shop.dto.ShopImgDTO;
import com.dongtong.shop.dto.ShopNearDTO;
import com.dongtong.shop.dto.ShopUndoDTO;
import com.dongtong.shop.query.ShopClerkQuery;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

        ShopClerkQuery query = new ShopClerkQuery();
        query.setShopType(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/queryClerkShop/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testUpShop() throws Exception {

        JSONObject object = new JSONObject();
        object.put("shopId", 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/upShop/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(object.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testDownShop() throws Exception {

        ShopUndoDTO dto = new ShopUndoDTO();
        dto.setShopId(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/downShop/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void testCountShopFromService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/countShopFromService/" + VERSION)
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
                .content("{\"shopId\":\"2\"}"))
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


    @Test
    public void testIssueShop() throws Exception {
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setHintId(159L);
        shopDTO.setAddress("武东路2013号");
        shopDTO.setRentType(0);
        shopDTO.setRentStatus(1);//出租状态默认出租中
        shopDTO.setContacter("张吴都");
        shopDTO.setContactTel("13889899989");
        shopDTO.setShopName("财大科技园");
        shopDTO.setLongitude("121.563724");
        shopDTO.setLatitude("31.208911");
        shopDTO.setDistrictId(1L);
        shopDTO.setDistrictName("杨浦区");
        shopDTO.setBlockId(2L);
        shopDTO.setBlockName("江湾镇板块");
        shopDTO.setFloor("3");
        shopDTO.setIsShow(0);
        shopDTO.setTotalFloor("10");
        shopDTO.setArea(100F);
        shopDTO.setWidth(5.9F);
        shopDTO.setDepth(8955F);
        shopDTO.setHeight(5655F);
        List<ShopImgDTO> shopImgList = new ArrayList<>();//商铺图片
        for(int i=0;i<5;i++){
            ShopImgDTO shopImg = new ShopImgDTO();
            shopImg.setImgUrl("http://test"+i+".jpg");
            if(i==3){
                shopImg.setIsCover(1);
            }else {
                shopImg.setIsCover(0);
            }
            shopImg.setImgIndex(i);
            shopImgList.add(shopImg);
        }
        shopDTO.setShopImgList(shopImgList);
        List<Long> operateRel = new ArrayList<>();//适合经营
        for(int i=0;i<3;i++){
            Long id = Long.parseLong(String.valueOf(i));
            operateRel.add(id);
        }
        shopDTO.setOperateRel(operateRel);
        List<Long> support = new ArrayList<>();
        for(int i=0;i<4;i++){
            Long id = Long.parseLong(String.valueOf(i));
            support.add(id);
        }
        shopDTO.setSupport(support);
        shopDTO.setShopCode("0023");
        shopDTO.setOperateStatus(1);
        shopDTO.setShopName("财大饭店");
        shopDTO.setIndustryId(3L);
        shopDTO.setRentWay(1);
        shopDTO.setRent(new BigDecimal("50000000"));
        shopDTO.setTransferFee(new BigDecimal("80000"));
        shopDTO.setCompactResidue(4);
        shopDTO.setDeposit(new BigDecimal("3000"));

        List<Long> feature = new ArrayList<>();//特色
        for(int i=0;i<4;i++){
            Long id = Long.parseLong(String.valueOf(i));
            feature.add(id);
        }
        shopDTO.setFeature(feature);
        //临铺信息
        ShopNearDTO nearLeftOne = new ShopNearDTO();
        nearLeftOne.setName("左一临铺");
        nearLeftOne.setIndustryId(2L);
        List<ShopImgDTO> nearImgList = new ArrayList<>();//临铺图片
        for(int i=0;i<3;i++){
            ShopImgDTO shopImg = new ShopImgDTO();
            shopImg.setImgUrl("http://linpu"+i+".jpg");
            if(i==2){
                shopImg.setIsCover(1);
            }else {
                shopImg.setIsCover(0);
            }
            shopImg.setImgIndex(i);
            nearImgList.add(shopImg);
        }
        nearLeftOne.setNearImg(nearImgList);
        shopDTO.setNearLeftOne(nearLeftOne);//临铺左一

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/issueShop/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(shopDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 修改商铺-租售信息
     * @throws Exception
     */
    @Test
    public void testUpdateShopRent() throws Exception {
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(12L);
        shopDTO.setRentType(1);
        shopDTO.setRentStatus(2);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/updateShopRent/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(shopDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 修改商铺-客户信息
     * @throws Exception
     */
    @Test
    public void testUpdateShopContacter() throws Exception {
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(13L);
        shopDTO.setContacter("张一号");
        shopDTO.setContactTel("1381561310");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/updateShopContacter/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(shopDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    /**
     * 修改商铺-经营状况信息
     * @throws Exception
     */
    @Test
    public void testUpdateShopOperateState() throws Exception {
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(1L);
        shopDTO.setOperateStatus(1);
        shopDTO.setShopName("杨浦第一店面");
        shopDTO.setIndustryId(11L);
        List<Long> feature = new ArrayList<>();
        feature.add(3L);
        feature.add(4L);
        shopDTO.setFeature(feature);
        List<ShopImgDTO> shopImgList = new ArrayList<>();//商铺图片
        for(int i=0;i<3;i++){
            ShopImgDTO shopImg = new ShopImgDTO();
            shopImg.setImgUrl("http://update "+shopDTO.getShopId()+".jpg");
            if(i==2){
                shopImg.setIsCover(1);
            }else {
                shopImg.setIsCover(0);
            }
            shopImg.setImgIndex(i);
            shopImgList.add(shopImg);
        }
        shopDTO.setShopImgList(shopImgList);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/updateShopOperateState/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(shopDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    /**
     * 修改商铺-租赁相关的信息
     * @throws Exception
     */
    @Test
    public void testUpdateShopRentParam() throws Exception {
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(46L);
        shopDTO.setRent(new BigDecimal("5000"));
        shopDTO.setTransferFee(new BigDecimal("20000"));
        shopDTO.setCompactResidue(10);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/updateShopRentParam/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(shopDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    /**
     * 修改商铺-临铺信息
     * @throws Exception
     */
    @Test
    public void testUpdateShopNear() throws Exception {
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(1L);
        //左一
        ShopNearDTO nearLeftOne = new ShopNearDTO();
        nearLeftOne.setName("update临铺左1");
        nearLeftOne.setIndustryId(3L);
        nearLeftOne.setNearId(5L);
        List<ShopImgDTO> shopImgList = new ArrayList<>();//商铺图片
        for(int i=0;i<3;i++){
            ShopImgDTO shopImg = new ShopImgDTO();
            shopImg.setImgUrl("http://update "+shopDTO.getShopId()+".jpg");
            if(i==2){
                shopImg.setIsCover(1);
            }else {
                shopImg.setIsCover(0);
            }
            shopImg.setImgIndex(i);
            shopImgList.add(shopImg);
        }
        nearLeftOne.setNearImg(shopImgList);
        shopDTO.setNearLeftOne(nearLeftOne);
        //左二
        ShopNearDTO nearLeftTwo = new ShopNearDTO();
        nearLeftTwo.setName("update临铺左2");
        nearLeftTwo.setIndustryId(3L);
        //nearLeftTwo.setNearId(3L);
        List<ShopImgDTO> leftTwoImg = new ArrayList<>();//商铺图片
        for(int i=0;i<3;i++){
            ShopImgDTO shopImg = new ShopImgDTO();
            shopImg.setImgUrl("http://add "+shopDTO.getShopId()+".jpg");
            if(i==2){
                shopImg.setIsCover(1);
            }else {
                shopImg.setIsCover(0);
            }
            shopImg.setImgIndex(i);
            leftTwoImg.add(shopImg);
        }
        nearLeftTwo.setNearImg(leftTwoImg);
        shopDTO.setNearLeftTwo(nearLeftTwo);
        //右一
        ShopNearDTO nearRightOne = new ShopNearDTO();
        nearRightOne.setName("update临铺右1");
        nearRightOne.setIndustryId(3L);
        nearRightOne.setNearId(6L);
        nearRightOne.setNearImg(shopImgList);
        shopDTO.setNearRightOne(nearRightOne);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/updateShopNear/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(shopDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    /**
     * 修改商铺-删除临铺
     * @throws Exception
     */
    @Test
    public void testDelShopNear() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/delShopNear/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"nearId\":\"47\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    /**
     * 修改商铺-查看临铺照片
     * @throws Exception
     */
    @Test
    public void testShopNearImg() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/shopNearImg/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"nearId\":\"47\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 认领商铺
     * @throws Exception
     */
    @Test
    public void testClaimShop() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/claimShop/" + VERSION)
                .header(DEFAULT_TOKEN_NAME, tokenKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"shopId\":\"\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
