package com.dongtong.clerk.query;

import java.io.Serializable;

/**
 * @Package com.dongtong.clerk.query.ClerkHintQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/11 13:26
 * version V1.0.0
 */
public class ClerkHintQuery implements Serializable {

	/**
	 * 线索ID
	 */
	private Long clerkId;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 纬度
	 */
	private String latitude;

	public Long getClerkId() {
		return clerkId;
	}

	public void setClerkId(Long clerkId) {
		this.clerkId = clerkId;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
