package com.dongtong.customer.manager;

import com.dongtong.customer.dao.BaseArticleInformationMapper;
import com.dongtong.customer.dto.req.ArticleReqDTO;
import com.dongtong.customer.dto.resp.ArticleRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/24 9:55
 * @since 1.0
 */
@Service
public class ArticleManager {
    @Autowired
    private BaseArticleInformationMapper baseArticleInformationMapper;

    public ArticleRespDTO getArticleInfo(ArticleReqDTO articleReqDTO) {
        return baseArticleInformationMapper.selectById(articleReqDTO);
    }
}
