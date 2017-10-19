package com.dongtong.clerk.dto;

import com.dongtong.clerk.util.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package com.dongtong.clerk.dto.ClerkHintFollowDTO
 * @Description: 线索跟进DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/2 17:14
 * version V1.0.0
 */
public class ClerkHintFollowDTO implements Serializable {

	private Long id;

	/**
	 * 业务员编号
	 */
	private Long clerkId;

	/**
	 * 业务员名称
	 */
	private String clerkName;

	/**
	 * 线索ID
	 */
	private Long hintId;

	/**
	 * 业务员头像
	 */
	private String clerkImg;

	/**
	 * 跟进内容
	 */
	private String content;

	/**
	 * 跟进时间展示
	 */
	private String followTime;

	/**
	 * 跟进时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClerkImg() {
		return clerkImg;
	}

	public void setClerkImg(String clerkImg) {
		this.clerkImg = clerkImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFollowTime() {
		return followTime;
	}

	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}

	public Long getClerkId() {
		return clerkId;
	}

	public void setClerkId(Long clerkId) {
		this.clerkId = clerkId;
	}

	public Long getHintId() {
		return hintId;
	}

	public void setHintId(Long hintId) {
		this.hintId = hintId;
	}

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		if(createTime != null){
			setFollowTime(DateFormatUtils.parseDateShow(createTime));
		}
	}
}
