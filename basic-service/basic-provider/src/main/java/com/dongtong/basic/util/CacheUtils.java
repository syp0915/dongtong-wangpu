package com.dongtong.basic.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/10 下午5:34.
 */
public class CacheUtils {

    /**
     * 获取到今明日临界所剩余秒
     * @return
     */
    public static Long getExpireSeconds() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (calendar.getTime().getTime() - System.currentTimeMillis()) / 1000;
    }
}
