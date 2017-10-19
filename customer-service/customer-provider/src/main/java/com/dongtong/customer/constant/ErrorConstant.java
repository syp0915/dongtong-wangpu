package com.dongtong.customer.constant;

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
    OBJECT_NOT_EXIST(3004, "指定对象不存在"),
    DB_DATA_EXCEPTION(3005, "数据库数据异常"),
    PHONE_USED_BY_CLERK(3006, "小二的手机号不允许登录旺铺，请更换手机号登录"),
    DB_OPR_EXCEPTION(3007, "数据库操作异常"),
    SCHEDULE_STATUS_EXCEPTION(3008, "日程状态参数异常"),
    ACCOUNT_EXCEPTION(3009, "用户账户状态异常"),
    NO_RIGHT_OPERATE(3010, "你无权进行此操作"),
    NO_EXSIT_VISIT_ID(3011,"约见id不存在"),
    ERROR_VISIT_STATUS(3012,"修改时间必须为待踩盘"),
    NULL_SCHEDULE(3013,"日程不存在"),
    UPDATE_VITIST_TIME_ERROR(3014,"更新约看时间处理失败"),
    DISCARD_VISIT(3015,"约看状态修改失败"),
    UPDATE_SCHEDULE_ERROR(3016,"约看状态修改失败"),
    SERVICE_TYPE_ERROR(3017,"服务类型错误");

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
