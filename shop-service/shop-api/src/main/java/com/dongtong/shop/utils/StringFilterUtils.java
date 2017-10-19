package com.dongtong.shop.utils;

import com.shfc.common.base.ValidateHelper;

/**
 * @Package com.dongtong.shop.utils.StringFilterUtils
 * @Description: 字符串过滤工具
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/6/22 14:06
 * version V1.0.0
 */
public class StringFilterUtils {

    public static final String ADDRESS_PATTERN = "[\\u4E00-\\u9FA5]+[市]*[\\u4E00-\\u9FA5]+区";
    private static final String  PATTERN = "\\d+号";

    /**
     * 过滤地址中的市-区
     * @param address
     * @return
     */
    public static String addressFilter(String address){
        if(ValidateHelper.isEmpty(address)) return address;

        return roadFilter(address.replaceFirst(ADDRESS_PATTERN, ""));
    }

    /**
     * 道路过滤
     * @param address
     * @return
     */
    public static String roadFilter(String address){
        if(ValidateHelper.isEmpty(address)) return address;
        int index = address.indexOf("弄") > 0 ? address.indexOf("弄") : (address.indexOf("路") + address.indexOf("街")) + 1;
        address = address.substring(0, index + 1);
        return address.replaceAll(PATTERN, "");
    }

    public static void main(String[] args) {
        String[] arrs = {"紫藤路168弄9号(700啤酒屋旁/紫藤路上的弄堂里)",
                "新川路269号", "上海市浦东新区华夏东路1520弄-16号",
                "上海浦东新区南桥路1021号一1025号(近妙境路)",
                "鞍山支路19号;鞍山路310弄1~26号",
                "上海市杨浦区隆昌路532弄-1-6号",
                "长浜路501号29栋117-118号",
                "水电路1321弄10、12号",
                "上海市浦东新区北市街215",
                "商场西路附近"
                };

        for (int i = 0; i < arrs.length; i++) {
            String address = arrs[i];
            System.out.println(addressFilter(address));
        }
    }
}
