package com.dongtong.clerk.util;

import com.shfc.common.base.Logger;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @Package com.dongtong.shfc.util.HttpUtil
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2017
 * Author wliao
 * @date 2017/6/22 13:46
 * version V1.0.0
 */
public class HttpUtil {

    public static String post(String url, Map<String, String> map) throws IOException {
        OkHttpClient client = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();
        if (map!=null&&!map.isEmpty()){
            for(Map.Entry<String,String> entry:map.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
        }

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()){
                String result = response.body().string();
                Logger.info(HttpUtil.class,"receive request data----------->" + result);
                return result;
            }else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            Logger.error(HttpUtil.class,"post请求异常：" + e.getMessage(),e);
//            e.printStackTrace();
        }
        return null;

    }

    public static String get(String url) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String resp = response.body().string();
            Logger.info(HttpUtil.class,"receive get request data:"+resp);
            return resp;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static void main(String[] args) throws IOException {
        String URL = "http://ga.wangpu888.com/globalapp/api/point/calculate/v1.0.0?lat=31&lng=121";
        System.out.println(get(URL));
    }
}
