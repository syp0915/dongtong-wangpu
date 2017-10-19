package com.dongtong.basic.dto;

import java.io.Serializable;

public class BlockInfoDTO implements Serializable {

    private static final long serialVersionUID = -8226069379769752451L;
    private String blockId;
    private String blockName;


    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }


}
