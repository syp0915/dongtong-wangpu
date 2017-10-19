package com.dongtong.clerk.constant;

import com.shfc.common.base.Logger;
import com.shfc.common.disconf.BaseProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Package com.dongtong.clerk.constant.ClerkProviderEnvProperties
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/8/11 18:01
 * version V1.0.0
 */
@Service
public class ClerkProviderEnvProperties  extends BaseProperties {

	private String specialPhone;

	private String globalappUrl;

	public String getGlobalappUrl() {
		try {
			return this.getProperty("globalapp.url");
		} catch (IOException e) {
			Logger.error(BaseProperties.class, "获取参数globalapp.url异常",e);
			return "";
		}
	}

	public String getSpecialPhone() {
		try {
			return this.getProperty("special.phone");
		} catch (IOException e) {
			Logger.error(BaseProperties.class, "获取参数special.phone 异常",e);
			return "";
		}
	}

	//	public String getRequestInterceptorOpenLogo() {
//		try {
//			return this.getProperty("request.interceptor.openLogo");
//		} catch (IOException e) {
//			Logger.error(BaseProperties.class, "获取参数request.interceptor.openLogo异常",e);
//			return "";
//		}
//	}
}
