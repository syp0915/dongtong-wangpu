package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-12 15:51
 **/
public class CustomerServiceDTO implements Serializable {
    private LoadDTO loadObject;//贷款信息

    private Object shopObject;//最近店铺信息

    private Integer isNew;//是否新用户  0:否，1:是

    private Object visitShopObject;//最近一次约看

    public LoadDTO getLoadObject() {
        return loadObject;
    }

    public void setLoadObject(LoadDTO loadObject) {
        this.loadObject = loadObject;
    }

    public Object getShopObject() {
        return shopObject;
    }

    public void setShopObject(Object shopObject) {
        this.shopObject = shopObject;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Object getVisitShopObject() {
        return visitShopObject;
    }

    public void setVisitShopObject(Object visitShopObject) {
        this.visitShopObject = visitShopObject;
    }
}