package com.dongtong.clerk.dto;

import com.shfc.mybatis.pagination.Page;

import java.io.Serializable;

/**
 * @Package com.dongtong.clerk.dto.ClerkTaskDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/10 17:14
 * version V1.0.0
 */
public class ClerkTaskDTO implements Serializable{

	/**
	 * 商铺地址
	 */
	private String address;

	/**
	 * 商铺面积
	 */
	private String area;

	/**
	 * 代办事项列表
	 */
	private Page<ClerkTaskDetailDTO> pendingList;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Page<ClerkTaskDetailDTO> getPendingList() {
		return pendingList;
	}

	public void setPendingList(Page<ClerkTaskDetailDTO> pendingList) {
		this.pendingList = pendingList;
	}
}
