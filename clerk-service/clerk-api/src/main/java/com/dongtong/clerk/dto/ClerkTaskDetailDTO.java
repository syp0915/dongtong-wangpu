package com.dongtong.clerk.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.clerk.dto.ClerkTaskDetailDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/10 17:21
 * version V1.0.0
 */
public class ClerkTaskDetailDTO implements Serializable{

	/**
	 * 代办类型 	0:线索 1:约看 2:签约
	 */
	private String type;

	/**
	 * 是否本人代办 0:否 1:是
	 */
	private String isOwn;

	/**
	 * 用户名
	 */
	private String customerName;

	/**
	 * 用户手机
	 */
	private String phone;

	/**
	 * 约见时间
	 */
	private String meetTime;

	/**
	 * 业务id
	 */
	private Long bizId;

	/**
	 * 商铺id
	 */
	private Long shopId;

	/**
	 * 状态
	 * 线索：0-待认领 1-待实勘 2-已实勘已收铺 3-已实勘已取消
	 * 约看：0-待约看 1-已约看 2-已取消
	 * 签约：0-待签约 1-已签约未上传合同 2-已签约已上传合同 9-已取消
	 */
	private Integer status;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsOwn() {
		return isOwn;
	}

	public void setIsOwn(String isOwn) {
		this.isOwn = isOwn;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMeetTime() {
		return meetTime;
	}

	public void setMeetTime(String meetTime) {
		this.meetTime = meetTime;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}
