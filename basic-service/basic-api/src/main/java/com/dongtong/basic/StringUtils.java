package com.dongtong.basic;

import com.shfc.common.encrypt.MD5Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/8 下午3:18.
 */
public class StringUtils {

    /**
     * 生成含8的4位验证码
     * @return
     */
    public static String generateVerifyCode(){
        String[] codeArr = {"1", "2", "3", "5", "6", "7", "8", "9", "0"};
        StringBuilder verifyCode = new StringBuilder("");
        List<String> verifyCodeList = new ArrayList<String>();
        verifyCodeList.add(codeArr[new Long(System.currentTimeMillis() * new Random().nextInt(99) % 9).intValue()]);
        verifyCodeList.add(codeArr[new Long(System.currentTimeMillis() * new Random().nextInt(999) % 9).intValue()]);
        verifyCodeList.add(codeArr[new Long(System.currentTimeMillis() * new Random().nextInt(9999) % 9).intValue()]);
        verifyCodeList.add(new Random().nextInt(4), "8");
        for (String str : verifyCodeList) {
            verifyCode.append(str);
        }
        return verifyCode.toString();
    }

    /**
     * 生成token
     * @return
     */
    public static String generateToken(){
        return MD5Utils.encrypt(System.currentTimeMillis() + "" + new Random().nextInt(9999), MD5Utils.MD5_KEY).toUpperCase();
    }

    /**
     * 手机号码校验
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^1[3|4|5|7|8][0-9]\\d{4,8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void main(String[] args) {
        //System.out.println(isChinaPhoneLegal("18512114337"));
        String str = "{\\\"data\\\":{\\\"block\\\":\\\"新华路\\\",\\\"blockId\\\":108,\\\"buildingId\\\":289714,\\\"buildingNo\\\":\\\"10号\\\",\\\"district\\\":\\\"长宁区\\\",\\\"districtId\\\":310105,\\\"lat\\\":\\\"31.204784\\\",\\\"lon\\\":\\\"121.419106\\\",\\\"mac\\\":\\\"0019684375FB\\\",\\\"resCategory\\\":\\\"智能2000\\\",\\\"resCode\\\":\\\"5010\\\",\\\"resName\\\":\\\"SVA超高清智能型机顶盒\\\",\\\"residenceId\\\":83,\\\"residenceName\\\":\\\"虹桥绿郡公馆\\\",\\\"serialNo\\\":\\\"94A51A2984663809A\\\"}".replace("\\","");
        System.out.println(str);
    }

}
