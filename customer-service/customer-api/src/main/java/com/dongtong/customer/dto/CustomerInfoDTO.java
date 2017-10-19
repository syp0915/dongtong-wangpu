package com.dongtong.customer.dto;

import com.dongtong.customer.dto.resp.AttentionPlateRespDTO;
import com.dongtong.customer.dto.resp.AttentionRespDTO;
import com.dongtong.customer.dto.resp.AttentionVocationRespDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-10 10:27
 *
 * v1.2
 *   --去掉原有关注内容字段，将关注内容存放在AttentionRespDTO对象中
 *   --去掉关注面积字段，统一归入attention字段
 *   --新增昵称字段nickName
 *   --新增签名字段signature
 *   --新增是否被禁言字段isBanned
 *   --新增我的服务字段mySerivce
 **/
public class CustomerInfoDTO implements Serializable {
    private Long customerId;

    private String phone;

    private String headPortrait;//头像

    private AttentionRespDTO attention = new AttentionRespDTO();//关注--- v1.2修改

    private Integer isNew;//是否新用户 0:否，1:是

    private Integer isBanned;    //是否被禁言---v1.2新增

    private String nickName;     //昵称---v1.2新增

    private String signature;    //个性签名---v1.2新增

    private Long myService;     //我的服务---v1.2新增

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public AttentionRespDTO getAttention() {
        return attention;
    }

    public void setAttention(AttentionRespDTO attention) {
        this.attention = attention;
    }

    public Integer getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Integer isBanned) {
        this.isBanned = isBanned;
    }

    public Long getMyService() {
        return myService;
    }

    public void setMyService(Long myService) {
        this.myService = myService;
    }

    public List<AttentionVocationRespDTO> fetchVocationList() {
        return attention.getVocationList();
    }

    public void fetchVocationList(List<AttentionVocationRespDTO> vocationList) {
        attention.setVocationList(vocationList);
    }

    public List<AttentionPlateRespDTO> fetchPlateList() {
        return attention.getPlateList();
    }

    public void fetchPlateList(List<AttentionPlateRespDTO> plateList) {
        attention.setPlateList(plateList);
    }

    public List<Integer> fetchAreaList() {
        return attention.getAreaList();
    }

    public void fetchAreaList(List<Integer> areaList) {
        attention.setAreaList(areaList);
    }

    @Override
    public String toString() {
        return "CustomerInfoDTO{" +
                "customerId=" + customerId +
                ", phone='" + phone + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", attention=" + attention +
                ", isNew=" + isNew +
                ", isBanned=" + isBanned +
                ", nickName='" + nickName + '\'' +
                ", signature='" + signature + '\'' +
                ", myService='" + myService + '\'' +
                '}';
    }
}
