package com.dongtong.basic.query;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-08 13:37
 **/
public class TagQuery implements Serializable {
    private static final long serialVersionUID = -4270500772509607967L;

    private Long tagType;

    public Long getTagType() {
        return tagType;
    }

    public void setTagType(Long tagType) {
        this.tagType = tagType;
    }
}
