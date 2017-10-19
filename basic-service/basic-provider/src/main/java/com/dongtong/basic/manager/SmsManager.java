package com.dongtong.basic.manager;

import com.alibaba.fastjson.JSON;
import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.dao.SmsVerifyCodeMapper;
import com.dongtong.basic.remote.MessageCenterNetRequest;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/8 下午3:10.
 */
@Service
public class SmsManager {

    @Autowired(required = false)
    private SmsVerifyCodeMapper smsVerifyCodeMapper;

    @Autowired(required = false)
    private MessageCenterNetRequest messageCenterNetRequest;


    public ResultDO sendSmsVerifyCode(String userPhone, String verifyCode){
        ResultDO resultDO = new ResultDO();
        List<String> contentParamList = new ArrayList<String>();
        contentParamList.add(verifyCode);
        String result = messageCenterNetRequest.sendCommonSmsVerifyCode(userPhone, contentParamList);
        if (result == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.REMOTE_SERVER_RESP_NULL.getCode());
            resultDO.setErrMsg(ErrorConstant.REMOTE_SERVER_RESP_NULL.getMsg());
            return resultDO;
        }
        resultDO = JSON.parseObject(result, ResultDO.class);
        return resultDO;
    }

}
