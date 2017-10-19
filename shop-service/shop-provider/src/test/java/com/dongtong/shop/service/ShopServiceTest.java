package com.dongtong.shop.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.shop.JunitBaseTest;
import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.enums.UndoType;
import com.dongtong.shop.manager.ShopManager;
import com.dongtong.shop.query.*;
import com.shfc.common.base.Logger;
import com.shfc.common.base.RegexUtils;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Package com.dongtong.shop.service.ShopServiceTest
 * @Description: 测试类
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/5 13:36
 * version V1.0.0
 */
public class ShopServiceTest extends JunitBaseTest {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopManager shopManager;
    @Test
    public void addShop(){
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setClerkId(2L);
        shopDTO.setAddress("武东路2010号");
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
        shopDTO.setIsShow(0);
        shopDTO.setFloor("-4");
        shopDTO.setTotalFloor("10");
        shopDTO.setArea(10226F);
        shopDTO.setWidth(5.93F);
        shopDTO.setDepth(343444F);
        shopDTO.setHeight(999.988F);
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
        shopDTO.setShopCode("000024");
        shopDTO.setOperateStatus(1);
        shopDTO.setShopName("财大饭店");
        shopDTO.setIndustryId(3L);
        shopDTO.setRentWay(1);
        shopDTO.setRent(new BigDecimal("5000"));
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
        ResultDO<Long> resultDO =  shopService.issueShop(shopDTO);
        if(!resultDO.isSuccess()){
            Logger.info(ShopServiceTest.class,resultDO.getErrMsg());
        }
        //System.out.println(resultDO.getData());
        Logger.info(ShopServiceTest.class,String.valueOf(resultDO.getData()));
    }

    @Test
    public void updateShopRent(){
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(46L);
        shopDTO.setRentType(1);
        shopDTO.setRentStatus(2);
        ResultDO<Boolean> resultDO = shopService.updateShopRent(shopDTO);
        if(resultDO.isSuccess()){
            Boolean result  = resultDO.getData();
            System.out.println(result);
        }else {
            System.out.println(resultDO.getErrMsg());
        }
    }
    @Test
    public void updateShopContacter(){
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(13L);
        shopDTO.setContacter("张一号");
        shopDTO.setContactTel("1381561310");
        ResultDO<Boolean> resultDO = shopService.updateShopContacter(shopDTO);
        if(resultDO.isSuccess()){
            Boolean result  = resultDO.getData();
            System.out.println(result);
        }else {
            System.out.println(resultDO.getErrMsg());
        }
    }
    @Test
    public void updateShopOperateState(){
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(46L);
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
        ResultDO<Boolean> resultDO = shopService.updateShopOperateState(shopDTO);
        if(resultDO.isSuccess()){
            Boolean result  = resultDO.getData();
            System.out.println(result);
        }else {
            System.out.println(resultDO.getErrMsg());
        }
    }
    @Test
    public void updateShopRentParam(){
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(46L);
        shopDTO.setRent(new BigDecimal("5000"));
        shopDTO.setTransferFee(new BigDecimal("20000"));
        shopDTO.setCompactResidue(10);
        ResultDO<Boolean> resultDO = shopService.updateShopRentParam(shopDTO);
        if(resultDO.isSuccess()){
            Boolean result  = resultDO.getData();
            System.out.println(result);
        }else {
            System.out.println(resultDO.getErrMsg());
        }
    }

    @Test
    public void updateShopService(){
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(196L);
        //shopDTO.setWaterRate(null);//水费
        ResultDO<Boolean> resultDO = shopService.updateShopService(shopDTO);
        if(resultDO.isSuccess()){
            Boolean result  = resultDO.getData();
            System.out.println(result);
        }else {
            System.out.println(resultDO.getErrMsg());
        }
    }
    @Test
    public void updateShopSupport(){
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(198L);
        shopDTO.setSupport(Arrays.asList(new Long[]{1L,2L,3L,4L}));//水费
        ResultDO<Boolean> resultDO = shopService.updateShopSupport(shopDTO);
        if(resultDO.isSuccess()){
            Boolean result  = resultDO.getData();
            System.out.println(result);
        }else {
            System.out.println(resultDO.getErrMsg());
        }
    }

