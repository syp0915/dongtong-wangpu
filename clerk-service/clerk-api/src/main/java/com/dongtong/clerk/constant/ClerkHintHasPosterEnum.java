package com.dongtong.clerk.constant;

/**
 * @Package com.dongtong.clerk.constant.ClerkHintStatusEnum
 * @Description: 是否张贴海报枚举 1:张贴 0:不张贴
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/9 15:51
 * version V1.0.0
 */
public enum ClerkHintHasPosterEnum {
	YES(1,"是"),
	NO(0,"否")
	;

	private final int value;
	private final String name;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ClerkHintHasPosterEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(Integer val) {
		if (val != null) {
			int value = val;
			for (ClerkHintHasPosterEnum constant : ClerkHintHasPosterEnum.values()) {
				if (constant.value == value) {
					return constant.name;
				}
			}
		}
		return "";
	}

	public ClerkHintHasPosterEnum getTypeByValue(int value) {
		for (ClerkHintHasPosterEnum constant : ClerkHintHasPosterEnum.values()) {
			if (constant.value == value) {
				return constant;
			}
		}
		return null;
	}
}
