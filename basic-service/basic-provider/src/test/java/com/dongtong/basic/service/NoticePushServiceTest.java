package com.dongtong.basic.service;

import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.req.BussinessNoticeReqDTO;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.dto.req.ShopNoticeReqDTO;
import com.dongtong.basic.dto.req.WorkNoticeReqDTO;
import com.shfc.common.result.ResultDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.service
 * @Description :消息推送测试
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-11 17:42
 * version V1.0.0
 **/
public class NoticePushServiceTest extends JunitBaseTest {

    @Autowired
    private NoticePushService noticePushService;

    @Test
    public void testPushPostCommentNotify(){
        BussinessNoticeReqDTO bussinessNoticeReqDTO=getBussinessNotice();
        ResultDO<Boolean> resultDO = noticePushService.pushPostCommentNotify(bussinessNoticeReqDTO,"1507bfd3f7f6d1a7a9e",1);
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testPushBusDelNotify(){
        BussinessNoticeReqDTO bussinessNoticeReqDTO=getBussinessNotice();
        ResultDO<Boolean> resultDO = noticePushService.pushBusDelNotify(bussinessNoticeReqDTO,"1507bfd3f7f6d1a7a9e",1);
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testPushScheduleNotify(){
        ServiceNoticeReqDTO serviceNoticeReqDTO=getServiceNotice();
        ResultDO<Boolean> resultDO = noticePushService.pushScheduleNotify(serviceNoticeReqDTO,"1",1);
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testPushScheduleChangeNotify(){
//        ServiceNoticeReqDTO serviceNoticeReqDTO= new ServiceNoticeReqDTO();
//        String jsonStr = "{\"bussinessId\":177,\"currentTime\":\"2017-08-22 10:30:00\",\"receiveId\":105,\"serviceName\":\"陈双双\",\"serviceStatus\":4,\"serviceTel\":\"18717703112\",\"serviceType\":1,\"shopAddress\":\"武东路188号\"}";
//        net.sf.json.JSONObject obj = new net.sf.json.JSONObject().fromObject(jsonStr);//将json字符串转换为json对象
//        serviceNoticeReqDTO = (ServiceNoticeReqDTO)net.sf.json.JSONObject.toBean(obj,ServiceNoticeReqDTO.class);
//        ResultDO<Boolean> resultDO = noticePushService.pushScheduleChangeNotify(serviceNoticeReqDTO,"17612174073","161a3797c835c924038",0);
//        while (1==1){
//            System.out.println("11111111");
//        }
//        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testPushServiceRevokeNotify(){
        ServiceNoticeReqDTO serviceNoticeReqDTO=getServiceNotice();
        ResultDO<Boolean> resultDO = noticePushService.pushServiceRevokeNotify(serviceNoticeReqDTO,"13761363380","161a3797c835c924038",0);
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testPushServiceSNotify(){
        ServiceNoticeReqDTO serviceNoticeReqDTO=getServiceNotice();
        ResultDO<Boolean> resultDO = noticePushService.pushServiceSucNotify(serviceNoticeReqDTO,"13761363380");
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testPushShopNotice(){
        ShopNoticeReqDTO shopNoticeReqDTO=getShopNotice();
        ResultDO resultDO = noticePushService.pushShopNotice(shopNoticeReqDTO,"100d85590976026b4be",1);
//        while (1==1){
//            System.out.println("111111111");
//        }
        Assert.assertNotNull(resultDO);
    }
    @Test
    public void testClerkShopNotice(){
        ShopNoticeReqDTO shopNoticeReqDTO=getShopNotice();
        ResultDO<Boolean> resultDO =noticePushService.clerkShopNotice(shopNoticeReqDTO,"13165ffa4e3912f08aa",1);
        System.out.println(resultDO);
    }
    @Test
    public void testPushWorkNotice(){
        WorkNoticeReqDTO workNoticeReqDTO=getWorkNotice();//18171adc033ce488533
        ResultDO resultDO = noticePushService.pushWorkNotice(workNoticeReqDTO,"13165ffa4e3912f08aa",0);
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testPushArrivalTimeNotice(){
        WorkNoticeReqDTO workNoticeReqDTO=getWorkNotice();
        ResultDO resultDO = noticePushService.pushArrivalTimeNotice(workNoticeReqDTO,"2",0);
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testPushWeekMonthNotice(){
        WorkNoticeReqDTO workNoticeReqDTO=getWorkNotice();
        ResultDO resultDO = noticePushService.pushWeekMonthNotice(workNoticeReqDTO,"13165ffa4e3912f08aa",0);
        Assert.assertNotNull(resultDO);
    }

    @Test
    public void testPushPostCommentBusNotify(){
        BussinessNoticeReqDTO bussinessNoticeReqDTO=getBussinessNotice();
        ResultDO resultDO = noticePushService.pushPostCommentBusNotify(bussinessNoticeReqDTO,"",1);
        Assert.assertNotNull(resultDO);
    }

    /**
     * 服务通知
     */
    public ServiceNoticeReqDTO getServiceNotice(){
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
        return serviceNoticeDTO;
    }

    /**
     * 生意圈通知
     */
    public BussinessNoticeReqDTO getBussinessNotice(){
        BussinessNoticeReqDTO bussinessNoticeDTO =new BussinessNoticeReqDTO();
        bussinessNoticeDTO.setBussinessId(2L);
        bussinessNoticeDTO.setReceiveId(3L);
        bussinessNoticeDTO.setReceiveType(0);
        bussinessNoticeDTO.setBussinessType(2);
        bussinessNoticeDTO.setContentOrReason("我也不信");
        bussinessNoticeDTO.setCommentTel("13761289987");
        bussinessNoticeDTO.setCommentOrOprationTime("2017-05-02 11:09:00");
        bussinessNoticeDTO.setPostName("我是亿万富翁");
        return bussinessNoticeDTO;
    }

    /**
     * 商铺通知
     */
    public ShopNoticeReqDTO getShopNotice(){
        ShopNoticeReqDTO shopNoticeDTO=new ShopNoticeReqDTO();
        shopNoticeDTO.setShopAddress("23");
        shopNoticeDTO.setShopNoticeType(0);
        shopNoticeDTO.setBussinessId(2L);
        shopNoticeDTO.setReceiveId(1L);
       return shopNoticeDTO;
    }

    /**
     * 工作通知
     */
    public WorkNoticeReqDTO getWorkNotice(){
        WorkNoticeReqDTO workNoticeDTO=new WorkNoticeReqDTO();
        workNoticeDTO.setBussinessId(2L);
        workNoticeDTO.setReceiveId(2L);
        workNoticeDTO.setServiceType(2);
        workNoticeDTO.setPlanTime("2016-09-09 12:09:12");
        workNoticeDTO.setShopAddress("中间路 3号");
        workNoticeDTO.setWorkNoticeType(4);
        workNoticeDTO.setCloseStoreName("热");
        workNoticeDTO.setLookName("地方的");
        workNoticeDTO.setMarketName("口语");
        workNoticeDTO.setRegisterName("法国人");
        workNoticeDTO.setSignName("就为");
        return workNoticeDTO;
    }

}
