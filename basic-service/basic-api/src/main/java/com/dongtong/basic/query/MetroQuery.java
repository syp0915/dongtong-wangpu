package com.dongtong.basic.query;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-08 13:59
 **/
public class MetroQuery implements Serializable{

    private static final long serialVersionUID = -5019206546662791474L;
    private String cityId;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
