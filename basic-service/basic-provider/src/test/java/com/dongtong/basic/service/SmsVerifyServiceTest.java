package com.dongtong.basic.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.JunitBaseTest;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.dto.resp.PicVerifyRespDTO;
import com.dongtong.basic.dto.resp.SmsVerifyRespDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.ServiceNoticeType;
import com.dongtong.basic.enums.ServiceStatus;
import com.dongtong.basic.enums.SmsUseSceneType;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/11 下午2:39.
 */
public class SmsVerifyServiceTest extends JunitBaseTest{

    @Autowired
    private SmsService smsService;

    /**
     * 发送短信验证码
     * @return
     */
    @Test
    public void testSendSmsVerifyCode(){
        String userPhone = "17602113283";
        Integer useSence = 0;
        ResultDO<SmsVerifyRespDTO> resultDO = smsService.sendSmsVerifyCode(userPhone, useSence);
        System.out.println("result------------------>" + JSON.toJSONString(resultDO));
    }

    /**
     * 发送图片验证码
     * @return
     */
    @Test
    public void getVerifyPic(){
        Integer useScene = 0;
        ResultDO<PicVerifyRespDTO> resultDO = smsService.getVerifyPic(useScene);
        System.out.println("result------------------>" + JSON.toJSONString(resultDO));
    }

    /**
     * 验证短信验证码
     * @return
     */
    @Test
    public void verifySmsVerifyCode(){
        Long messageId = 8888888888888L;
        String verifyCode = "8888";
        String userPhone = "18512114337";
        ResultDO<Boolean> resultDO = smsService.verifySmsVerifyCode(messageId, verifyCode, userPhone, SmsUseSceneType.LOGIN.getValue());
        System.out.println("result------------------>" + JSON.toJSONString(resultDO));
    }

    /**
     * 验证图片验证码
     * @return
     */
    @Test
    public void verifyPicVerify(){
        Long picVerifyId = 1L;
        String verifyCode = "9128";
        ResultDO<Boolean> resultDO = smsService.verifyPicVerify(picVerifyId, verifyCode);
        System.out.println("result------------------>" + JSON.toJSONString(resultDO));
    }


}
