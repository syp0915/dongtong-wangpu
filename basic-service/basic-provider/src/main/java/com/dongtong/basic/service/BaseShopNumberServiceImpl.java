package com.dongtong.basic.service;

import com.dongtong.basic.domain.BaseShopNumber;
import com.dongtong.basic.manager.BaseShopNumberManager;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.exception.AppException;
import com.shfc.common.math.RandomUtils;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @Package com.dongtong.basic.service.BaseShopNumberServiceImpl
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/6/13 11:35
 * version V1.0.0
 */
@Service
public class BaseShopNumberServiceImpl implements BaseShopNumberService {
    @Autowired
    private BaseShopNumberManager baseShopNumberManager;
    @Override
    @Transactional(rollbackFor = AppException.class)
    public ResultDO<String> updateShopNumber(String shopNum){
        ResultDO<String> resultDO = new ResultDO<>();
        if(ValidateHelper.isEmptyString(shopNum)){
            resultDO.setErrMsg("二维码编号不能为空！");
            return resultDO;
        }
        Integer count  = null;
        try {
            count = baseShopNumberManager.updateShopNumber(shopNum);
            if(count!=null && count>0){
                resultDO.setSuccess(true);
                return resultDO;
            }
        } catch (Exception e) {
            Logger.error(this,"修改商铺二维码状态数据库异常！");
            resultDO.setErrMsg("修改商铺二维码状态数据库异常！");
            return resultDO;
        }
        Logger.error(this,"修改商铺二维码状态数据库异常！");
        resultDO.setErrMsg("修改商铺二维码状态数据库异常！");
        return resultDO;
    }

    @Override
    public List<BaseShopNumber> shopNumberList(String shopNum) {
        return baseShopNumberManager.selectUnusedByShopNum(shopNum);
    }

    @Override
    public int bathInsertShopNumber(Set<String> shopNumber) {
        return baseShopNumberManager.bathInsertShopNumber(shopNumber);
    }

    @Override
    public int insertSingleShopNumber(String shopNumber) {
        return baseShopNumberManager.insertShopNumber(shopNumber);
    }

    @Override
    public ResultDO<String> createShopNumber(String cityCode) {
        ResultDO<String> resultDO = new ResultDO<>();
        int randomLength = 4;
        if(ValidateHelper.isEmptyString(cityCode)){
            resultDO.setErrMsg("城市编号不能为空！");
            return resultDO;
        }else if(cityCode.length()>4){
            resultDO.setErrMsg("城市编号长度不能大于4位！");
            return resultDO;
        }else if(cityCode.length()==3){
            randomLength=randomLength+1;
        }
        StringBuilder sbf = new StringBuilder();
        sbf.append(cityCode).append(RandomUtils.generateLowerString(randomLength)).append(RandomUtils.generateNumberString(4));
        String shopNum = sbf.toString();
        try {
            List<BaseShopNumber> list = shopNumberList(shopNum);
            if(list==null || list.isEmpty()){
                int count  = baseShopNumberManager.insertShopNumber(shopNum);
                if(count==1){
                    resultDO.setData(shopNum);
                    resultDO.setSuccess(true);
                    return resultDO;
                }
            }
            return resultDO;
        }catch (Exception e){
            resultDO.setErrMsg("二维码插入异常，可能存在重复数据");
            return resultDO;
        }
    }
}
