package com.dongtong.customer.service;

import com.dongtong.customer.JunitBaseTest;
import com.dongtong.customer.dto.req.ArticleReqDTO;
import com.shfc.common.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/24 10:20
 * @since 1.0`
 */
public class ArticleServiceTest extends JunitBaseTest {
     @Autowired
    private ArticleService articleService;
    @Test
    public  void testGetArticleInfo(){
        ArticleReqDTO articleReqDTO=new ArticleReqDTO();
        articleReqDTO.setId(1L);
       ResultDO res= articleService.getArticleInfo(articleReqDTO);
        System.out.println(res);
    }
}
