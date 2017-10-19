package com.dongtong.basic.service;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.domain.BaseMetro;
import com.dongtong.basic.dto.MetroLineInfoDTO;
import com.dongtong.basic.dto.MetroStationInfoDTO;
import com.dongtong.basic.manager.MetroManager;
import com.dongtong.basic.query.MetroQuery;
import com.fc.common.redis.RedisUtil;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-05 13:33
 **/
@Service
public class MetroServiceImpl implements MetroService {
    @Autowired
    private MetroManager metroManager;
    @Override
    public ResultDO<List<MetroLineInfoDTO>> queryMetro(MetroQuery query) {
        ResultDO<List<MetroLineInfoDTO>> resultDO=new ResultDO<List<MetroLineInfoDTO>>();
        if(ValidateHelper.isEmpty(query.getCityId())){
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
        }
        Object object= RedisUtil.get("BASIC_METRO"+query.getCityId());
        if(object!=null){
            List<MetroLineInfoDTO> list=(List<MetroLineInfoDTO>)object;
            resultDO.setData(list);
            resultDO.setSuccess(true);
            return resultDO;
        }
        List<MetroLineInfoDTO> lineInfoDTOList =new ArrayList<>();
        // 查询线路信息
        List<BaseMetro> linelist = metroManager.selectByCityId(query.getCityId());

        for(BaseMetro baseMetro:linelist){
            MetroLineInfoDTO metroLineInfoDTO=new MetroLineInfoDTO();

            metroLineInfoDTO.setLineId(String.valueOf(baseMetro.getId()));
            metroLineInfoDTO.setLineName(baseMetro.getName());
            //查询地铁站
            List<MetroStationInfoDTO> metroStationList=metroManager.selectByMetroId(baseMetro.getId());

            metroLineInfoDTO.setStationList(metroStationList);
            lineInfoDTOList.add(metroLineInfoDTO);
        }
        resultDO.setData(lineInfoDTOList);
        if(!ValidateHelper.isEmpty(resultDO.getData())){
            RedisUtil.set("BASIC_METRO"+query.getCityId(),resultDO.getData(),86400);
        }
        resultDO.setSuccess(true);
        return resultDO;
    }
}
