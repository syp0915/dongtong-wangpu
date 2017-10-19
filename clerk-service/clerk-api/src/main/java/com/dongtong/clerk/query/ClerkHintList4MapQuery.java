package com.dongtong.clerk.query;

import java.util.List;

/**
 * @Package com.dongtong.clerk.query.ClerkHintList4MapQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/15 13:48
 * version V1.0.0
 */
public class ClerkHintList4MapQuery extends BaseQuery {
	/**
	 * 板块ID
	 */
	private Long blockId;

	/**
	 * 最小经度
	 */
	private String minLon;

	/**
	 * 最大经度
	 */
	private String maxLon;

	/**
	 * 最小纬度
	 */
	private String minLat;

	/**
	 * 最大纬度
	 */
	private String maxLat;

	/**
	 * 线索归属类型 1:全部 2:我的
	 */
	private Integer ownType;

	/**
	 * 线索状态列表 0:待认领 1:待实勘 2:已实勘
	 */
	private List statusList;

	/**
	 * 拥有者ID
	 */
	private Long ownerId;

	/**
	 * 线索来源 1:扫街 2:客户 3:网站
	 */
	private List hintFromList;

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public String getMinLon() {
		return minLon;
	}

	public void setMinLon(String minLon) {
		this.minLon = minLon;
	}

	public String getMaxLon() {
		return maxLon;
	}

	public void setMaxLon(String maxLon) {
		this.maxLon = maxLon;
	}

	public String getMinLat() {
		return minLat;
	}

	public void setMinLat(String minLat) {
		this.minLat = minLat;
	}

	public String getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(String maxLat) {
		this.maxLat = maxLat;
	}

	public Integer getOwnType() {
		return ownType;
	}

	public void setOwnType(Integer ownType) {
		this.ownType = ownType;
	}

	public List getStatusList() {
		return statusList;
	}

	public void setStatusList(List statusList) {
		this.statusList = statusList;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public List getHintFromList() {
		return hintFromList;
	}

	public void setHintFromList(List hintFromList) {
		this.hintFromList = hintFromList;
	}
}
