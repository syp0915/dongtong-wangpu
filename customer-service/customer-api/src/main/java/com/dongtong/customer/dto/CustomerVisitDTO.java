package com.dongtong.customer.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 * h5用户约看
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-23 11:09
 **/
@Data
public class CustomerVisitDTO implements Serializable {
    private String contactMobile;//手机号
    private String smsVerifyCode;//短信验证码
    private String picVerifyCode;//图片验证码
    private Long messageId;//消息id
    private Long picVerifyId;//图片验证码id
    private String inviteCode;//邀请码
    private Integer osType;//0-iOS 1-Android
    private String deviceId;//设备id

    private Long shopId;
    private String contactName;

    private String subscribeTime;

}
