package com.dongtong.basic.service;

import com.dongtong.basic.constant.ErrorConstant;
import com.dongtong.basic.domain.BaseBlock;
import com.dongtong.basic.domain.BaseDistrict;
import com.dongtong.basic.dto.BlockInfoDTO;
import com.dongtong.basic.dto.RegionInfoDTO;
import com.dongtong.basic.manager.AreaManager;
import com.dongtong.basic.query.AreaQuery;
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
 * @create 2017-05-04 17:12
 **/
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired(required = false)
    private AreaManager areaManager;
    @Override
    public ResultDO<List<RegionInfoDTO>> qyeryArea(AreaQuery query) {
        ResultDO<List<RegionInfoDTO>> resultDO=new ResultDO<List<RegionInfoDTO>>();
        if (ValidateHelper.isEmpty(query.getCityId())) {
            resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
            resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
        }
        Object object= RedisUtil.get("BASIC_AREA"+query.getCityId());
        if(object!=null){
            List<RegionInfoDTO> list=(List<RegionInfoDTO>)object;
            resultDO.setData(list);
            resultDO.setSuccess(true);
            return resultDO;
        }

        List<RegionInfoDTO> list = new ArrayList<RegionInfoDTO>();
        //查询区域
        List<BaseDistrict> districts = areaManager.selectByCityId(query.getCityId());

        for (BaseDistrict baseDistrict : districts) {
            RegionInfoDTO regionInfoDTO = new RegionInfoDTO();
            regionInfoDTO.setRegionId(String.valueOf(baseDistrict.getId()));
            regionInfoDTO.setRegionName(baseDistrict.getDistrictName());
            List<BlockInfoDTO> blockList = new ArrayList<BlockInfoDTO>();
            // 查询区县下的街道、镇
            List<BaseBlock> blocks = areaManager.selectByDistrictId(baseDistrict.getId());
            for (BaseBlock baseBlock : blocks) {
                BlockInfoDTO blockInfoDTO = new BlockInfoDTO();
                blockInfoDTO.setBlockId(String.valueOf(baseBlock.getId()));
                blockInfoDTO.setBlockName(baseBlock.getBlockName());
                blockList.add(blockInfoDTO);
            }
            regionInfoDTO.setBlockList(blockList);
            list.add(regionInfoDTO);
        }
        resultDO.setData(list);

        if(!ValidateHelper.isEmpty(resultDO.getData())){
            RedisUtil.set("BASIC_AREA"+query.getCityId(), resultDO.getData(), 86400);//一天存一次
        }

        resultDO.setSuccess(true);
        return resultDO;
    }
}
