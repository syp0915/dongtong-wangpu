package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseShopNumberMapper;
import com.dongtong.basic.domain.BaseShopNumber;
import com.shfc.common.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @Package com.dongtong.basic.manager.BaseShopNumberManager
 * @Description: 二维码管理
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/6/13 11:10
 * version V1.0.0
 */
@Service
public class BaseShopNumberManager {
    @Autowired
    private BaseShopNumberMapper baseShopNumberMapper;

    /**
     * 修改二维码状态为已使用
     * add by xiehb
     * @param shopNum
     * @return
     */
    @Transactional(rollbackFor = AppException.class)
    public Integer updateShopNumber(String shopNum) throws AppException{
        List<BaseShopNumber> list =selectUnusedByShopNum(shopNum);
        if(!list.isEmpty()){
            BaseShopNumber baseShopNumber = list.get(0);
            baseShopNumber.setStatus(1);
            return baseShopNumberMapper.updateByPrimaryKeySelective(baseShopNumber);
        }
        return null;
    }

    /**
     * 查询未被使用的二维码编号
     * @param shopNum
     * @return
     */
    public List<BaseShopNumber> selectUnusedByShopNum(String shopNum){
        return baseShopNumberMapper.selectUnusedByShopNum(shopNum);
    }

    /**
     * 批量插入二维码
     * @param shopNumber
     * @return
     */
    public int bathInsertShopNumber(Set<String> shopNumber){
        return baseShopNumberMapper.insertSet(shopNumber);
    }

    /**
     * 单条插入二维码
     * @param shopNumber
     * @return
     */
    public int insertShopNumber(String shopNumber){
        BaseShopNumber record = new BaseShopNumber();
        record.setShopNum(shopNumber);
        record.setStatus(0);
        return baseShopNumberMapper.insert(record);
    }
}
