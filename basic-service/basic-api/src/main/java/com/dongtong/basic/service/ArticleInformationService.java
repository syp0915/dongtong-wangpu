package com.dongtong.basic.service;

import com.dongtong.basic.domain.BaseArticleInformation;
import com.shfc.common.result.ResultDO;

/**
 * @Package com.dongtong.basic.service.ArticleInformationService
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/10 19:09
 * version V1.0.0
 */
public interface ArticleInformationService {
    /**
     * 根据ID查询文章资讯
     * @param id
     * @return
     */
    ResultDO<BaseArticleInformation> getArticleById(Long id);
}
