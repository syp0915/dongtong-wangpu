package com.dongtong.clerk.service;

import com.dongtong.clerk.JunitBaseTest;
import com.dongtong.clerk.bo.ClerkHintBO;
import com.dongtong.clerk.constant.ClerkHintFromEnum;
import com.dongtong.clerk.dao.ClerkHintMapper;
import com.dongtong.clerk.dto.*;
import com.dongtong.clerk.query.ClerkHintList4MapQuery;
import com.dongtong.clerk.query.ClerkHintListQuery;
import com.dongtong.clerk.query.ClerkHintQuery;
import com.dongtong.clerk.query.ClerkHintTypeQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.clerk
 * @Description :TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 14:50
 * version V1.0.0
 **/
public class ClerkHintServiceTest extends JunitBaseTest{

    @Autowired
    private ClerkHintService clerkHintService;
    @Resource
    private ClerkHintMapper clerkHintMapper;

    @Test
    public  void addClerkHint(){
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        clerkHintDTO.setShopAddress("川沙路23号");
        clerkHintDTO.setLinkmanName("朵喵喵喵喵喵喵喵");
        clerkHintDTO.setLinkmanPhone("13982367867");
//        clerkHintDTO.setSubscribeTime("20170516142011");
        clerkHintDTO.setBlockId(1L);
        clerkHintDTO.setBlockName("a");
        clerkHintDTO.setDistrictId(1L);
        clerkHintDTO.setDistrictName("er");
        clerkHintDTO.setHintFrom(ClerkHintFromEnum.STREET.getValue());
        clerkHintDTO.setTotalFloor(10);
        clerkHintDTO.setFloor("1");
        clerkHintDTO.setHasPoster(1);
        clerkHintDTO.setShopCode("123132131213");
        ResultDO resultDO=clerkHintService.issueClue(clerkHintDTO);
        System.out.println(resultDO.getData());
    }

    @Test
    public void getTotal(){
        ResultDO resultDO=clerkHintService.releaseTotal();
        System.out.println(resultDO.getData());
    }

    /**
     * 收铺线索列表接口
     */
    @Test
    public void getShopClueList(){
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        clerkHintDTO.setId(1L);
        Page page=new Page();
        page.setPageNumber(1);
        page.setPageSize(2);
        ResultDO <Page<ClerkHintBO>> res= clerkHintService.getShopClueList(clerkHintDTO,page);
        System.out.println(res.toString());
    }


    /**
     * 店铺线索详情
     * @param
     * @return
     */
    @Test
    public void getShopClueInfo(){
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        clerkHintDTO.setId(211L);
        ResultDO <ClerkHintDTO> res= clerkHintService.getShopClueInfo(clerkHintDTO);
        System.out.println(res.toString());
    }

    /**
     * 店铺线索认领
     * @param
     * @return
     */
    @Test
    public void testShopClaim() {
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        clerkHintDTO.setId(374L);
        clerkHintDTO.setOwnerId(56L);
        ResultDO res =clerkHintService.shopClaim(clerkHintDTO);
        System.out.println(res.toString());
    }
    /**
     * 店铺线索修改时间
     * @param
     * @return
     */
    @Test
    public void updateSubscribeTime() throws ParseException {
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        clerkHintDTO.setId(3L);
        clerkHintDTO.setSubscribeTime("20170515164401");
        ResultDO res =clerkHintService.updateSubscribeTime(clerkHintDTO);
        if(res!=null)
        System.out.println(res.toString());
    }
    /***
     *  店铺线索废弃
     * @param
     * @return
     */
    @Test
    public void updateClueStatus() {
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        clerkHintDTO.setId(141L);
        clerkHintDTO.setRemark("11111");
        clerkHintDTO.setDiscardCause("不想租");
        clerkHintDTO.setModifier(106L);
        ResultDO res =clerkHintService.updateClueStatus(clerkHintDTO);
        System.out.println(res.toString());
    }

