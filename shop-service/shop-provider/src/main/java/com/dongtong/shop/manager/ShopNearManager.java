package com.dongtong.shop.manager;

import com.dongtong.shop.dao.ShopMapper;
import com.dongtong.shop.dao.ShopNearImgMapper;
import com.dongtong.shop.dao.ShopNearMapper;
import com.dongtong.shop.domain.ShopNear;
import com.dongtong.shop.dto.IssueShopDTO;
import com.dongtong.shop.dto.ShopImgDTO;
import com.dongtong.shop.dto.ShopNearDTO;
import com.dongtong.shop.dto.ShopNearDetailDTO;
import com.dongtong.shop.enums.ShopNearSeat;
import com.shfc.common.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Package com.dongtong.shop.manager.ShopNearManager
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/9 16:59
 * version V1.0.0
 */
@Service
public class ShopNearManager {
    @Autowired
    private ShopNearMapper shopNearMapper;
    @Autowired
    private ShopNearImgMapper shopNearImgMapper;
    @Autowired
    private ShopMapper shopMapper;

    public ShopNear selectById(Long nearId){
        return shopNearMapper.selectByPrimaryKey(nearId);
    }
    public ShopNear selectNearByIdAndShopId(Long nearId,Long shopId){
        return shopNearMapper.selectNearByIdAndShopId(nearId,shopId);
    }

