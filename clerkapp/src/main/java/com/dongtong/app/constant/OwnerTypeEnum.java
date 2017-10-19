package com.dongtong.app.constant;

/**
 * @Package com.dongtong.app.constant.OwnerTypeEnum
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/27 13:32
 * version V1.0.0
 */
public enum OwnerTypeEnum {
	ALL(1,"全部"),
	MY(2,"我的");
	private final int value;
	private final String name;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	OwnerTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(Integer val) {
		if (val != null) {
			int value = val;
			for (OwnerTypeEnum constant : OwnerTypeEnum.values()) {
				if (constant.value == value) {
					return constant.name;
				}
			}
		}
		return "";
	}

	public OwnerTypeEnum getTypeByValue(int value) {
		for (OwnerTypeEnum constant : OwnerTypeEnum.values()) {
			if (constant.value == value) {
				return constant;
			}
		}
		return null;
	}
}
