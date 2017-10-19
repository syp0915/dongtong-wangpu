package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseConfigureMapper;
import com.dongtong.basic.domain.BaseConfigure;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.manager
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-06-22 16:38
 * version V1.0.0
 **/
@Service
public class BaseConfigureManager {

    @Autowired
    private BaseConfigureMapper baseConfigureMapper;


    /**
     * 根据code查询配置参数值
     * @param code
     * @return
     */
   public BaseConfigure queryCustomerServiceTel(Long code){
       BaseConfigure baseConfigure=baseConfigureMapper.queryCustomerServiceTel(code);
       return baseConfigure;
   }
}
