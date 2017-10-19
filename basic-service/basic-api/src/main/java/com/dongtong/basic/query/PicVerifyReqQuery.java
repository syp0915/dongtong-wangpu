package com.dongtong.basic.query;

import java.io.Serializable;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/4 下午1:47.
 */
public class PicVerifyReqQuery implements Serializable {
    private Integer useScene;//0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺

    public Integer getUseScene() {
        return useScene;
    }

    public void setUseScene(Integer useScene) {
        this.useScene = useScene;
    }
}
