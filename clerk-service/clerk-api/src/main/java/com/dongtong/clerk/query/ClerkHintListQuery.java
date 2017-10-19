package com.dongtong.clerk.query;

import java.util.List;

/**
 * @Package com.dongtong.clerk.query.ClerkHintListQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/12 18:34
 * version V1.0.0
 */
public class ClerkHintListQuery extends BaseQuery{
	/**
	 * 线索归属类型 1:全部 2:我的
	 */
	private Integer hintOwnType;

	/**
	 * 区域Id
	 */
	private String areaId;

	/**
	 * 板块ID
	 */
	private String blockId;

	/**
	 * 排序类型 1:按距离 2:按热度 3:更新时间
	 */
	private Integer orderType;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 纬度
	 */
	private String latitude;

	/**
	 * 状态 0:待认领 1:待实勘 2:已实勘已收铺 3:已实勘已取消
	 */
	private List statusList;

	/**
	 * 排序字段
	 */
	private String orderColumn;

	/**
	 * 拥有者ID
	 */
	private Long ownerId;

	/**
	 * 线索来源
	 */
	private List fromList;

	/**
	 * 拓铺员ID
	 */
	private Long expandClerkId;

	/**
	 * 交易员ID
	 */
	private Long tradeClerkId;

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public Integer getHintOwnType() {
		return hintOwnType;
	}

	public void setHintOwnType(Integer hintOwnType) {
		this.hintOwnType = hintOwnType;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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

	public List getStatusList() {
		return statusList;
	}

	public void setStatusList(List statusList) {
		this.statusList = statusList;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public List getFromList() {
		return fromList;
	}

	public void setFromList(List fromList) {
		this.fromList = fromList;
	}

	public Long getExpandClerkId() {
		return expandClerkId;
	}

	public void setExpandClerkId(Long expandClerkId) {
		this.expandClerkId = expandClerkId;
	}

	public Long getTradeClerkId() {
		return tradeClerkId;
	}

	public void setTradeClerkId(Long tradeClerkId) {
		this.tradeClerkId = tradeClerkId;
	}
}
