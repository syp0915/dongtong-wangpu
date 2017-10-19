package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.basic.dto.resp.AppUpdateRespDTO;
import com.dongtong.basic.query.AppUpdateQuery;
import com.dongtong.basic.service.AppService;
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
 * @date 2017/6/14 下午1:49.
 */
@Service
public class AppAO {

    @Autowired(required = false)
    private AppService appService;

    public ResultDO<AppUpdateRespDTO> queryAppUpdate(AppUpdateQuery query){
        ResultDO<AppUpdateRespDTO> resultDO = new ResultDO<>();
        Integer osType = query.getOsType();
        String version = query.getVersion();
        if (ValidateHelper.isEmpty(osType) || ValidateHelper.isEmpty(version)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        return appService.queryAppUpdate(1, osType, version);
    }
}
