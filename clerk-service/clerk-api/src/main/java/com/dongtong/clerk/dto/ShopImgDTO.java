package com.dongtong.clerk.dto;

import java.io.Serializable;

/**
 * @Package com.dongtong.clerk.dto.ShopImgDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/14 16:40
 * version V1.0.0
 */
public class ShopImgDTO implements Serializable {
	/**
	 * 图片路径
	 */
	private String imgUrl;

	/**
	 * 图片顺序
	 */
	private Integer imgIndex;

	/**
	 * 是否封面
	 */
	private Integer isCover;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getImgIndex() {
		return imgIndex;
	}

	public void setImgIndex(Integer imgIndex) {
		this.imgIndex = imgIndex;
	}

	public Integer getIsCover() {
		return isCover;
	}

	public void setIsCover(Integer isCover) {
		this.isCover = isCover;
	}
}
