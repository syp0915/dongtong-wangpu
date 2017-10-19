package com.dongtong.basic.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 * 区域板块信息
 *
 * @author wky
 * @version V1.0
 * @create 2016-12-30 09:08
 **/
public class RegionInfoDTO implements Serializable{

    private static final long serialVersionUID = -659507710728765617L;
    private String regionId;
    private String regionName;

    private List<BlockInfoDTO> blockList;


    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<BlockInfoDTO> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<BlockInfoDTO> blockList) {
        this.blockList = blockList;
    }
}
