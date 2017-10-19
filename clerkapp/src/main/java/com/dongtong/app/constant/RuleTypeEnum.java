package com.dongtong.app.constant;

/**
 * @Package com.dongtong.app.constant.RuleType
 * @Description: 角色枚举
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/6/26 16:30
 * version V1.0.0
 */
public enum RuleTypeEnum {
	TRADE_CLERK(0,"业务员（交易）"),
	MANAGER(1,"运营人员"),
	EXPAND_SHOP_CLERK(2,"拓铺员");
	private final int value;
	private final String name;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	RuleTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(Integer val) {
		if (val != null) {
			int value = val;
			for (RuleTypeEnum constant : RuleTypeEnum.values()) {
				if (constant.value == value) {
					return constant.name;
				}
			}
		}
		return "";
	}

	public RuleTypeEnum getTypeByValue(int value) {
		for (RuleTypeEnum constant : RuleTypeEnum.values()) {
			if (constant.value == value) {
				return constant;
			}
		}
		return null;
	}
}
