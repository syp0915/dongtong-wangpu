package com.dongtong.shop.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.clerk.dto.DistractBlockDTO
 * @Description: 区域板块信息DTO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/11 17:37
 * version V1.0.0
 */
public class DistrictBlockDTO implements Serializable{

	private Long districtId;

	private String districtName;

	private Long blockId;

	private String blockName;

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
}
