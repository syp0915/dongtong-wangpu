package com.dongtong.basic.dto.resp;

import java.io.Serializable;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/4 下午1:58.
 */
public class PicVerifyRespDTO implements Serializable{
    private String picUrl;//验证码url
    private String picVerifyCode;//图片验证码内容
    private String picVerifyId;//图片验证码id
    private String base64Str;//base编码字符串

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicVerifyCode() {
        return picVerifyCode;
    }

    public void setPicVerifyCode(String picVerifyCode) {
        this.picVerifyCode = picVerifyCode;
    }

    public String getPicVerifyId() {
        return picVerifyId;
    }

    public void setPicVerifyId(String picVerifyId) {
        this.picVerifyId = picVerifyId;
    }

    public String getBase64Str() {
        return base64Str;
    }

    public void setBase64Str(String base64Str) {
        this.base64Str = base64Str;
    }
}
