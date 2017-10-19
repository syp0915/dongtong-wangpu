package com.dongtong.clerk.query;

/**
 * @Package com.dongtong.clerk.query.CustomerSignQuery
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/12 9:19
 * version V1.0.0
 */
public class CustomerSignInQuery extends BaseQuery{

	/**
	 * 用户编号
	 */
	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}
