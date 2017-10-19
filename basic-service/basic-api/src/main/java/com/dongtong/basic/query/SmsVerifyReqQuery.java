package com.dongtong.basic.query;

import java.io.Serializable;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/4 上午10:35.
 */
public class SmsVerifyReqQuery implements Serializable {
    private String userPhone;//用户手机号
    private Integer sendType;//0-短信 1-语音
    private Integer useScene;//使用场景 0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getUseScene() {
        return useScene;
    }

    public void setUseScene(Integer useScene) {
        this.useScene = useScene;
    }
}
