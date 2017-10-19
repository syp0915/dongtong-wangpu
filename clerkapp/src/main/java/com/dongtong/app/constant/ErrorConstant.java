package com.dongtong.app.constant;

/**
 * @Package com.shfc.cloud.constant.ErrorConstant
 * @Description: 错误对照码
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/3/17 9:43
 * version V1.0.0
 */
public enum ErrorConstant {
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

    ERROR_VERSION(2000, "[服务器]版本号错误"),

    NULL_REQ_DATA(33001,"[线索服务]请求参数不能为空"),
    NULL_SHOP_ADDRESS(33002,"[线索服务]商铺地址不能为空"),
    NULL_LINKMAN_NAME(33003,"[线索服务]联系人姓名不能为空"),
    NULL_LINKMAN_PHONE(33004,"[线索服务]联系电话不能为空"),
    NULL_SUBSCRIBE_TIME(33005,"[线索服务]约见时间不能为空"),
    NULL_CLERK_HINT_ID(33006,"[线索服务]线索ID不能为空"),
    NULL_LON_OR_LAT(33007,"[线索服务]经纬度不能为空"),
    NULL_BLOCKID_OR_LON_LAT(33008,"[线索服务]经纬度或板块ID不能同时为空"),
    SIGN_IN_ERROR(33009,"签到处理失败"),
    NULL_CUSTOMER_ID(33010,"用户编号不能为空"),
    NULL_LONGITUDE_LATITUDE(33011,"经纬度不能为空"),
    NULL_ADRESS(33012,"地址不能为空"),
    NULL_SHOP_ID(33013,"商铺ID不能为空"),
    NULL_REASON(33014,"[线索服务]废弃原因不能为空"),
    NULL_SIGNID(33015,"签约id不能为空"),
    NULL_VISIT_ID(33016,"约见id不能为空"),
    NULL_VISIT_TIME(33017,"约见时间不能为空"),
    NULL_VISIT_REASON(33018,"取消约看原因不能为空"),
    NULL_SIGN_REASON(33019,"签约撤销原因不能为空"),
    NULL_SIGN_TIME(33020,"签约时间不能为空"),
    NULL_SCHEDULE(33021,"日程不存在"),
    ERROR_DATE_FORMAT(33022,"日期格式不正确"),
    NO_EXSIT_CLERK_HINT(33023,"线索id不存在"),
    CLAIM_CLERK_HINT(33024,"线索已被认领"),
    DISCARD_CLERK_HINT(33025,"线索已废弃"),
    NO_EXSIT_VISIT_ID(33026,"约见id不存在"),
    DISCARD_SIGN(33028,"签约状态修改失败"),
    NO_EXSIT_SIGN_ID(33027,"签约id不存在"),
    DISCARD_VISIT(33029,"约看状态修改失败"),
    UPDATE_FAIL(33030,"更新签约数据失败"),
    ERROR_CLERK_HINT_STATUS(33031,"修改时间必须为待实勘"),
    ERROR_VISIT_STATUS(33032,"修改时间必须为待踩盘"),
    CLERK_RULE_ERROR(33033,"业务员不存在或无权限操作"),
    ERROR_SIGN_STATUS(33034,"修改时间必须为待签约"),
    NULL_CHERK_ID(33035,"业务员编号不能为空"),
    NULL_FOLLOW_CONTENT(33036,"跟进内容不能为空"),
    NULL_HINT_FROM(33037,"线索来源不能为空"),
    NULL_FLOOR(33038,"楼层信息不能为空"),
    NULL_IS_SHOW(33039,"前台是否展示手机信息不能为空"),
    NULL_ADDR_IS_SHOW(33040,"前台是否展示地址信息不能为空"),
    NULL_HINT_SHOP_IMG(33041,"线索图片不能为空"),
    NULL_HAS_POSTER(33042,"是否张贴海报"),
    CLERK_HINT_CANCEL_FAIL(33043, "线索废弃失败"),
    NULL_PARAMETER(4001,"必传参数不能为空"),
    OBJECT_NOT_EXIST(4002, "指定对象不存在"),
    DB_DATA_EXCEPTION(4003, "数据库数据异常"),
    UNSUPPORT_SMS_TYPE(4004, "不支持的验证类型"),
    PHONE_FORMAT_EXCEPTION(4005, "手机号码格式异常"),
    ;

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
