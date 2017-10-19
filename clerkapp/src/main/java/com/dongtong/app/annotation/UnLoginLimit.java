package com.dongtong.app.annotation;

/**
 * @Package com.dongtong.app.annotation.UnLoginLimit
 * @Description: 不需要登录限制
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 16:50
 * version V1.0.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 免登陆接口
 * @author jon lv
 * @date
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UnLoginLimit {
}
