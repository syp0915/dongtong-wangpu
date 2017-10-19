package com.dongtong.clerk.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.clerk.JunitBaseTest;
import com.dongtong.clerk.dto.ClerkDetailDTO;
import com.dongtong.clerk.dto.UpdateClerkDetailDTO;
import com.dongtong.clerk.dto.resp.LoginRespDTO;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/12 下午3:39.
 */
public class ClerkServiceTest extends JunitBaseTest {

    @Autowired(required = false)
    private ClerkService clerkService;

    @Test
    public void tesCustomerLogin(){
        String userPhone = "18812114337";
        String smsVerifyCode = "9876";
        Long messageId = 1L;
        String picVerifyCode = "8890";
        Long picVerifyId = 2L;
        String inviteCode = "5678";
        Integer osType = 0;
        String deviceId = "FDSFSDGERQ$T748923748923789432DSSFS";
        ResultDO<LoginRespDTO> resultDO = clerkService.clerkLogin(userPhone, smsVerifyCode, messageId, picVerifyCode, picVerifyId, inviteCode, osType, deviceId);
        System.out.println(JSON.toJSONString("result--------------->" + JSON.toJSONString(resultDO)));
    }

    @Test
    public void testGetClerkDetailById(){
        ResultDO<ClerkDetailDTO> resultDO = clerkService.getClerkDetailById(1L);
        System.out.println("result--------------->" +resultDO.getErrCode()+"----------"+resultDO.getErrMsg());
    }

    @Test
    public void testGetClerkDetailById1(){
        ResultDO<ClerkDetailDTO> resultDO = clerkService.getClerkDetailById(2221L);
        System.out.println("result--------------->" +resultDO.getErrCode()+"----------"+resultDO.getErrMsg());
    }

    @Test
    public void testUpdateClerkDetail(){
        UpdateClerkDetailDTO dto = new UpdateClerkDetailDTO();
        dto.setClerkId(1L);
        dto.setRealName("小明");
        dto.setUrl("www.baidu.com");
        ResultDO resultDO = clerkService.updateClerkDetail(dto);
        System.out.println("result--------------->" +resultDO.getErrCode()+"----------"+resultDO.getErrMsg());
    }


    @Test
    public void testUpdateClerkDetail1(){
        UpdateClerkDetailDTO dto = new UpdateClerkDetailDTO();
        dto.setClerkId(1L);
        dto.setUrl("www.baidu.com");
        ResultDO resultDO = clerkService.updateClerkDetail(dto);
        System.out.println("result--------------->" +resultDO.getErrCode()+"----------"+resultDO.getErrMsg());
    }

    @Test
    public void testUpdateClerkDetail2(){
        UpdateClerkDetailDTO dto = new UpdateClerkDetailDTO();
        dto.setClerkId(122L);
        dto.setRealName("小明");
        dto.setUrl("www.baidu.com");
        ResultDO resultDO = clerkService.updateClerkDetail(dto);
        System.out.println("result--------------->" +resultDO.getErrCode()+"----------"+resultDO.getErrMsg());
    }

    @Test
    public void testUpdateClerkDetail3(){
        UpdateClerkDetailDTO dto = new UpdateClerkDetailDTO();
        dto.setRealName("小明");
        dto.setUrl("www.baidu.com");
        ResultDO resultDO = clerkService.updateClerkDetail(dto);
        System.out.println("result--------------->" +resultDO.getErrCode()+"----------"+resultDO.getErrMsg());
    }

    @Test
    public void testIsNewNotification(){
        ResultDO<Boolean> resultDO = clerkService.isNewNotification(1L);
        System.out.println("result--------------->" +resultDO.getErrCode()+"----------"+resultDO.getErrMsg()+"ddddd"+resultDO.getData());
    }
    @Test
    public void getClerkId(){
        ResultDO resultDO =clerkService.getAllClerkIds();
        System.out.println("result--------------->" +resultDO.getErrCode()+"----------"+resultDO.getErrMsg()+"ddddd"+resultDO.getData());
    }

    @Test
    public void testClerkLogout(){
        ResultDO resultDO = clerkService.clerkLogout(1L, "DADAFSARE5234324DFSFDSFDSF43");
        System.out.println("result--------------->" + JSON.toJSONString(resultDO));
    }

    @Test
    public void testUpdateDeviceId(){
        ResultDO resultDO = clerkService.updateDeviceId(1L, "DADAFSARE5234324DFSFDSFDSF43");
        System.out.println("result--------------->" + JSON.toJSONString(resultDO));
    }

}
