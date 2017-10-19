package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.dto.resp.AliyunSTSRespDTO;
import com.dongtong.basic.query.AliyunSTSQuery;
import com.dongtong.basic.service.AliyunService;
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
 * @date 2017/5/25 下午6:07.
 */
@Service
public class AliyunAO {

    @Autowired(required = false)
    private AliyunService aliyunService;

    public ResultDO<AliyunSTSRespDTO> getTmpAccessInfo(AliyunSTSQuery query) {
        ResultDO<AliyunSTSRespDTO> resultDO = new ResultDO<AliyunSTSRespDTO>();
        String osType = query.getOsType();
        if (ValidateHelper.isEmpty(osType)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        Long customerId = HttpSessionUtils.getCurrentAppUserId();
        return aliyunService.getTmpAccessInfo("customer", osType, customerId);
    }
}
