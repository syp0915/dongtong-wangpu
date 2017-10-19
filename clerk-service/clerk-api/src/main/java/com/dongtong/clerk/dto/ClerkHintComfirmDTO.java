package com.dongtong.clerk.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.clerk.dto.ClerkHintComfirmDTO
 * @Description: 线索操作DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/2 17:04
 * version V1.0.0
 */
public class ClerkHintComfirmDTO implements Serializable {

	/**
	 * 线索编号
	 */
	private Long hintId;

	/**
	 * 业务员编号
	 */
	private Long clerkId;

	public Long getHintId() {
		return hintId;
	}

	public void setHintId(Long hintId) {
		this.hintId = hintId;
	}

	public Long getClerkId() {
		return clerkId;
	}

	public void setClerkId(Long clerkId) {
		this.clerkId = clerkId;
	}
}
