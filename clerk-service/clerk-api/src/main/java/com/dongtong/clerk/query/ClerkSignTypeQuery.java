package com.dongtong.clerk.query;

/**
 * @Author:zhoumin
 * @Description:
 * @Date:Created in 16:12 2017/8/5.
 */
public class ClerkSignTypeQuery extends BaseQuery {
    /**
     * 业务员ID
     */
    private Long id;
    /**
     * 查询类型：1:约看 2签约
     */
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
