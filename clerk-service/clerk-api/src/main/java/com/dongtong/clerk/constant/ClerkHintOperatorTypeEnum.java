package com.dongtong.clerk.constant;

/**
 * @Package com.dongtong.clerk.constant.ClerkHintOperatorTypeEnum
 * @Description: 线索操作日志类型枚举 1:客户发布线索2:拓铺员发布线索 3:拓铺员认领
 *    4:分配拓铺员 5:拓铺员确认有效 6:拓铺员确认无效 7:交易员认领 8:转化商铺 9:线索废弃
 *    10:线索取消 11:线索恢复12:线索确认超时 13:线索核实超时
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/9 15:51
 * version V1.0.0
 */
public enum ClerkHintOperatorTypeEnum {
	CUST_PUBLISH_HINT(1,"客户发布线索"),
	EXPAND_CLERK_PUBLISH_HINT(2,"拓铺员发布线索"),
	EXPAND_CLERK_CLAIM(3,"拓铺员认领"),
	ASSIGN_EXPAND_CLERK(4,"分配拓铺员"),
	EXPAND_CLERK_CONFIRM(5,"拓铺员确认有效"),
	EXPAND_CLERK_CONFIRM_INVALID(6,"拓铺员确认无效"),
	TRADE_CLAIM(7,"交易员认领"),
	CONVERT_SHOP(8,"转化商铺"),
	EXAMINE_SPOT_FINISHED(4,"已转化"),
	CANCELED(9,"线索废弃"),
	DISCARD(10,"线索取消"),
	HINT_RECOVERY(11,"线索恢复"),
	HINT_CONFIRM_OVERTIME(12,"线索确认超时"),
	HINT_CHECK_OVERTIME(13,"线索核实超时"),
	BATCH_IMPORT_HINT(14,"批量导入线索"),
	TRADE_CLERK_CONFIRM_INVALID(15,"交易员确认无效"),
	;

	private final int value;
	private final String name;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ClerkHintOperatorTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(Integer val) {
		if (val != null) {
			int value = val;
			for (ClerkHintOperatorTypeEnum constant : ClerkHintOperatorTypeEnum.values()) {
				if (constant.value == value) {
					return constant.name;
				}
			}
		}
		return "";
	}

	public ClerkHintOperatorTypeEnum getTypeByValue(int value) {
		for (ClerkHintOperatorTypeEnum constant : ClerkHintOperatorTypeEnum.values()) {
			if (constant.value == value) {
				return constant;
			}
		}
		return null;
	}
}
