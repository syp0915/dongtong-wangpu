package com.dongtong.app.utils;

import com.baidu.disconf.client.common.annotations.DisconfItem;
import com.shfc.common.base.Logger;
import com.shfc.common.disconf.BaseProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Package com.dongtong.app.utils.CustomerappProperties
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/18 15:39
 * version V1.0.0
 */
@Component
@Scope("singleton")
public class CustomerappProperties extends BaseProperties {
	private String customerAppVersion;

	private String activeTime;

	private String jdwxAppkey;

	private String jdwxUrl;

	private String jdwxAuth;

	private static Integer topicPublishOpenFlag;

	public String getCustomerAppVersion() {
		try {
			return this.getProperty("customer.app.version");
		} catch (IOException e) {
			Logger.error(CustomerappProperties.class, "获取参数customer.app.version异常",e);
			return "";
		}
	}

	public void setCustomerAppVersion(String customerAppVersion) {
		this.customerAppVersion = customerAppVersion;
	}

	public String getActiveTime() {
		try {
			return this.getProperty("customer.app.active.time");
		} catch (IOException e) {
			Logger.error(CustomerappProperties.class, "获取参数customer.app.active.time异常",e);
			return "";
		}
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public String getJdwxAppkey() {
		try {
			return this.getProperty("jdwx.appkey");
		} catch (IOException e) {
			Logger.error(CustomerappProperties.class, "获取参数jdwx.appkey异常",e);
			return "";
		}
	}

	public void setJdwxAppkey(String jdwxAppkey) {
		this.jdwxAppkey = jdwxAppkey;
	}

	public String getJdwxUrl() {
		try {
			return this.getProperty("jdwx.url");
		} catch (IOException e) {
			Logger.error(CustomerappProperties.class, "获取参数jdwx.url异常",e);
			return "";
		}
	}

	public void setJdwxUrl(String jdwxUrl) {
		this.jdwxUrl = jdwxUrl;
	}

	public String getJdwxAuth() {
		try {
			return this.getProperty("jdwx.auth");
		} catch (IOException e) {
			Logger.error(CustomerappProperties.class, "获取参数jdwx.auth异常",e);
			return "";
		}
	}

	public void setJdwxAuth(String jdwxAuth) {
		this.jdwxAuth = jdwxAuth;
	}

	/**
	 * 生意圈发帖开关
	 *
	 * @return
	 */
	@DisconfItem(key = "topic.publish.openFlag")
	public static Integer getTopicPublishOpenFlag() {
		return topicPublishOpenFlag;
	}

	public static void setTopicPublishOpenFlag(Integer topicPublishOpenFlag) {
		CustomerappProperties.topicPublishOpenFlag = topicPublishOpenFlag;
	}
}
