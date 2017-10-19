package com.dongtong.clerk.constant;

/**
 * @Package com.dongtong.clerk.constant.ClerkHintStatusEnum
 * @Description: 线索来源枚举 1:扫街 2:客户 3:网站
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/9 15:51
 * version V1.0.0
 */
public enum ClerkHintFromEnum {
	STREET(1,"扫街"),
	CUSTOMER(2,"客户"),
	INTERNET(3,"网站")
	;

	private final int value;
	private final String name;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ClerkHintFromEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(Integer val) {
		if (val != null) {
			int value = val;
			for (ClerkHintFromEnum constant : ClerkHintFromEnum.values()) {
				if (constant.value == value) {
					return constant.name;
				}
			}
		}
		return "";
	}

	public ClerkHintFromEnum getTypeByValue(int value) {
		for (ClerkHintFromEnum constant : ClerkHintFromEnum.values()) {
			if (constant.value == value) {
				return constant;
			}
		}
		return null;
	}
}
