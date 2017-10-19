package com.dongtong.customer.service;

import com.dongtong.customer.dto.req.ArticleReqDTO;
import com.dongtong.customer.dto.resp.ArticleRespDTO;
import com.dongtong.customer.manager.ArticleManager;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/24 9:54
 * @since 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleManager articleManager;

    @Override
    public ResultDO<ArticleRespDTO> getArticleInfo(ArticleReqDTO articleReqDTO) {
        ResultDO resultDO=new ResultDO();
        ArticleRespDTO articleRespDTO=articleManager.getArticleInfo(articleReqDTO);
        resultDO.setData(articleRespDTO);
        resultDO.setSuccess(true);
        return resultDO;
    }
}
