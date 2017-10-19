package com.dongtong.customer.service;

import com.shfc.common.encrypt.MD5Utils;
import org.junit.Test;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/23 下午2:25.
 */
public class TestGenerator {

    @Test
    public void generatorQrCode(){
        String baseUrl = "http://115.159.160.220/lmd/app/down/download.html?a=";
        String stakeNo = "1000578";
        String str2 = MD5Utils.encrypt(stakeNo, MD5Utils.MD5_KEY).toUpperCase();
        String str3 = str2.substring(0, 16);
        String str4 = str2.substring(16);
        char[] strArray = stakeNo.toCharArray();
        int sum = 0;
        for (int i = 0; i < strArray.length; i++) {
            sum += Integer.parseInt(strArray[i] + "");
        }
        String result = str3 + stakeNo + str4 + sum;
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        System.out.println(baseUrl + result);
    }


    @Test
    public void checkQrCode(){
        String url = "http://115.159.160.220/lmd/app/down/download.html?a=E522A7C06D05CF6A10005786ABA9B9BE7B8336D21";
        String[] strArray = url.split("=");
        String aString = strArray[1];
        int stakeNoLength = aString.length() - 33;
        String checkBit = aString.charAt(aString.length() - 1) + "";//取出校验位
        String stakeNo = aString.substring(16, 16+stakeNoLength - 1);
        String pre16String = aString.substring(0, 16);
        String last16String = aString.substring(15+stakeNoLength, aString.length() - 2);
        System.out.println(MD5Utils.encrypt(stakeNo, MD5Utils.MD5_KEY).toUpperCase().equals(pre16String + last16String));
        System.out.println(pre16String);
        System.out.println(last16String);
        System.out.println(stakeNo);

    }
}
