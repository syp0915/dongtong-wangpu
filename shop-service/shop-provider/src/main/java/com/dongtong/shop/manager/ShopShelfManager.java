package com.dongtong.shop.manager;

import com.alibaba.fastjson.JSONObject;
import com.dongtong.shop.dao.ShopMapper;
import com.dongtong.shop.dao.ShopShelfMapper;
import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.domain.ShopShelf;
import com.shfc.mybatis.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Package com.dongtong.shop.manager.ShopShelfManager
 * @Description: ShopShelfManager
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/8/14 13:28
 * version V1.0.0
 */
@Service
public class ShopShelfManager {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopShelfMapper shopShelfMapper;

    public ShopShelf selectByPrimaryKey(Long shelfId){
        return shopShelfMapper.selectByPrimaryKey(shelfId);
    }

    public void updateByPrimaryKeySelective(ShopShelf shelf){
        shopShelfMapper.updateByPrimaryKeySelective(shelf);
    }

    @Transactional
    public void modifyShelfAndShopScore(Shop shop, ShopShelf shelf){
        shopMapper.updateByPrimaryKeySelective(shop);
        updateByPrimaryKeySelective(shelf);
    }

    /**
     * 查询上架记录
     * @return
     */
   public Page<ShopShelf> queryShopShelf(int pageNumber, int pageSize){
       Page<ShopShelf> page = new Page<>(pageNumber, pageSize);

       shopShelfMapper.queryShopShelf(page);

       return page;
   }
}
