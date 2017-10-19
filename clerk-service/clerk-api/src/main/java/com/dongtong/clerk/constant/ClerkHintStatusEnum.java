package com.dongtong.clerk.constant;

/**
 * @Package com.dongtong.clerk.constant.ClerkHintStatusEnum
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/9 15:51
 * version V1.0.0
 */
public enum ClerkHintStatusEnum {
	WAITTING_CLAIM(0,"拓铺员待认领"),
	WAITING_CONFIRM(1,"拓铺员待确认"),
	WAITING_TRADE_CLAIM(2,"交易员待认领"),
	WAITTING_EXAMINE_SPOT(3,"待实勘"),
	EXAMINE_SPOT_FINISHED(4,"已转化"),
	CANCELED(5,"已废弃"),
	DISCARD(6,"已撤销")
	;

	private final int value;
	private final String name;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ClerkHintStatusEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(Integer val) {
		if (val != null) {
			int value = val;
			for (ClerkHintStatusEnum constant : ClerkHintStatusEnum.values()) {
				if (constant.value == value) {
					return constant.name;
				}
			}
		}
		return "";
	}

	public ClerkHintStatusEnum getTypeByValue(int value) {
		for (ClerkHintStatusEnum constant : ClerkHintStatusEnum.values()) {
			if (constant.value == value) {
				return constant;
			}
		}
		return null;
	}
}
