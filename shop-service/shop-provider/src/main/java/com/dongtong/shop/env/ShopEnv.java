package com.dongtong.shop.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Package com.dongtong.shop.env.ShopEnv
 * @Description: 环境参数
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/14 12:38
 * version V1.0.0
 */
@Component
public class ShopEnv {
    @Value("${shop.img.score}")
    private float imgScore;
    @Value("${shop.img.max.score}")
    private float imgMaxScore;

    @Value("${shop.near.score}")
    private float nearScore;
    @Value("${shop.near.max.score}")
    private float nearMaxScore;

    @Value("${shop.base.score}")
    private float baseScore;
    @Value("${shop.base.max.score}")
    private float baseMaxScore;

    @Value("${shop.operate.score}")
    private float operateScore;
    @Value("${shop.operate.max.score}")
    private float operateMaxScore;

    @Value("${shop.support.score}")
    private float supportScore;
    @Value("${shop.support.max.score}")
    private float supportMaxScore;

    @Value("${globalapp.url}")
    private String globalappUrl;

    public float getImgScore() {
        return imgScore;
    }

    public void setImgScore(float imgScore) {
        this.imgScore = imgScore;
    }

    public float getImgMaxScore() {
        return imgMaxScore;
    }

    public void setImgMaxScore(float imgMaxScore) {
        this.imgMaxScore = imgMaxScore;
    }

    public float getNearScore() {
        return nearScore;
    }

    public void setNearScore(float nearScore) {
        this.nearScore = nearScore;
    }

    public float getNearMaxScore() {
        return nearMaxScore;
    }

    public void setNearMaxScore(float nearMaxScore) {
        this.nearMaxScore = nearMaxScore;
    }

    public float getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(float baseScore) {
        this.baseScore = baseScore;
    }

    public float getBaseMaxScore() {
        return baseMaxScore;
    }

    public void setBaseMaxScore(float baseMaxScore) {
        this.baseMaxScore = baseMaxScore;
    }

    public float getOperateScore() {
        return operateScore;
    }

    public void setOperateScore(float operateScore) {
        this.operateScore = operateScore;
    }

    public float getOperateMaxScore() {
        return operateMaxScore;
    }

    public void setOperateMaxScore(float operateMaxScore) {
        this.operateMaxScore = operateMaxScore;
    }

    public float getSupportScore() {
        return supportScore;
    }

    public void setSupportScore(float supportScore) {
        this.supportScore = supportScore;
    }

    public float getSupportMaxScore() {
        return supportMaxScore;
    }

    public void setSupportMaxScore(float supportMaxScore) {
        this.supportMaxScore = supportMaxScore;
    }

    public String getGlobalappUrl() {
        return globalappUrl;
    }

    public void setGlobalappUrl(String globalappUrl) {
        this.globalappUrl = globalappUrl;
    }
}
