package com.dongtong.clerk.constant;

/**
 * @Package com.dongtong.clerk.constant.IssuerTypeEnum
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/9 15:46
 * version V1.0.0
 */
public enum IssuerTypeEnum {
	CUSTEMER(0,"用户"),
	CLERK(1,"业务员")
	;

	private final int value;
	private final String name;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	IssuerTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(Integer val) {
		if (val != null) {
			int value = val;
			for (IssuerTypeEnum constant : IssuerTypeEnum.values()) {
				if (constant.value == value) {
					return constant.name;
				}
			}
		}
		return "";
	}

	public IssuerTypeEnum getTypeByValue(int value) {
		for (IssuerTypeEnum constant : IssuerTypeEnum.values()) {
			if (constant.value == value) {
				return constant;
			}
		}
		return null;
	}
}