    @Transactional(rollbackFor = AppException.class)
    public void delShopNear(Long nearId) throws AppException{
        shopNearMapper.deleteByPrimaryKey(nearId);
        shopNearImgMapper.deleteByNearId(nearId);
    }
    public void saveNearShop(Long shopId,IssueShopDTO shopDTO){
        //临铺信息左1
        ShopNearDTO nearLeftOne = shopDTO.getNearLeftOne();
        // 临铺信息左2
        ShopNearDTO nearLeftTwo = shopDTO.getNearLeftTwo();
        // 临铺信息右1
        ShopNearDTO nearRightOne = shopDTO.getNearRightOne();
        // 临铺信息右2
        ShopNearDTO nearRightTwo = shopDTO.getNearRightTwo();
        if(nearLeftOne!=null){
            ShopNear near = new ShopNear();
            near.setShopId(shopId);
            near.setIndustryId(nearLeftOne.getIndustryId());
            near.setName(nearLeftOne.getName());
            near.setNearSeat(ShopNearSeat.LEFT_ONE.getValue());
            shopNearMapper.insert(near);
            Long nearId  = near.getId();
            shopNearImgMapper.insertList(nearLeftOne.getNearImg(),nearId);
        }
        if(nearLeftTwo!=null){
            ShopNear near = new ShopNear();
            near.setShopId(shopId);
            near.setIndustryId(nearLeftTwo.getIndustryId());
            near.setName(nearLeftTwo.getName());
            near.setNearSeat(ShopNearSeat.LEFT_TWO.getValue());
            shopNearMapper.insert(near);
            Long nearId  = near.getId();
            shopNearImgMapper.insertList(nearLeftTwo.getNearImg(),nearId);
        }
        if(nearRightOne!=null){
            ShopNear near = new ShopNear();
            near.setShopId(shopId);
            near.setIndustryId(nearRightOne.getIndustryId());
            near.setName(nearRightOne.getName());
            near.setNearSeat(ShopNearSeat.RIGHT_ONE.getValue());
            shopNearMapper.insert(near);
            Long nearId  = near.getId();
            shopNearImgMapper.insertList(nearRightOne.getNearImg(),nearId);
        }
        if(nearRightTwo!=null){
            ShopNear near = new ShopNear();
            near.setShopId(shopId);
            near.setIndustryId(nearRightTwo.getIndustryId());
            near.setName(nearRightTwo.getName());
            near.setNearSeat(ShopNearSeat.RIGHT_TWO.getValue());
            shopNearMapper.insert(near);
            Long nearId  = near.getId();
            shopNearImgMapper.insertList(nearRightTwo.getNearImg(),nearId);
        }
    }
    @Transactional(rollbackFor = AppException.class)
    public void updateShopNear(IssueShopDTO shopDTO) throws AppException{
        Long shopId = shopDTO.getShopId();
        //临铺信息左1
        ShopNearDTO nearLeftOne = shopDTO.getNearLeftOne();
        // 临铺信息左2
        ShopNearDTO nearLeftTwo = shopDTO.getNearLeftTwo();
        // 临铺信息右1
        ShopNearDTO nearRightOne = shopDTO.getNearRightOne();
        // 临铺信息右2
        ShopNearDTO nearRightTwo = shopDTO.getNearRightTwo();
        if(nearLeftOne!=null){
            Long nearId = nearLeftOne.getNearId();
            // 先根据shop_id和near_seat查询临铺信息
            ShopNear shopNear = shopNearMapper.selectNearByShopIdAndNearSeat(shopId,ShopNearSeat.LEFT_ONE.getValue());
            if(shopNear!=null){
                nearId = shopNear.getId();
            }
            if(nearId != null){
                ShopNear near = shopNearMapper.selectByPrimaryKey(nearId);
                near.setIndustryId(nearLeftOne.getIndustryId());
                near.setName(nearLeftOne.getName());
                near.setNearSeat(ShopNearSeat.LEFT_ONE.getValue());
                shopNearMapper.updateByPrimaryKeySelective(near);//更新临铺主表
                shopNearImgMapper.deleteByNearId(nearId);//先删除临铺照片，再重新插入
                shopNearImgMapper.insertList(nearLeftOne.getNearImg(),nearId);
            }else {
                ShopNear near = new ShopNear();
                near.setShopId(shopId);
                near.setIndustryId(nearLeftOne.getIndustryId());
                near.setName(nearLeftOne.getName());
                near.setNearSeat(ShopNearSeat.LEFT_ONE.getValue());
                shopNearMapper.insert(near);
                shopNearImgMapper.insertList(nearLeftOne.getNearImg(),near.getId());
            }
        }
        if(nearLeftTwo!=null){
            Long nearId = nearLeftTwo.getNearId();
            // 先根据shop_id和near_seat查询临铺信息
            ShopNear shopNear = shopNearMapper.selectNearByShopIdAndNearSeat(shopId,ShopNearSeat.LEFT_TWO.getValue());
            if(shopNear!=null){
                nearId = shopNear.getId();
            }
            if(nearId != null){
                ShopNear near = shopNearMapper.selectByPrimaryKey(nearId);
                near.setIndustryId(nearLeftTwo.getIndustryId());
                near.setName(nearLeftTwo.getName());
                near.setNearSeat(ShopNearSeat.LEFT_TWO.getValue());
                shopNearMapper.updateByPrimaryKeySelective(near);//更新临铺主表
                shopNearImgMapper.deleteByNearId(nearId);//先删除临铺照片，再重新插入
                shopNearImgMapper.insertList(nearLeftTwo.getNearImg(),nearId);
            }else {
                ShopNear near = new ShopNear();
                near.setShopId(shopId);
                near.setIndustryId(nearLeftTwo.getIndustryId());
                near.setName(nearLeftTwo.getName());
                near.setNearSeat(ShopNearSeat.LEFT_TWO.getValue());
                shopNearMapper.insert(near);
                shopNearImgMapper.insertList(nearLeftTwo.getNearImg(),near.getId());
            }
        }
        if(nearRightOne!=null){
            Long nearId = nearRightOne.getNearId();
            // 先根据shop_id和near_seat查询临铺信息
            ShopNear shopNear = shopNearMapper.selectNearByShopIdAndNearSeat(shopId,ShopNearSeat.RIGHT_ONE.getValue());
            if(shopNear!=null){
                nearId = shopNear.getId();
            }
            if(nearId != null){
                ShopNear near = shopNearMapper.selectByPrimaryKey(nearId);
                near.setIndustryId(nearRightOne.getIndustryId());
                near.setName(nearRightOne.getName());
                near.setNearSeat(ShopNearSeat.RIGHT_ONE.getValue());
                shopNearMapper.updateByPrimaryKeySelective(near);//更新临铺主表
                shopNearImgMapper.deleteByNearId(nearId);//先删除临铺照片，再重新插入
                shopNearImgMapper.insertList(nearRightOne.getNearImg(),nearId);
            }else {
                ShopNear near = new ShopNear();
                near.setShopId(shopId);
                near.setIndustryId(nearRightOne.getIndustryId());
                near.setName(nearRightOne.getName());
                near.setNearSeat(ShopNearSeat.RIGHT_ONE.getValue());
                shopNearMapper.insert(near);
                shopNearImgMapper.insertList(nearRightOne.getNearImg(),near.getId());
            }
        }
        if(nearRightTwo!=null){
            Long nearId = nearRightTwo.getNearId();
            // 先根据shop_id和near_seat查询临铺信息
            ShopNear shopNear = shopNearMapper.selectNearByShopIdAndNearSeat(shopId,ShopNearSeat.RIGHT_TWO.getValue());
            if(shopNear!=null){
                nearId = shopNear.getId();
            }
            if(nearId != null){
                ShopNear near = shopNearMapper.selectByPrimaryKey(nearId);
                near.setIndustryId(nearRightTwo.getIndustryId());
                near.setName(nearRightTwo.getName());
                near.setNearSeat(ShopNearSeat.RIGHT_TWO.getValue());
                shopNearMapper.updateByPrimaryKeySelective(near);//更新临铺主表
                shopNearImgMapper.deleteByNearId(nearId);//先删除临铺照片，再重新插入
                shopNearImgMapper.insertList(nearRightTwo.getNearImg(),nearId);
            }else {
                ShopNear near = new ShopNear();
                near.setShopId(shopId);
                near.setIndustryId(nearRightTwo.getIndustryId());
                near.setName(nearRightTwo.getName());
                near.setNearSeat(ShopNearSeat.RIGHT_TWO.getValue());
                shopNearMapper.insert(near);
                shopNearImgMapper.insertList(nearRightTwo.getNearImg(),near.getId());
            }
        }

    }
    public List<ShopImgDTO> selectNearImg(Long nearId){
        return shopNearImgMapper.selectNearImg(nearId);
    }

    public List<ShopNearDetailDTO> selectNearShop(Long shopId){
        return shopMapper.selectNearShop(shopId);
    }
}