    @Test
    public void updateShopNear(){
        IssueShopDTO shopDTO = new IssueShopDTO();
        shopDTO.setShopId(196L);
        //左一
       /* ShopNearDTO nearLeftOne = new ShopNearDTO();
        nearLeftOne.setName("update临铺左1");
        nearLeftOne.setIndustryId(3L);
        nearLeftOne.setNearId(30L);
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
        shopDTO.setNearLeftOne(nearLeftOne);*/
        //左二
        /*ShopNearDTO nearLeftTwo = new ShopNearDTO();
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
        nearRightOne.setNearId(3L);
        nearRightOne.setNearImg(shopImgList);
        shopDTO.setNearRightOne(nearRightOne);*/
        //右二
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
        ShopNearDTO nearRightTwo = new ShopNearDTO();
        nearRightTwo.setName("update临铺右1");
        nearRightTwo.setIndustryId(3L);
        nearRightTwo.setNearId(134L);
        nearRightTwo.setNearImg(shopImgList);
        shopDTO.setNearRightTwo(nearRightTwo);

        ResultDO<List<ShopNearDetailDTO>> resultDO = shopService.updateShopNear(shopDTO);
        if(resultDO.isSuccess()){
            List<ShopNearDetailDTO> result  = resultDO.getData();
            System.out.println(JSON.toJSON(result));
        }else {
            System.out.println(resultDO.getErrMsg());
        }
    }
    @Test
    public void delShopNear(){
        ResultDO<Boolean> resultDO =  shopService.delShopNear(4L,null);
        if(resultDO.isSuccess()){
            Boolean result  = resultDO.getData();
            System.out.println(result);
        }else {
            System.out.println(resultDO.getErrMsg());
        }
    }
    @Test
    public void listSort(){
        List<ShopImgDTO> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            ShopImgDTO dto = new ShopImgDTO();
            dto.setImgIndex(new Random().nextInt(5));
            dto.setImgUrl("12301.jpg");
            dto.setIsCover(i);
            list.add(dto);
        }
        Set<Integer> set = new HashSet<>();
        Iterator<ShopImgDTO> iterator = list.iterator();
        while (iterator.hasNext()){
            ShopImgDTO dto = iterator.next();
            Integer isCover = dto.getIsCover();//是否为封面(0-否 1-是)
            set.add(isCover);
        }
        System.out.println("set大小=="+set.size());
        if(set.size()!=list.size()){
            System.out.println("只能有一个封面");
            return;
        }
        Collections.sort(list, new Comparator<ShopImgDTO>(){
            @Override
            public int compare(ShopImgDTO o1, ShopImgDTO o2) {
                return o1.getImgIndex().compareTo(o2.getImgIndex());
            }
        });
        for(int i=0;i<list.size();i++){
            ShopImgDTO dto = list.get(i);
            System.out.println(dto.toString());
        }
    }

    @Test
    public void testQueryShopByCustomer(){
        ShopCustomerQuery query = new ShopCustomerQuery();
      /*  query.setQueryType(1);
        query.setShopType(2);
        query.setDistrictId(31001L);
        query.setLongitude("121.437817");
        query.setLatitude("31.107798");
        query.setRentList(Arrays.asList(new Integer[]{1,2,3,4}));
        query.setTransferList(Arrays.asList(new Integer[]{1,2,3,4}));
        query.setAreaList(Arrays.asList(new Integer[]{1,2,3,4}));*/
//        query.setFeatureTagList(Arrays.asList(new Long[]{1L,2L,3L,4L}));
//        query.setSupportTagList(Arrays.asList(new Long[]{1L,2L,3L,4L}));
        /*query.setSortType(2);*/
        query.setFollowAreaList(Arrays.asList(new Integer[]{1,2}));
        query.setFollowPlateList(Arrays.asList(new String[]{"大宁","曹杨"}));
        query.setFollowVocationList(Arrays.asList(new Long[]{1L,11L,15L}));
        ResultDO<Page<ShopCustomerDTO>> resultDO = shopService.queryShopByCustomer(query, 63L);
        Assert.assertTrue(resultDO.isSuccess());
    }

    @Test
    public void testQueryShopByClerk(){
        ShopClerkQuery query = new ShopClerkQuery();
        query.setShopType(1);
        query.setDistrictId(31001L);
        query.setLongitude("121.437817");
        query.setLatitude("31.107798");
        query.setRentList(Arrays.asList(new Integer[]{1,2,3,4}));
        query.setTransferList(Arrays.asList(new Integer[]{1,2,3,4}));
        query.setAreaList(Arrays.asList(new Integer[]{1,2,3,4}));
//        query.setFeatureTagList(Arrays.asList(new Long[]{1L,2L,3L,4L}));
//        query.setSupportTagList(Arrays.asList(new Long[]{1L,2L,3L,4L}));
        query.setSortType(2);
        ResultDO<Page<ShopClerkDTO>> resultDO = shopService.queryShopByClerk(query);
        Assert.assertTrue(resultDO.isSuccess());
    }

    @Test
    public void testGetLatestShop(){
        ResultDO<LatestShopDTO> resultDO = shopService.getLatestShop();
        Assert.assertTrue(resultDO.isSuccess());
    }

    @Test
    public void testGetLatestShopByPhone(){
        ResultDO<LatestShopDTO> resultDO = shopService.getLatestShopByPhone("13965013458");
        Assert.assertTrue(resultDO.isSuccess());
    }

    @Test
    public void testQueryMyShopScanCount(){
        ResultDO<LatestShopDTO> resultDO = shopService.getLatestShop();
        if(resultDO.isSuccess() && resultDO.getData() != null){
            Shop shop = shopManager.getShopById(resultDO.getData().getId());
            ResultDO<ShopScanCountDTO> resultDO1 = shopService.queryMyShopScanCount(shop.getContactTel());

            Assert.assertTrue(resultDO1.isSuccess());
        }
    }

    @Test
    public void testQueryMyPublishShop(){
        ResultDO<LatestShopDTO> resultDO = shopService.getLatestShop();
        if(resultDO.isSuccess() && resultDO.getData() != null){
            Shop shop = shopManager.getShopById(resultDO.getData().getId());
            ResultDO<Page<ShopCustomerPublishDTO>> resultDO1 = shopService.queryMyPublishShop(new BaseQuery(), shop.getContactTel());
            Assert.assertTrue(resultDO1.isSuccess());
        }
    }

    @Test
    public void testUnDoShop(){
        ResultDO<LatestShopDTO> resultDO = shopService.getLatestShop();
        if(resultDO.isSuccess() && resultDO.getData() != null){
            Shop shop = shopManager.getShopById(resultDO.getData().getId());
            ShopUndoDTO shopUndoDTO = new ShopUndoDTO();
            shopUndoDTO.setUndoType(UndoType.CLERK.getValue());
            shopUndoDTO.setUndoContent("测试下架");
            shopUndoDTO.setRentStatus(1);
            shopUndoDTO.setShopId(2L);
//            shopUndoDTO.setShopId(shop.getId());
            shopUndoDTO.setUndoId(shop.getClerkId());
            shopUndoDTO.setType(1);
            ResultDO<Boolean> resultDO1 = shopService.unDoShop(shopUndoDTO);

            Assert.assertTrue(resultDO1.isSuccess());
        }
    }

    /**
     * 测试商铺上架
     */
    @Test
    public void testPutAwayShop(){
        ResultDO<LatestShopDTO> resultDO = shopService.getLatestShop();
        if(resultDO.isSuccess() && resultDO.getData() != null){
            Shop shop = shopManager.getShopById(resultDO.getData().getId());
            ShopUndoDTO shopUndoDTO = new ShopUndoDTO();
            shopUndoDTO.setRentStatus(2);
            shopUndoDTO.setUndoType(UndoType.CLERK.getValue());
            shopUndoDTO.setUndoContent("测试下架");
            shopUndoDTO.setTagId(23L);
            shopUndoDTO.setShopId(shop.getId());
            shopUndoDTO.setUndoId(shop.getClerkId());
            ResultDO<Boolean> unDoShop = shopService.unDoShop(shopUndoDTO);
            System.out.println(unDoShop.toString());
            if(unDoShop.isSuccess()){
                ResultDO<Boolean> resultDO1 = shopService.putAwayShop(shop.getClerkId(), shop.getId());
                System.out.println(resultDO1);
                Assert.assertTrue(resultDO1.isSuccess());
            }

        }
    }



    @Test
    public void testCountShopFromClient(){
        ResultDO<ShopCountDTO> resultDO = shopService.countShopFromClient();
        Assert.assertNotNull(resultDO.getData());
    }

    @Test
    public void testCountShopFromService() {
        ResultDO<ShopCountDTO> resultDO = shopService.countShopFromService(12L);
        Assert.assertNotNull(resultDO.getData());
    }

    @Test
    public void testQueryShopImg(){
        ResultDO<List<ShopImgDTO>> resultDO = shopService.queryShopImg(37L);
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testQueryShopDetail(){
        ResultDO<ShopDetailDTO> resultDO = shopService.queryShopDetail(1L);
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testShopCollectedList(){
        ShopCollectedQuery query = new ShopCollectedQuery();
        query.setCustomerId(1L);
        query.setPageSize(2);
        ResultDO<Page<ShopCollectedCustomerDTO>> resultDO = shopService.shopCollectedList(query);
        if(resultDO.isSuccess()){
            List<ShopCollectedCustomerDTO> list = resultDO.getData().getData();
            if (list!=null && list.size()>0)
            System.out.println(JSON.toJSONString(list));
        }
    }
    @Test
    public void shopBrowseList(){
        ShopCollectedQuery query = new ShopCollectedQuery();
        query.setCustomerId(1L);
       // query.setPageSize(2);
        ResultDO<Page<ShopBrowseCustomerDTO>> resultDO = shopService.shopBrowseList(query);
        if(resultDO.isSuccess()){
            List<ShopBrowseCustomerDTO> list = resultDO.getData().getData();
            if (list!=null && list.size()>0)
                System.out.println(JSON.toJSONString(list));
        }
    }

    @Test
    public void testQueryClerkByShopId() {
        ClerkDTO clerkDTO = shopService.queryClerkByShopId(2L);
        System.out.println(clerkDTO);
    }


    @Test
    public void testShopVisitedList(){
        ShopVisitQuery query=new ShopVisitQuery();
        query.setCustomerId(61L);
 /*       query.setLatitude("31.3046400000");
        query.setLongitude("121.4919600000");*/
        ResultDO<Page<ShopVisitCustomerDTO>> resultDO=shopService.shopVisitedList(query);
        System.out.println(JSON.toJSONString(resultDO));
    }

    @Test
    public void testCountShops(){

        System.out.println(JSON.toJSONString(shopService.countShops()));
    }

    @Test
    public void testQueryShopId(){
        ResultDO<Long> resultDO = shopService.getShopId("098761");
        Assert.assertNotNull(resultDO);
    }
    @Test
    public void test02(){
        boolean result  = RegexUtils.find("1237t.98","^(([1-9]{1,5})|([0-9]{1,5}\\.[0-9]{1,2}))$");
        System.out.println("=======result========"+result);
    }

    @Test
    public void testUpdateClaimInfo(){
        ResultDO<String> resultDO =  shopService.updateClaimInfo(177L,3L);
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testShopCodeGenerate(){
        ShopCodeDTO dto = new ShopCodeDTO();
        dto.setCityCode("021");
        dto.setCount(5);
        ResultDO<String> resultDO = shopService.shopCodeGenerate(dto);
        if(resultDO.isSuccess()){
            System.out.println("成功==");
            System.out.println(resultDO.getData());
        }else {
            System.out.println("失败==");
            System.out.println(resultDO.getErrMsg());
        }

    }

    @Test
    public void testGetShopById(){
        ResultDO<Shop> resultDO = shopService.getShopById(1L);
        System.out.println(resultDO.toString());
    }

    @Test
    public void calculateShopScore(){
        shopService.calculateShopScore();
    }
}
