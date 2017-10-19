package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.customer.dto.req.ArticleReqDTO;
import com.dongtong.customer.dto.resp.ArticleRespDTO;
import com.dongtong.customer.service.ArticleService;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/24 13:46
 * @since 1.0
 */
@Service
public class ArticleAO {
    @Autowired
    private ArticleService articleService;
    public ResultDO<ArticleRespDTO> getArticleInfo(ArticleReqDTO reqJson) {
        ResultDO res=new ResultDO();
        if(reqJson.getId()==null){
            res.setSuccess(false);
            res.setErrMsg(ErrorConstant.NULL_ARTICLE_ID.getMsg());
            res.setErrCode(ErrorConstant.NULL_ARTICLE_ID.getCode());
            return res;
        }
      return  articleService.getArticleInfo(reqJson);
    }
}
