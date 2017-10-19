package com.dongtong.basic.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.basic.domain.AppVersionUpdate.java
 * @Description: app更新信息表
 * @Company: 东同电子
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author Jianguo Li
 * @date 2017/06/22 15:52
 * version v1.0.0
 */
public class AppVersionUpdate extends BaseBean {
    /**
     * 终端类型0-用户端 1-业务端
     */
    private Integer type;

    /**
     * 系统类型0-iOS 1-Android
     */
    private Integer osType;

    /**
     * 版本代码
     */
    private Integer versionCode;

    /**
     * 版本号
     */
    private String versionName;

    /**
     * 更新提示标题
     */
    private String title;

    /**
     * 更新等级0-静默 1-非强制提醒更新 2-强制更新
     */
    private Integer updateLevel;

    private Long creator;

    /**
     * 更新描述
     */
    private String desc;

    /**
     * 安装包下载url
     */
    private String downUrl;

    /**
     * 获取终端类型0-用户端 1-业务端
     *
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置终端类型0-用户端 1-业务端
     *
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取系统类型0-iOS 1-Android
     *
     * @return os_type
     */
    public Integer getOsType() {
        return osType;
    }

    /**
     * 设置系统类型0-iOS 1-Android
     *
     * @param osType
     */
    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    /**
     * 获取版本代码
     *
     * @return version_code
     */
    public Integer getVersionCode() {
        return versionCode;
    }

    /**
     * 设置版本代码
     *
     * @param versionCode
     */
    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * 获取版本号
     *
     * @return version_name
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * 设置版本号
     *
     * @param versionName
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    /**
     * 获取更新提示标题
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置更新提示标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取更新等级0-静默 1-非强制提醒更新 2-强制更新
     *
     * @return update_level
     */
    public Integer getUpdateLevel() {
        return updateLevel;
    }

    /**
     * 设置更新等级0-静默 1-非强制提醒更新 2-强制更新
     *
     * @param updateLevel
     */
    public void setUpdateLevel(Integer updateLevel) {
        this.updateLevel = updateLevel;
    }

    /**
     * @return creator
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * 获取更新描述
     *
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置更新描述
     *
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * 获取安装包下载url
     *
     * @return down_url
     */
    public String getDownUrl() {
        return downUrl;
    }

    /**
     * 设置安装包下载url
     *
     * @param downUrl
     */
    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl == null ? null : downUrl.trim();
    }

    /**
     * @Title toString
     * @Author Jianguo Li
     * @Date 2017/06/22 15:52
     * @return java.lang.String
     * @throws []
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", type=").append(type);
        sb.append(", osType=").append(osType);
        sb.append(", versionCode=").append(versionCode);
        sb.append(", versionName=").append(versionName);
        sb.append(", title=").append(title);
        sb.append(", updateLevel=").append(updateLevel);
        sb.append(", creator=").append(creator);
        sb.append(", desc=").append(desc);
        sb.append(", downUrl=").append(downUrl);
        sb.append("]");
        return sb.toString();
    }
}