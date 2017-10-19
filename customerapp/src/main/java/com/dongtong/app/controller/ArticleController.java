package com.dongtong.app.controller;

import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.ArticleAO;
import com.dongtong.customer.dto.req.ArticleReqDTO;
import com.dongtong.customer.dto.resp.ArticleRespDTO;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 文章资讯
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/24 13:45
 * @since 1.0
 */
@RestController
@RequestMapping(value = "api/article",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {
    @Autowired
    private ArticleAO articleAO;

    @UnLoginLimit
    @ApiOperation(value = "标签列表",notes = "标签列表")
    @RequestMapping(value = "/getArticleInfo/{version:.+}",method = RequestMethod.POST)
    public ResultDO <ArticleRespDTO> getArticleInfo(@ApiParam(name = "reqJson",value = "请求参数",required = true) @RequestBody ArticleReqDTO reqJson,
                                                   @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return articleAO.getArticleInfo(reqJson);
    }


}
