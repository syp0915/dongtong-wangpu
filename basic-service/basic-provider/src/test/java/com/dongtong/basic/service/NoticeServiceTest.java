package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.*;
import com.dongtong.basic.query.NoticeQuery;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.service
 * @Description ：消息通知接口测试
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-10 17:23
 * version V1.0.0
 **/
public class NoticeServiceTest extends JunitBaseTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    public void testUndoStatus(){
        ResultDO resultDO=notificationService.checkNotification("51",1);
        System.out.println(resultDO.getData());
    }

    @Test
    public void testNoticeList(){
        BaseNoticeReqDTO baseNoticeDTO=new BaseNoticeReqDTO();
        baseNoticeDTO.setReceiveType(1);
        baseNoticeDTO.setReceiveId(20L);
        ResultDO resultDO=notificationService.getNoticeList(baseNoticeDTO);
        System.out.println(resultDO.getData());
    }
    @Test
    public void testupdateNoticeStatusById(){
        ResultDO resultDO=notificationService.updateById(1L);
        System.out.println("RESULT-------->"+ JSON.toJSON(resultDO.getData()));
    }

    @Test
    public void testupdateNoticeStatus(){
        BaseNotification baseNotification=new BaseNotification();
        baseNotification.setReceiverType(0);
        baseNotification.setReceiverId(2L);
        baseNotification.setNotifyType(0);
        ResultDO resultDO=notificationService.updateNoticeStatus(baseNotification);
        System.out.println("RESULT-------->"+ JSON.toJSON(resultDO.getData()));
    }

    @Test
    public  void testList(){
        NoticeQuery noticeQuery=new NoticeQuery();
        noticeQuery.setReceiverId(22L);
        noticeQuery.setNotifyType(3);
        noticeQuery.setReceiverType(1);
        noticeQuery.setPageNumber(1);
        noticeQuery.setPageSize(10);
        ResultDO resultDO=notificationService.selectNoticeListByType(noticeQuery);
        System.out.println(resultDO.getData());
    }
    @Test
    public void  selectBusNoticeCount(){
        BaseNoticeReqDTO baseNoticeDTO=new BaseNoticeReqDTO();
        baseNoticeDTO.setReceiveType(1);
        baseNoticeDTO.setReceiveId(20L);
        ResultDO resultDO=notificationService.selectBusNoticeCount(baseNoticeDTO);
        System.out.println(resultDO.getData());
    }

    /**
     * 服务通知
     */
    @Test
    public void testServiceNotice(){
        ServiceNoticeReqDTO serviceNoticeDTO=new ServiceNoticeReqDTO();
        serviceNoticeDTO.setServiceType(1);
        serviceNoticeDTO.setServiceStatus(2);
        serviceNoticeDTO.setShopAddress("酷酷路20号");
        serviceNoticeDTO.setReceiveId(1L);
        serviceNoticeDTO.setOldTime("2017-05-11");
        serviceNoticeDTO.setCurrentTime("2017-05-11");
        serviceNoticeDTO.setReason("不想看啦");
        serviceNoticeDTO.setServiceName("果然");
        serviceNoticeDTO.setServiceTel("18733543367");
        serviceNoticeDTO.setBussinessId(6L);
        ResultDO resultDO=notificationService.addServiceNotice(serviceNoticeDTO);
        System.out.println(resultDO.getData());

    }

    /**
     * 生意圈通知
     */
    @Test
    public void testBussinessNotice(){
        BussinessNoticeReqDTO bussinessNoticeDTO =new BussinessNoticeReqDTO();
        bussinessNoticeDTO.setBussinessId(2L);
        bussinessNoticeDTO.setReceiveId(3L);
        bussinessNoticeDTO.setReceiveType(1);
        bussinessNoticeDTO.setBussinessType(0);
        bussinessNoticeDTO.setContentOrReason("我也不信");
        bussinessNoticeDTO.setCommentTel("13761289987");
        bussinessNoticeDTO.setCommentOrOprationTime("2017-05-02 11:09:00");
        bussinessNoticeDTO.setPostName("我是亿万富翁");
        ResultDO resultDO=notificationService.addBussinessNotice(bussinessNoticeDTO);
        System.out.println(resultDO.getData());

    }

    /**
     * 商铺通知
     */
    @Test
    public void testShopNotice(){
        ShopNoticeReqDTO shopNoticeDTO=new ShopNoticeReqDTO();
        shopNoticeDTO.setShopAddress("徐汇区杨树坪路");
        shopNoticeDTO.setShopNoticeType(0);
        shopNoticeDTO.setBussinessId(2L);
        shopNoticeDTO.setReceiveId(1L);
        ResultDO resultDO=notificationService.addShopNotice(shopNoticeDTO);
        System.out.println(resultDO.getData());
    }

    /**
     * 工作通知
     */
    @Test
    public void testWorkNotice(){
        WorkNoticeReqDTO workNoticeDTO=new WorkNoticeReqDTO();
        workNoticeDTO.setBussinessId(2L);
        workNoticeDTO.setReceiveId(2L);
        workNoticeDTO.setServiceType(0);
        workNoticeDTO.setPlanTime("12:01");
        workNoticeDTO.setShopAddress("中间路 3号");
        workNoticeDTO.setWorkNoticeType(4);
        workNoticeDTO.setCloseStoreName("热");
        workNoticeDTO.setLookName("地方的");
        workNoticeDTO.setMarketName("口语");
        workNoticeDTO.setRegisterName("法国人");
        workNoticeDTO.setSignName("就为");
        ResultDO resultDO=notificationService.addWorkNotice(workNoticeDTO);
        System.out.println(resultDO.getData());

    }



}
