package com.dongtong.clerk.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Package com.dongtong.clerk.dto.ClerkHintImgsDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/2 16:53
 * version V1.0.0
 */
public class ClerkHintImgsDTO implements Serializable {
	private Long id;

	/**
	 * 修改人编号
	 */
	private Long modifier;
	/**
	 * 图片列表
	 */
	private List<ShopImgDTO> shopImgList;

	public List<ShopImgDTO> getShopImgList() {
		return shopImgList;
	}

	public void setShopImgList(List<ShopImgDTO> shopImgList) {
		this.shopImgList = shopImgList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModifier() {
		return modifier;
	}

	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}
}
