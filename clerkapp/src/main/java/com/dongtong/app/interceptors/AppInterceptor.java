package com.dongtong.app.interceptors;

import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.exception.AppWebException;
import com.dongtong.app.exception.ErrorConstant;
import com.dongtong.app.utils.AuthSessionUtils;
import com.dongtong.app.utils.HttpSessionUtils;
import com.fc.common.redis.RedisUtil;
import com.shfc.common.base.ValidateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Package com.dongtong.app.interceptors.AppInterceptor
 * @Description: AppInterceptor 拦截器
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 16:53
 * version V1.0.0
 */
@Slf4j
public class AppInterceptor extends HandlerInterceptorAdapter {

    @Value("${clerk.app.version}")
    private String appVersion;
    /**
     * X-token 有效时间 30天
     * 单位秒
     */
    @Value("${clerk.app.active.time}")
    private Long ACTIVE_TIME;

    /**
     * 默认请求request header 头部存放 token 名称
     */
    public String DEFAULT_TOKEN_NAME = "X-token";

    public static String REQUEST_TIME = "http_request_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute(REQUEST_TIME, new Date());

        String uri = request.getRequestURI();
        if (!uri.endsWith(appVersion)) {
            // 版本号错误
            throw new AppWebException(ErrorConstant.ERROR_VERSION.getCode(),
                    ErrorConstant.ERROR_VERSION.getMsg());
        }

        // 判断是否为json 请求
        String contentType = request.getContentType();
        if (!MediaType.APPLICATION_JSON_UTF8_VALUE.contains(contentType)) {
            throw new AppWebException(ErrorConstant.ERROR_MEDIA_TYPE.getCode(),
                    ErrorConstant.ERROR_MEDIA_TYPE.getMsg());
        }

        String tokenKey = request.getHeader(DEFAULT_TOKEN_NAME);
        log.info("tokenKey:{}",tokenKey);

        Object clerkId = RedisUtil.get("CLERK_" + tokenKey);
        log.info("clerkId:{}",RedisUtil.get("CLERK_" + tokenKey) );
        if(!ValidateHelper.isEmpty(tokenKey) && clerkId != null){
            // 用户存在，重新设置缓存失效时间
            RedisUtil.set(tokenKey, clerkId, ACTIVE_TIME);
            HttpSessionUtils.putObject(AuthSessionUtils.APP_CURRENT_USER_ID, clerkId);
            HttpSessionUtils.putObject(AuthSessionUtils.APP_CURRENT_USER_TOKEN, tokenKey);
        }

        if(handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            UnLoginLimit unlimited = method.getMethodAnnotation(UnLoginLimit.class);
            if(unlimited != null){
                // 免登陆接口
                return true;
            }else{
                // 需要登录接口
                if(ValidateHelper.isEmpty(tokenKey) || RedisUtil.get("CLERK_" + tokenKey) == null){
                    // 需要登录接口
                    throw new AppWebException(ErrorConstant.LOGIN_FIRST.getCode(),
                            ErrorConstant.LOGIN_FIRST.getMsg());
                }
            }
        }

        return super.preHandle(request, response, handler);
    }
}
