package com.dongtong.basic.dto.resp;
import java.io.Serializable;

/**
 * @author sunyaping
 * @Package com.dongtong.basic.dto.resp
 * @Description
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-15 14:22
 * version V1.0.0
 **/
public class HttpBaseBean implements Serializable {
    protected Integer pageSize = 10;
    protected Integer pageNumber = 1;
    protected Long totalCount;
    protected Integer totalPage;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
