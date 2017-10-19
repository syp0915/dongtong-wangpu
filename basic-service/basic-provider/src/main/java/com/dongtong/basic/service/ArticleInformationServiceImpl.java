package com.dongtong.basic.service;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.domain.BaseArticleInformation;
import com.dongtong.basic.manager.ArticleInformationManager;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package com.dongtong.basic.service.ArticleInformationServiceImpl
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/10 19:10
 * version V1.0.0
 */
@Service
public class ArticleInformationServiceImpl implements ArticleInformationService {

    @Autowired
    private ArticleInformationManager articleInformationManager;

    @Override
    public ResultDO<BaseArticleInformation> getArticleById(Long id) {
        ResultDO<BaseArticleInformation> resultDO = new ResultDO<BaseArticleInformation>();
        if(ValidateHelper.isEmpty(id)){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
            return resultDO;
        }
        resultDO = articleInformationManager.getArticleById(id);
        return resultDO;
    }
}
