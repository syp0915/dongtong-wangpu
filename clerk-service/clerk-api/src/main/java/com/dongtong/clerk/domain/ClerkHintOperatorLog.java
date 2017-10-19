package com.dongtong.clerk.domain;

import com.shfc.common.httpbean.BaseBean;

/**
 * @Package: com.dongtong.clerk.domain.ClerkHintOperatorLog.java
 * @Description: 线索操作日志表
 * @Company: ultimate leader
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author zhanghz
 * @date 2017/08/02 17:55
 * version v1.0.0
 */
public class ClerkHintOperatorLog extends BaseBean {
    /**
     * 线索id
     */
    private Long hintId;

    /**
     * 流转类型 1:客户发布线索2:拓铺员发布线索 3:拓铺员认领 4:分配拓铺员 5:拓铺员确认有效 6:拓铺员确认无效 7:交易员认领 8:转化商铺 9:线索废弃 10:线索取消 11:线索恢复12:线索确认超时 13:线索核实超时
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
     * 获取流转类型 1:客户发布线索2:拓铺员发布线索 3:拓铺员认领 4:分配拓铺员 5:拓铺员确认有效 6:拓铺员确认无效 7:交易员认领 8:转化商铺 9:线索废弃 10:线索取消 11:线索恢复12:线索确认超时 13:线索核实超时
     *
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置流转类型 1:客户发布线索2:拓铺员发布线索 3:拓铺员认领 4:分配拓铺员 5:拓铺员确认有效 6:拓铺员确认无效 7:交易员认领 8:转化商铺 9:线索废弃 10:线索取消 11:线索恢复12:线索确认超时 13:线索核实超时
     *
     * @param type
     */
    public void setType(Integer type) {
        this.type = type ;
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