package com.dongtong.clerk.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.clerk.dto.ClerkHintStatisticsDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/9 10:55
 * version V1.0.0
 */
public class ClerkHintStatisticsDTO implements Serializable {

	/**
	 * 全部线索
	 */
	private Integer allHintCount;

	/**
	 * 我的线索
	 */
	private Integer myHintCount;

	public Integer getAllHintCount() {
		return allHintCount;
	}

	public void setAllHintCount(Integer allHintCount) {
		this.allHintCount = allHintCount;
	}

	public Integer getMyHintCount() {
		return myHintCount;
	}

	public void setMyHintCount(Integer myHintCount) {
		this.myHintCount = myHintCount;
	}
}
