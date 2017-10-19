package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.StringUtils;
import com.dongtong.customer.dto.resp.LoginRespDTO;
import com.dongtong.customer.query.LoginReqQuery;
import com.dongtong.customer.query.UpdateDeviceIdQuery;
import com.dongtong.customer.service.CustomerService;
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
 * @date 2017/5/10 下午1:43.
 */
@Service
public class LoginAO {

    @Autowired(required = false)
    private CustomerService customerService;

    public ResultDO<LoginRespDTO> customerLogin(LoginReqQuery loginReqQuery){
        ResultDO<LoginRespDTO> resultDO = new ResultDO<LoginRespDTO>();
        String userPhone = loginReqQuery.getUserPhone();
        String smsVerifyCode = loginReqQuery.getSmsVerifyCode();
        Long messageId = loginReqQuery.getMessageId();
        String picVerifyCode = loginReqQuery.getPicVerifyCode();
        Long picVerifyId = loginReqQuery.getPicVerifyId();
        String inviteCode = loginReqQuery.getInviteCode();
        Integer osType = loginReqQuery.getOsType();
        String deviceId = loginReqQuery.getDeviceId();
        if (ValidateHelper.isEmpty(userPhone)
                || ValidateHelper.isEmpty(smsVerifyCode)
                || ValidateHelper.isEmpty(messageId)
                || ValidateHelper.isEmpty(osType)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        if (!StringUtils.isChinaPhoneLegal(userPhone)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.PHONE_FORMAT_EXCEPTION.getCode());
            resultDO.setErrMsg(ErrorConstant.PHONE_FORMAT_EXCEPTION.getMsg());
            return resultDO;
        }
        return customerService.customerLogin(userPhone, smsVerifyCode, messageId, picVerifyCode, picVerifyId, inviteCode, osType, deviceId);
    }

    public ResultDO<LoginRespDTO> customerLogout(){
        ResultDO<LoginRespDTO> resultDO = new ResultDO<LoginRespDTO>();
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        String token = HttpSessionUtils.getCurrentAppUserToken();
        if (ValidateHelper.isEmpty(customerId) || ValidateHelper.isEmpty(token)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        return customerService.customerLogout(customerId, token);
    }

    public ResultDO<LoginRespDTO> updateDeviceId(UpdateDeviceIdQuery updateDeviceIdQuery){
        ResultDO<LoginRespDTO> resultDO = new ResultDO<LoginRespDTO>();
        String deviceId = updateDeviceIdQuery.getDeviceId();
        if (ValidateHelper.isEmpty(deviceId)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        if (customerId == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        return customerService.updateDeviceId(customerId, deviceId);
    }

}
