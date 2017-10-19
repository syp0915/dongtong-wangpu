package com.dongtong.basic.constant;

/**
 * @Package com.shfc.nnstv.constant.ErrorConstant
 * @Description: 错误对照码
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wuky
 * @date 2017/5/5 9:43
 * version V1.0.0
 */
public enum ErrorConstant {

    SUCCESS(0, "success"),

    BAD_REQUEST(400, "Bad Request!"),
    NOT_AUTHORIZATION(401, "NotAuthorization"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    RUNTIME_EXCEPTION(1000, "[服务器]运行时异常"),
    NULL_POINTER_EXCEPTION(1001, "[服务器]空值异常"),
    CLASS_CAST_EXCEPTION(1002, "[服务器]数据类型转换异常"),
    IO_EXCEPTION(1003, "[服务器]IO异常"),
    NO_SUCH_METHOD_EXCEPTION(1004, "[服务器]未知方法异常"),
    INDEX_OUT_OF_BOUNDS_EXCEPTION(1005, "[服务器]数组越界异常"),
    CONNECT_EXCEPTION(1006, "[服务器]网络异常"),
    ERROR_MEDIA_TYPE(1007, "[服务器]Content-type错误，请使用application/json"),
    EMPTY_REQUEST_BOYD(1008, "[服务器]request请求body不能为空"),
    ERROR_REQUEST_BOYD(1009, "[服务器]request请求body非json对象"),
    REMOTE_SERVER_RESP_NULL(1010, "远程服务器无应答或应答为空"),


    ERROR_VERSION(2000, "[服务器]版本号错误"),
    NULL_PARAMETER(3003,"必传参数不能为空"),
    PARAMETER_ERROR(3004,"参数值错误"),
    OBJECT_NOT_EXIST(3005, "请求对象不存在"),

    UNSUPPORT_SMS_TYPE(4000, "不支持的短信类型"),
    UNEXIST_SMS_VERIFY_CODE(4001, "不存在的短信验证码"),
    ERROR_SMS_VERIFY_CODE(4003, "短信验证码验证失败"),
    UNEXIST_PIC_VERIFY_CODE(4004, "不存在的图片验证码"),
    ERROR_PIC_VERIFY_CODE(4005, "短信验证码验证失败"),
    SMS_VERIFY_CODE_EXPIRE(4006, "短信验证码已过期"),
    PIC_VERIFY_CODE_EXPIRE(4006, "图片验证码已过期"),
    EXCEED_SMS_VERIFY_CODE_MAX_COUNT(4007, "超过短信验证码当日使用限制"),
    CODE_HAS_VERIFIED(4008, "验证码已经被验证"),
    VERIFY_NOT_MATCH_PHONE(4009, "验证码和手机号不匹配"),
    SMS_ERROR_USE_SCENE(4010, "非法的验证码使用场景");
    
    private final int code;
    private final String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ErrorConstant(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getNameByValue(Integer val) {
        if (val != null) {
            int value = val;
            for (ErrorConstant constant : ErrorConstant.values()) {
                if (constant.code == value) {
                    return constant.msg;
                }
            }
        }
        return "";
    }

    public ErrorConstant getTypeByValue(int value) {
        for (ErrorConstant constant : ErrorConstant.values()) {
            if (constant.code == value) {
                return constant;
            }
        }
        return null;
    }

}
