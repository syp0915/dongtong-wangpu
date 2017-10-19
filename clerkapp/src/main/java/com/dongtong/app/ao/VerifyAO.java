package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.basic.StringUtils;
import com.dongtong.basic.dto.resp.PicVerifyRespDTO;
import com.dongtong.basic.dto.resp.SmsVerifyRespDTO;
import com.dongtong.basic.query.PicVerifyReqQuery;
import com.dongtong.basic.query.SmsVerifyReqQuery;
import com.dongtong.basic.service.SmsService;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/10 下午4:32.
 */
@Service
public class VerifyAO {

    @Autowired(required = false)
    private SmsService smsService;

    public ResultDO<SmsVerifyRespDTO> sendSmsVerifyCode(SmsVerifyReqQuery query){
        ResultDO<SmsVerifyRespDTO> resultDO = new ResultDO<SmsVerifyRespDTO>();
        String userPhone = query.getUserPhone();
        Integer sendType = query.getSendType();
        Integer useScene = query.getUseScene();
        if (ValidateHelper.isEmpty(userPhone) || ValidateHelper.isEmpty(useScene)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        if (sendType != null && sendType != 0){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.UNSUPPORT_SMS_TYPE.getCode());
            resultDO.setErrMsg(ErrorConstant.UNSUPPORT_SMS_TYPE.getMsg());
            return resultDO;
        }
        if (!StringUtils.isChinaPhoneLegal(userPhone)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.PHONE_FORMAT_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.PHONE_FORMAT_EXCEPTION.getMsg());
            return resultDO;
        }
        return smsService.sendSmsVerifyCode(userPhone, useScene);
    }

    public ResultDO<PicVerifyRespDTO> getVerifyPic(PicVerifyReqQuery query){
        ResultDO<PicVerifyRespDTO> resultDO = new ResultDO<PicVerifyRespDTO>();
        Integer useScene = query.getUseScene();
        if (ValidateHelper.isEmpty(useScene)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        return smsService.getVerifyPic(useScene);
    }


}
