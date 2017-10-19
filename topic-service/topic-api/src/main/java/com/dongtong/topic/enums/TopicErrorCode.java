package com.dongtong.topic.enums;

import com.shfc.common.base.ValidateHelper;

/**
 * @version V1.0.0
 * @Package com.dongtong.topic.enums
 * @Description:
 * @Company:
 * @Copyright: Copyright (c) 2017
 * @Author chenxs
 * @date 2017/5/9 15:51
 */
public enum TopicErrorCode {

    PARAMETER_NOT_NULL("必填参数不能为空",20000),
    TOPIC_ENTITY_IS_NULL("帖子实体不存在",20001),
    NO_AUTH_OPEATE("没有权限操作",20002),
    PIC_COUNT_ERROR("图片数量最多9张",20003),
    DB_OPERATE_ERROR("数据库操作出错",20004),
    PARAMS_ILLEGAL_ERROR("输入参数不合法",20005),
    TOPIC_DELETE("帖子被删除",20006),
    IS_BANNED("您已被禁言",20007);



    private final String msg;
    private final int code;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    TopicErrorCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public static String getMsgByCode(Integer cod) {
        if (cod != null) {
            int code = cod;
            for (TopicErrorCode errorCode : TopicErrorCode.values()) {
                if (errorCode.code == code) {
                    return errorCode.msg;
                }
            }
        }
        return "";
    }
    public static Integer getValueByName(String name) {
        if (!ValidateHelper.isEmpty(name)) {
            String value = name;
            for (TopicErrorCode errorCode : TopicErrorCode.values()) {
                if (errorCode.msg.equals(value)) {
                    return errorCode.code;
                }
            }
        }
        return null;
    }

    public TopicErrorCode getTypeByCode(int code) {
        for (TopicErrorCode errorCode : TopicErrorCode.values()) {
            if (errorCode.code == code) {
                return errorCode;
            }
        }
        return null;
    }
}
