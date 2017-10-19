package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

import java.util.Date;

/**
 * @Package: com.shfc.sms.domain.HistoryMonthRanking.java
 * @Description: 历史月排名
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author chen xiushen
 * @date 2017/05/12 13:06
 * version v1.0.0
 */
public class HistoryMonthRanking extends BaseBean {
    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 姓名
     */
    private String name;

    /**
     * 排名
     */
    private String ranking;

    /**
     * 数量
     */
    private String quantity;

    /**
     * 分类(0-收铺、1-踩盘、2-签约、3-注册)
     */
    private Integer classify;

    /**
     * 周期
     */
    private String period;

    /**
     * 周期开始时间
     */
    private Date periodBeginTime;

    /**
     * 周期结束时间
     */
    private Date periodEndTime;

    /**
     * 年份
     */
    private String year;

    private String dataTime;

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * 获取用户ID
     *
     * @return customer_id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置用户ID
     *
     * @param customerId
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取头像
     *
     * @return head_portrait
     */
    public String getHeadPortrait() {
        return headPortrait;
    }

    /**
     * 设置头像
     *
     * @param headPortrait
     */
    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait == null ? null : headPortrait.trim();
    }

    /**
     * 获取姓名
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取排名
     *
     * @return ranking
     */
    public String getRanking() {
        return ranking;
    }

    /**
     * 设置排名
     *
     * @param ranking
     */
    public void setRanking(String ranking) {
        this.ranking = ranking == null ? null : ranking.trim();
    }

    /**
     * 获取数量
     *
     * @return quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * 设置数量
     *
     * @param quantity
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity == null ? null : quantity.trim();
    }

    /**
     * 获取分类(0-收铺、1-踩盘、2-签约、3-注册)
     *
     * @return classify
     */
    public Integer getClassify() {
        return classify;
    }

    /**
     * 设置分类(0-收铺、1-踩盘、2-签约、3-注册)
     *
     * @param classify
     */
    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    /**
     * 获取周期
     *
     * @return period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * 设置周期
     *
     * @param period
     */
    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    /**
     * 获取周期开始时间
     *
     * @return period_begin_time
     */
    public Date getPeriodBeginTime() {
        return periodBeginTime;
    }

    /**
     * 设置周期开始时间
     *
     * @param periodBeginTime
     */
    public void setPeriodBeginTime(Date periodBeginTime) {
        this.periodBeginTime = periodBeginTime;
    }

    /**
     * 获取周期结束时间
     *
     * @return period_end_time
     */
    public Date getPeriodEndTime() {
        return periodEndTime;
    }

    /**
     * 设置周期结束时间
     *
     * @param periodEndTime
     */
    public void setPeriodEndTime(Date periodEndTime) {
        this.periodEndTime = periodEndTime;
    }

    /**
     * 获取年份
     *
     * @return year
     */
    public String getYear() {
        return year;
    }

    /**
     * 设置年份
     *
     * @param year
     */
    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }
}