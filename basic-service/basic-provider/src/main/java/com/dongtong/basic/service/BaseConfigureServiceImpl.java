package com.dongtong.basic.service;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.domain.BaseConfigure;
import com.dongtong.basic.manager.BaseConfigureManager;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.service
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-06-22 16:41
 * version V1.0.0
 **/
@Service
public class BaseConfigureServiceImpl implements BaseConfigureService{

    @Autowired
    private BaseConfigureManager baseConfigureManager;

    /**
     * 根据code查询配置参数值
     * @param code
     * @return
     */
    @Override
    public ResultDO<BaseConfigure> queryCustomerServiceTel(Long code) {
        ResultDO<BaseConfigure> resultDO=new ResultDO<>();
        if(ValidateHelper.isEmpty(code)){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
        }
        BaseConfigure baseConfigure=baseConfigureManager.queryCustomerServiceTel(code);
        resultDO.setSuccess(true);
        resultDO.setData(baseConfigure);
        return resultDO;
    }
}
