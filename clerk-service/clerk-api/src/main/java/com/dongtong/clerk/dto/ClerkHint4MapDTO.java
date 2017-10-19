package com.dongtong.clerk.dto;

import com.dongtong.clerk.util.DateFormatUtils;

import java.io.Serializable;

/**
 * @Package com.dongtong.clerk.dto.ClerkHint4MapDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/15 14:14
 * version V1.0.0
 */
public class ClerkHint4MapDTO implements Serializable{

	/**
	 * 线索ID
	 */
	private Long clerkId;

	/**
	 * 经度
	 */
	private String latitude;

	/**
	 * 纬度
	 */
	private String longitude;

	public Long getClerkId() {
		return clerkId;
	}

	public void setClerkId(Long clerkId) {
		this.clerkId = clerkId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
