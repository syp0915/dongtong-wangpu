package com.dongtong.basic.manager;

import com.dongtong.basic.dao.BaseArticleInformationMapper;
import com.dongtong.basic.domain.BaseArticleInformation;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.basic.manager.ArticleInformationManager
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/10 19:10
 * version V1.0.0
 */
@Service
public class ArticleInformationManager {

    @Autowired
    private BaseArticleInformationMapper baseArticleInformationMapper;

    public ResultDO<BaseArticleInformation> getArticleById(Long id){
        ResultDO<BaseArticleInformation> resultDO = new ResultDO<BaseArticleInformation>();
        BaseArticleInformation baseArticleInformation = baseArticleInformationMapper.selectByPrimaryKey(id);
        if(ValidateHelper.isEmpty(baseArticleInformation)){
            resultDO.setErrMsg("");
            resultDO.setErrCode(0);
            resultDO.setSuccess(false);
            return resultDO;
        }
        resultDO.setErrMsg("");
        resultDO.setSuccess(true);
        resultDO.setErrCode(0);
        resultDO.setData(baseArticleInformation);
        return resultDO;
    }
}