    /**
     * 查询线索详情信息
     */
    @Test
    public void testQueryClerkHintDetail(){
        ClerkHintQuery query = new ClerkHintQuery();
        query.setClerkId(211L);
        ResultDO result = clerkHintService.queryClerkHintDetail(query);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testQueryClerkHintList1(){
        ClerkHintListQuery listQuery = new ClerkHintListQuery();
        listQuery.setLongitude("1");
        listQuery.setLatitude("1");
        listQuery.setOrderType(3);
        listQuery.setHintOwnType(2);
        listQuery.setOwnerId(1L);
        ResultDO result = clerkHintService.queryClerkHintList(listQuery);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testQueryClerkHintList2(){
        ClerkHintListQuery listQuery = new ClerkHintListQuery();
        listQuery.setLongitude("1");
        listQuery.setLatitude("1");
        listQuery.setOrderType(1);
        listQuery.setHintOwnType(2);
        listQuery.setOwnerId(1L);
        List statusList = new ArrayList();
        statusList.add(1);
        statusList.add(2);
        listQuery.setStatusList(statusList);
        ResultDO result = clerkHintService.queryClerkHintList(listQuery);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testQueryClerkHintList3(){
        ClerkHintListQuery listQuery = new ClerkHintListQuery();
        listQuery.setLongitude("121.490769");
        listQuery.setLatitude("31.243668");
        listQuery.setOrderType(1);
        listQuery.setHintOwnType(1);
        listQuery.setOwnerId(105L);
//        listQuery.setStatus(3);
        List statusList = new ArrayList();
        statusList.add(2);
        statusList.add(3);
        listQuery.setStatusList(statusList);
        ResultDO result = clerkHintService.queryClerkHintList(listQuery);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testQueryClerkHintListForMap0(){
        ClerkHintList4MapQuery clerkHintList4MapQuery = new ClerkHintList4MapQuery();
        clerkHintList4MapQuery.setBlockId(143L);
        List hinFromList = new ArrayList();
        hinFromList.add(1);
        clerkHintList4MapQuery.setHintFromList(hinFromList);
        clerkHintList4MapQuery.setOwnType(1);
        ResultDO result = clerkHintService.queryClerkHintListForMap(clerkHintList4MapQuery);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testQueryClerkHintListForMap1(){
        ClerkHintList4MapQuery clerkHintList4MapQuery = new ClerkHintList4MapQuery();
        clerkHintList4MapQuery.setBlockId(143L);
        List hinFromList = new ArrayList();
        hinFromList.add(2);
        clerkHintList4MapQuery.setHintFromList(hinFromList);
        clerkHintList4MapQuery.setOwnType(1);
        ResultDO result = clerkHintService.queryClerkHintListForMap(clerkHintList4MapQuery);
        Assert.assertTrue(result.isSuccess());
    }
    @Test
    public void selectSchedule(){
        ResultDO result = clerkHintService.selectSchedule();
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 张贴海报
     */
    @Test
    public  void addPublishHintByClerk(){
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        clerkHintDTO.setIssuerId(104L);
        clerkHintDTO.setShopAddress("武东路198号");
        clerkHintDTO.setLinkmanName("白嘉轩");
        clerkHintDTO.setLinkmanPhone("13482192819");
        clerkHintDTO.setLongitude("121.490601");
        clerkHintDTO.setLatitude("31.243001");
//        clerkHintDTO.setDistrictId(310104L);
//        clerkHintDTO.setDistrictName("徐家汇");
//        clerkHintDTO.setBlockId(80L);
//        clerkHintDTO.setBlockName("南杨小区");
        List<ShopImgDTO> shopImgDTOList = new ArrayList<ShopImgDTO>();
        ShopImgDTO shopImgDTO = new ShopImgDTO();
        shopImgDTO.setImgIndex(1);
        shopImgDTO.setIsCover(1);
        shopImgDTO.setImgUrl("http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg");
        shopImgDTOList.add(shopImgDTO);
        clerkHintDTO.setShopImgList(shopImgDTOList);
        clerkHintDTO.setHintFrom(1);
        clerkHintDTO.setTotalFloor(10);
        clerkHintDTO.setFloor("1");
        clerkHintDTO.setHasPoster(1);
        clerkHintDTO.setShopCode("123456789");
        clerkHintDTO.setIsShow(1);
        clerkHintDTO.setAddrIsShow(1);
        ResultDO resultDO=clerkHintService.publishHintByClerk(clerkHintDTO);

        Assert.assertTrue(resultDO.isSuccess());
    }

    /**
     * 不张贴海报
     */
    @Test
    public void addPublishHintByClerk1(){
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        clerkHintDTO.setIssuerId(102L);
        clerkHintDTO.setShopAddress("武东路198号");
        clerkHintDTO.setLinkmanName("白嘉轩");
        clerkHintDTO.setLinkmanPhone("13482192819");
        clerkHintDTO.setLongitude("121.490601");
        clerkHintDTO.setLatitude("31.243001");
//        clerkHintDTO.setDistrictId(310104L);
//        clerkHintDTO.setDistrictName("徐家汇");
//        clerkHintDTO.setBlockId(80L);
//        clerkHintDTO.setBlockName("南杨小区");
        List<ShopImgDTO> shopImgDTOList = new ArrayList<ShopImgDTO>();
        ShopImgDTO shopImgDTO = new ShopImgDTO();
        shopImgDTO.setImgIndex(1);
        shopImgDTO.setIsCover(1);
        shopImgDTO.setImgUrl("http://shfc-img.img-cn-shanghai.aliyuncs.com/other/%E6%97%BA%E9%93%BA%E6%88%BF%E4%BA%A7%E5%9C%B0%E5%9D%80/2017/05/11/IMG_1494483965261_60809.jpg");
        shopImgDTOList.add(shopImgDTO);
        clerkHintDTO.setShopImgList(shopImgDTOList);
        clerkHintDTO.setHintFrom(1);
        clerkHintDTO.setTotalFloor(10);
        clerkHintDTO.setFloor("1");
        clerkHintDTO.setHasPoster(0);
        clerkHintDTO.setShopCode("123456789");
        clerkHintDTO.setIsShow(1);
        clerkHintDTO.setAddrIsShow(1);
        ResultDO resultDO=clerkHintService.publishHintByClerk(clerkHintDTO);

        Assert.assertTrue(resultDO.isSuccess());
    }

    @Test
    public void updateStatus() {
        ClerkHintDTO clerkHintDTO=new ClerkHintDTO();
        clerkHintDTO.setId(1L);
        clerkHintDTO.setStatus(2);

        ResultDO res =clerkHintService.updateStatus(clerkHintDTO);
        System.out.println(res.toString());
    }

    @Test
    public void testHintStatistics(){
        ResultDO res = clerkHintService.hintStatistics(1L);
        Assert.assertTrue(res.isSuccess());
    }


    @Test
    public void testQueryHintList(){
        ClerkHintTypeQuery clerkHintTypeQuery = new ClerkHintTypeQuery();
        clerkHintTypeQuery.setId(1L);
        clerkHintTypeQuery.setType(2);
        ResultDO<Page<ClerkHintList>> resultDO = clerkHintService.queryHintList(clerkHintTypeQuery);
        System.out.println("*********************************************");
        System.out.println(resultDO.toString());
    }

    @Test
    public void testGetHintDetailInfo(){
        ClerkHintDetailReqDTO reqDTO = new ClerkHintDetailReqDTO();
        reqDTO.setClerkId(1L);
        reqDTO.setId(195L);
        reqDTO.setType(1);
        ResultDO<ClerkHintDetailDTO> resultDO = clerkHintService.getHintDetailInfo(reqDTO);
        System.out.println(resultDO.toString());
    }

    /**
     * 交易员认领线索
     */
    @Test
    public void testTradeClerkClaim(){
        ClerkHintComfirmDTO clerkHintComfirmDTO = new ClerkHintComfirmDTO();
        clerkHintComfirmDTO.setHintId(211L);
        clerkHintComfirmDTO.setClerkId(102L);
        ResultDO resultDO = clerkHintService.tradeClerkClaim(clerkHintComfirmDTO);
        Assert.assertNotNull(resultDO);
    }

    /**
     * 修改线索位置信息
     */
    @Test
    public void testUpdatePositionInfo(){
        ClerkHintDTO clerkHintDTO = new ClerkHintDTO();
        clerkHintDTO.setModifier(106L);
        clerkHintDTO.setId(308L);
        clerkHintDTO.setLongitude("121.49");
        clerkHintDTO.setLatitude("31.24");
        clerkHintDTO.setShopAddress("武东路198号4楼");
        clerkHintDTO.setHasPoster(1);
        clerkHintDTO.setShopCode("02100000044");
        ResultDO resultDO = clerkHintService.updatePositionInfo(clerkHintDTO);
        Assert.assertTrue(resultDO.isSuccess());
    }

}
