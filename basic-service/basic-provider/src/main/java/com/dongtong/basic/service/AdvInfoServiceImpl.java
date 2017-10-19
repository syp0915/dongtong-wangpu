package com.dongtong.basic.service;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.dto.AdvInfoDTO;
import com.dongtong.basic.manager.AdvInfoManager;
import com.dongtong.basic.query.AdvQuery;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 19:31
 **/
@Service
public class AdvInfoServiceImpl implements AdvInfoService {
    @Autowired
    private AdvInfoManager advInfoManager;
    @Override
    public ResultDO<List<AdvInfoDTO>> advList(AdvQuery query) {
        ResultDO<List<AdvInfoDTO>> resultDO=new ResultDO<List<AdvInfoDTO>>();
        if(ValidateHelper.isEmpty(query.getPosition())){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        List<AdvInfoDTO> list=advInfoManager.advList(query.getPosition());

        resultDO.setData(list);
        resultDO.setSuccess(true);
        return resultDO;
    }

    public ResultDO<List<AdvInfoDTO>> adList(AdvQuery query) {
        ResultDO<List<AdvInfoDTO>> resultDO=new ResultDO<List<AdvInfoDTO>>();
        if(ValidateHelper.isEmpty(query.getType())){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;

        }
        if(query.getType()==3){//广告
            if(ValidateHelper.isEmpty(query.getPosition())){
                resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
                resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
                return resultDO;
            }
        }
        List<AdvInfoDTO> list=advInfoManager.adList(query.getPosition(),query.getType());

        resultDO.setData(list);
        resultDO.setSuccess(true);
        return resultDO;
    }

}
