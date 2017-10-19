package com.dongtong.basic.service;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.dao.AppVersionUpdateMapper;
import com.dongtong.basic.domain.AppVersionUpdate;
import com.dongtong.basic.domain.BaseConfigure;
import com.dongtong.basic.dto.resp.AppUpdateRespDTO;
import com.dongtong.basic.manager.BaseConfigureManager;
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
 * @date 2017/6/13 下午4:11.
 */
@Service
public class AppServiceImpl implements AppService {

    @Autowired(required = false)
    private AppVersionUpdateMapper appVersionUpdateMapper;

    @Autowired(required = false)
    private BaseConfigureManager baseConfigureManager;

    /**
     * 获取app版本更新信息
     *
     * @param osType
     * @param version
     * @return
     */
    @Override
    public ResultDO<AppUpdateRespDTO> queryAppUpdate(Integer type, Integer osType, String version) {
        ResultDO<AppUpdateRespDTO> resultDO = new ResultDO<>();
        if (ValidateHelper.isEmpty(osType) || ValidateHelper.isEmpty(version)){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        AppVersionUpdate appVersionUpdate = appVersionUpdateMapper.findByOsType(type, osType);
        if (appVersionUpdate == null){
            resultDO.setSuccess(false);
            resultDO.setErrCode(ErrorConstant.OBJECT_NOT_EXIST.getCode());
            resultDO.setErrMsg(ErrorConstant.OBJECT_NOT_EXIST.getMsg());
            return resultDO;
        }
        AppUpdateRespDTO appUpdateRespDTO = new AppUpdateRespDTO();
        appUpdateRespDTO.setVersion(appVersionUpdate.getVersionName());
        if(version.compareTo(appVersionUpdate.getVersionName())>=0){
            appUpdateRespDTO.setNeedUpdate(false);
        }else {
            appUpdateRespDTO.setNeedUpdate(true);
            appUpdateRespDTO.setUpdateLevel(appVersionUpdate.getUpdateLevel());
            appUpdateRespDTO.setTitle(appVersionUpdate.getTitle());
            appUpdateRespDTO.setDesc(appVersionUpdate.getDesc());
            appUpdateRespDTO.setDownloadUrl(appVersionUpdate.getDownUrl());
        }
        BaseConfigure baseConfigure = baseConfigureManager.queryCustomerServiceTel(0L);
        if (baseConfigure != null){
            appUpdateRespDTO.setServicePhone(baseConfigure.getValue());
        }
        resultDO.setSuccess(true);
        resultDO.setErrCode(ErrorConstant.SUCCESS.getCode());
        resultDO.setErrMsg(ErrorConstant.SUCCESS.getMsg());
        resultDO.setData(appUpdateRespDTO);
        return resultDO;
    }
}
