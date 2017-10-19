package com.dongtong.customer.constant;

import com.shfc.common.base.Logger;
import com.shfc.common.disconf.BaseProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author sunyaping
 * @Package com.dongtong.customer.constant
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-08-31 11:12
 * version V1.0.0
 **/
@Service
public class CustomerProperties extends BaseProperties {

    private String specialPhone;

    public String getSpecialPhone() {
        try {
            return this.getProperty("special.phone");
        } catch (IOException e) {
            Logger.error(CustomerProperties.class, "获取参数special.phone 异常",e);
            return "";
        }
    }


}
