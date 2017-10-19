package com.dongtong.clerk.query;

/**
 * @Package com.dongtong.clerk.query.ClerkTaskQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/10 17:15
 * version V1.0.0
 */
public class ClerkTaskQuery extends BaseQuery {
	/**
	 * 商铺编号
	 */
	private Long shopId;

	/**
	 * 业务员编号
	 */
	private Long clerkId;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getClerkId() {
		return clerkId;
	}

	public void setClerkId(Long clerkId) {
		this.clerkId = clerkId;
	}
}
