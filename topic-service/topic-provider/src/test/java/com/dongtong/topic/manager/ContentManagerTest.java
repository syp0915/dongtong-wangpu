package com.dongtong.topic.manager;

import com.dongtong.topic.JunitBaseTest;
import com.dongtong.topic.dao.ContentMapper;
import com.dongtong.topic.dto.req.ContentGuideDetailReqDTO;
import com.dongtong.topic.dto.resp.ShopListRespDTO;
import com.shfc.common.base.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ContentManagerTest extends JunitBaseTest {

    @Autowired
    private ContentManager contentManager;

    @Test
    public void testGetRecomendShop(){
        List<Long> shopIdList = new ArrayList<>();
        ContentGuideDetailReqDTO contentGuideDetailReqDTO = new ContentGuideDetailReqDTO();
        List<ShopListRespDTO> shopList = contentManager.getRecomendShopList(14L, contentGuideDetailReqDTO);
        Logger.info(this.getClass(),"****************************************************************");
        Logger.info(this.getClass(),"接口返回的内容：" + shopList);
    }
}
