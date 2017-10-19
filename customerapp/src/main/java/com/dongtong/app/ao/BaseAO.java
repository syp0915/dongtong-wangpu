package com.dongtong.app.ao;


import com.dongtong.basic.dto.*;
import com.dongtong.basic.query.AdvQuery;
import com.dongtong.basic.query.AreaQuery;
import com.dongtong.basic.query.MetroQuery;
import com.dongtong.basic.query.TagQuery;
import com.dongtong.basic.service.*;
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
 * @create 2017-05-08 09:40
 **/
@Service
public class BaseAO {
    @Autowired(required = false)
    private TagService tagService;

    @Autowired(required = false)
    private AreaService areaService;

    @Autowired(required = false)
    private MetroService metroService;

    @Autowired(required = false)
    private AdvInfoService advInfoService;

    @Autowired(required = false)
    private IndustryService industryService;



    public ResultDO<List<TagInfoDTO>> tagList(TagQuery query){
        if(ValidateHelper.isEmpty(query.getTagType())){
            ResultDO<List<TagInfoDTO>> resultDO=new  ResultDO<List<TagInfoDTO>>();
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        return tagService.queryTag(query);
    }


    public ResultDO<List<RegionInfoDTO>> areaList(AreaQuery query){
        if(ValidateHelper.isEmpty(query.getCityId())){
            ResultDO<List<RegionInfoDTO>> resultDO=new ResultDO<List<RegionInfoDTO>>();
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }
        return areaService.qyeryArea(query);
    }

    public ResultDO<List<MetroLineInfoDTO>> queryMetro(MetroQuery query){
        if(ValidateHelper.isEmpty(query.getCityId())){
            ResultDO<List<MetroLineInfoDTO>> resultDO=new ResultDO<List<MetroLineInfoDTO>>();
            resultDO.setErrMsg("请求参数不能为空");
            return resultDO;
        }

        return metroService.queryMetro(query);

    }

    public ResultDO<List<AdvInfoDTO>> advList(AdvQuery query,String version){
//        if(ValidateHelper.isEmpty(query.getPosition())){
//            ResultDO<List<AdvInfoDTO>> resultDO=new ResultDO<List<AdvInfoDTO>>();
//            resultDO.setErrMsg("请求参数不能为空");
//            return resultDO;
//        }
        if(version.compareTo("v1.2.0")>=0){
            return advInfoService.adList(query);
        }else {
            return advInfoService.advList(query);
        }


    }


    public ResultDO<List<IndustryInfoDTO>> industryList(){
        return industryService.industryList();
    }


}
