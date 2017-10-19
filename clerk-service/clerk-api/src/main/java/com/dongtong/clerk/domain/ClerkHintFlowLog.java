package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.clerk.domain.ClerkHintFlowLog.java
 * @Description: 线索状态流转表
 * @Company: ultimate leader
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author zhanghz
 * @date 2017/08/02 17:55
 * version v1.0.0
 */
public class ClerkHintFlowLog extends BaseBean {
    /**
     * 线索id
     */
    private Long hintId;

    /**
     * 流转类型 1:拓铺员认领 2:拓铺员确认 3:交易员认领 4:转化商铺 5:线索废弃 6:线索撤销
     */
    private Integer type;

    /**
     * 业务员id
     */
    private Long clerkId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取线索id
     *
     * @return hint_id
     */
    public Long getHintId() {
        return hintId;
    }

    /**
     * 设置线索id
     *
     * @param hintId
     */
    public void setHintId(Long hintId) {
        this.hintId = hintId;
    }

    /**
     * 获取流转类型 1:拓铺员认领 2:拓铺员确认 3:交易员认领 4:转化商铺 5:线索废弃 6:线索撤销
     *
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置流转类型 1:拓铺员认领 2:拓铺员确认 3:交易员认领 4:转化商铺 5:线索废弃 6:线索撤销
     *
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取业务员id
     *
     * @return clerk_id
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 设置业务员id
     *
     * @param clerkId
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 获取备注
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}