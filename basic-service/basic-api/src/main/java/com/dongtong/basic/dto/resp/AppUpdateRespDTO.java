package com.dongtong.basic.dto.resp;

import java.io.Serializable;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/6/12 下午8:28.
 */
public class AppUpdateRespDTO implements Serializable {

    private Boolean needUpdate;//	是否需要更新
    private Integer updateLevel;//	更新等级0-静默 1-非强制更新 2-强制更新
    private String title;//更新标题
    private String desc;//	更新描述
    private String downloadUrl;//	app下载地址
    private String version;//	最新版本号
    private String servicePhone;//服务电话

    public Boolean getNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(Boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    public Integer getUpdateLevel() {
        return updateLevel;
    }

    public void setUpdateLevel(Integer updateLevel) {
        this.updateLevel = updateLevel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }
}
