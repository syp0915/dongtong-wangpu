package com.dongtong.basic.enums;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.enums
 * @Description：工作通知中的服务类型 （0-实堪 1-约看 2-签约  ）
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-09 10:53
 * version V1.0.0
 **/
public enum WorkServiceType {
    FIELD_TRIP("实堪",0),
    ORDER_SEE("约看",1),
    SIGN_CONTRACT("签约",2),
    WEEK("周榜单", 3),
    MONTH("月榜单", 4),
    COMMENT_NOTICE("帖子被评价",5),
    RECEIVE_CLUE("线索转到业务员名下",6),
    SCRAP_CLUE("线索被废弃",7),
    TIMEOUT_CLUE("线索跟进即将超时",8),
    HITE("线索",9);


    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    WorkServiceType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (WorkServiceType type : WorkServiceType.values()) {
                if (type.value == value) {
                    return type.name;
                }
            }
        }
        return "";
    }

    public WorkServiceType getTypeByValue(int value) {
        for (WorkServiceType type : WorkServiceType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

}
