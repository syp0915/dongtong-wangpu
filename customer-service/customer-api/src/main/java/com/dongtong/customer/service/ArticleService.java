package com.dongtong.customer.service;

import com.dongtong.customer.dto.req.ArticleReqDTO;
import com.dongtong.customer.dto.resp.ArticleRespDTO;
import com.shfc.common.result.ResultDO;

/**
 * 文章
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/24 9:43
 * @since 1.0
 */
public interface ArticleService {
    /**
     * 获取文章信息 根据文章id
     * @return
     */
    ResultDO  <ArticleRespDTO> getArticleInfo(ArticleReqDTO articleReqDTO);
}
