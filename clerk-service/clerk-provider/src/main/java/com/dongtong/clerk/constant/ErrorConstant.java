package com.dongtong.clerk.constant;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/9 下午3:24.
 */
public enum  ErrorConstant {
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
    SYSTEM_ERROR(1011, "[服务器]系统异常，请联系管理员"),


    ERROR_VERSION(2000, "[服务器]版本号错误"),
    NULL_PARAMETER(3003,"必传参数不能为空"),
    PHONE_USED_BY_CUSTOMER(3007, "手机号已被用户注册"),
    OBJECT_NOT_EXIST(3004, "指定对象不存在"),

    SHOP_NOT_EXIST(3005,"商铺信息不存在"),
    NULL_SHOP_ID(3006,"商铺ID不能为空"),
    NULL_CLERT_HINT_ID(3007,"线索ID不能为空"),
    CLERT_HINT_NOT_EXIST(3008,"线索信息不存在"),
    NULL_ADRESS(3009,"地址不能为空"),
    SIGN_IN_ERROR(3010,"签到处理失败"),
    NULL_CUSTOMER_ID(3011,"用户编号不能为空"),
    NULL_LONGITUDE_LATITUDE(3012,"经纬度不能为空"),
    NULL_BLOCKID_OR_LON_LAT(3013,"板块编号或经纬度范围不能同时为空"),
    HINT_PUBLISH_FAILER(3014,"线索发布处理失败"),
    ACCOUNT_EXCEPTION(3015, "业务员状态异常"),
    UPDATE_FAIL(3016,"更新签约表失败"),
    ERROR_NO_REGISTER(3017, "请先完成业务员注册"),
    DB_QUERY_EXCEPTION(3018, "数据库查询异常"),
    CLERK_NOT_EXIST(3019,"业务员信息不存在"),
    NOT_AUTH_OPERATE(3020,"无操作权限"),
    UPDATE_DATA_FAIL(3021,"更新数据失败"),
    DELETE_DATA_FAIL(3022,"删除数据失败"),
    HINT_STATUS_NOT_ACCORD(3023,"线索状态不符"),
    PARAMETER_TYPE_ERROR(3019, "参数类型错误"),
    TRADE_CLAIM_ERROR(3024, "交易员认领失败"),
    NULL_CLERK_ID(3025,"业务员ID不能为空"),
    NULL_TYPE(3026,"类型不能为空"),
    HINT_NOT_BELONG_CLERK(3027,"线索不属于该业务员"),
    ERROR_HINT_STATUS(3028,"线索状态不一致"),
    SHOP_CODE_UPDATE_FAIL(3029,"更新二维码使用状态失败"),
    HAS_CLAIMED_HINT(3030,"已经认领过该线索，不可重复认领");

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
