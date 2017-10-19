package com.dongtong.customer.dto;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *贷款统计
 * @author wky
 * @version V1.0
 * @create 2017-05-15 15:22
 **/
public class LoanStatistics implements Serializable{
    private Integer people;

    private Integer sum;

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
