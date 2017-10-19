package com.dongtong.topic.dto;

import com.shfc.mybatis.pagination.Page;

import java.io.Serializable;

/**
 * @Package com.dongtong.topic.dto.CustomerTopicListDTO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/17 13:58
 * version V1.0.0
 */
public class CustomerTopicListDTO implements Serializable{
    private Page<TopicListDTO> page;
    private Integer createTopicOpen;

    public Integer getCreateTopicOpen() {
        return createTopicOpen;
    }

    public void setCreateTopicOpen(Integer createTopicOpen) {
        this.createTopicOpen = createTopicOpen;
    }

    public Page<TopicListDTO> getPage() {
        return page;
    }

    public void setPage(Page<TopicListDTO> page) {
        this.page = page;
    }
}
