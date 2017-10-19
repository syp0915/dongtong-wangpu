package com.dongtong.basic.service;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.dto.TagInfoDTO;
import com.dongtong.basic.manager.TagManager;
import com.dongtong.basic.query.TagQuery;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 18:49
 **/
@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TagManager tagManager;
    @Override
    public ResultDO<List<TagInfoDTO>> queryTag(TagQuery query) {
        ResultDO<List<TagInfoDTO>> resultDO=new ResultDO<List<TagInfoDTO>>();
        if(ValidateHelper.isEmpty(query.getTagType())){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
        }
        List<TagInfoDTO> list=tagManager.queryTagByType(query.getTagType());
        resultDO.setData(list);
        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<List<String>> queryTagById(List<Long> tagIds) {
        ResultDO<List<String>> resultDO=new ResultDO<>();
        if(tagIds.size()==0||tagIds==null){
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        List<String> list=tagManager.queryTagById(tagIds);
        resultDO.setData(list);
        resultDO.setSuccess(true);
        return resultDO;
    }

}
