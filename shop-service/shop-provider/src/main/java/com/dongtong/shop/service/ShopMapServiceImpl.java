package com.dongtong.shop.service;

import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.manager.ShopManager;
import com.dongtong.shop.manager.ShopMapManager;
import com.dongtong.shop.query.*;
import com.shfc.common.base.Logger;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.shop.service.ShopMapServiceImpl
 * @Description: 商铺地图service
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 18:19
 * version V1.0.0
 */
@Service
public class ShopMapServiceImpl implements ShopMapService{
    @Autowired
    private ShopMapManager shopMapManager;
    @Autowired
    private ShopManager shopManager;

    @Override
    public ResultDO<List<ShopMapDTO>> queryClerkRegionShopMap(MapClerkQuery query) {

        ResultDO<List<ShopMapDTO>> resultDO = new ResultDO<>();
        if(query == null){
            resultDO.setErrMsg("请求出参数不能为空");
            return resultDO;
        }
        if(query.getType() == null || query.getType() < 0 || query.getType() > 1){
            resultDO.setErrMsg("查询类型错误");
            return resultDO;
        }
        try {
            resultDO.setData(shopMapManager.queryClerkRegionShopMap(query));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "区域板块层级店铺数量异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<List<ShopMapDetailDTO>> queryClerkShopMap(MapClerkShopQuery query) {
        ResultDO<List<ShopMapDetailDTO>> resultDO = new ResultDO<>();

        if(query == null) query = new MapClerkShopQuery();

        if(query.getQueryType() != null &&
                (query.getQueryType() <0 || query.getQueryType() > 1)){
            resultDO.setErrMsg("筛选类型错误");
            return resultDO;
        }

        if(!ValidateHelper.isEmpty(query.getStatusList())){
            for(Integer status: query.getStatusList()){
                if(status == null || status.intValue() < 1 || status.intValue() > 3){
                    resultDO.setErrMsg("出租状态错误");
                    return resultDO;
                }
            }
        }

        if(query.getShelfList()!=null && !query.getShelfList().isEmpty()){
            for(int shelf : query.getShelfList()){
                if(shelf<0 || shelf>1){
                    resultDO.setErrMsg("发布状态错误");
                    return resultDO;
                }
            }
        }

        try {
            resultDO.setData(shopMapManager.queryClerkShopMap(query));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "店铺级别的地图异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<ShopMapSummaryDTO> findShopSummaryById(MapSummaryQuery query) {

        ResultDO<ShopMapSummaryDTO> resultDO = new ResultDO<>();
        Shop shop = shopManager.getShopById(query.getShopId());
        if(shop == null){
            resultDO.setErrMsg("商铺不存在");
            return resultDO;
        }
        if(ValidateHelper.isEmpty(query.getLongitude()) || ValidateHelper.isEmpty(query.getLatitude())){
            resultDO.setErrMsg("经纬度不能为空");
            return resultDO;
        }
        try {
            resultDO.setData(shopMapManager.findShopSummaryById(query));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "店铺级别的地图异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }

        return resultDO;
    }

    @Override
    public ResultDO<List<ShopMapDTO>> queryCustomerRegionShopMap(MapCustomerQuery query) {
        ResultDO<List<ShopMapDTO>> resultDO = new ResultDO<>();
        if(query == null){
            resultDO.setErrMsg("请求出参数不能为空");
            return resultDO;
        }
        if(query.getType() == null || query.getType() < 0 || query.getType() > 1){
            resultDO.setErrMsg("查询类型错误");
            return resultDO;
        }
        String checkScreenParam  = checkScreenParam(query);
        if(checkScreenParam!=null){
            resultDO.setErrMsg(checkScreenParam);
            return resultDO;
        }
        try {
            resultDO.setData(shopMapManager.queryCustomerRegionShopMap(query));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "用户端地图区域板块层级店铺数量异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<List<ShopMapCustomerDetailDTO>> queryCustomerShopMap(MapCustomerQuery query) {
        ResultDO<List<ShopMapCustomerDetailDTO>> resultDO = new ResultDO<>();
        if(query == null){
            query = new MapCustomerQuery();
        }
        String checkScreenParam  = checkScreenParam(query);
        if(checkScreenParam!=null){
            resultDO.setErrMsg(checkScreenParam);
            return resultDO;
        }
        try {
            resultDO.setData(shopMapManager.queryCustomerShopMap(query));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "用户端店铺级别的地图异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<ShopCustomerDTO> viewShopSummaryById(MapSummaryQuery query) {
        ResultDO<ShopCustomerDTO> resultDO = new ResultDO<>();
        Shop shop = shopManager.getShopById(query.getShopId());
        if(shop == null){
            resultDO.setErrMsg("商铺不存在");
            return resultDO;
        }
        String lat = query.getLatitude();
        String lon = query.getLongitude();
        if(lat==""){
            query.setLatitude(null);
        }
        if(lon==""){
            query.setLongitude(null);
        }
        try {
            resultDO.setData(shopMapManager.viewShopSummaryById(query));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "用户端店铺级别的地图异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    @Override
    public ResultDO<List<ShopMapDTO>> queryClerkRegionHintMap(MapClerkQuery query) {
        ResultDO<List<ShopMapDTO>> resultDO = new ResultDO<>();
        if(query == null){
            resultDO.setErrMsg("请求出参数不能为空");
            return resultDO;
        }
        if(query.getType() == null || query.getType() < 0 || query.getType() > 1){
            resultDO.setErrMsg("查询类型错误");
            return resultDO;
        }
        try {
            resultDO.setData(shopMapManager.queryClerkRegionHintMap(query));
            resultDO.setSuccess(true);
        }catch (Exception e){
            Logger.error(e, "区域板块层级线索数量异常");
            resultDO.setErrMsg("系统异常，请联系管理员");
        }
        return resultDO;
    }

    private String checkScreenParam(BaseShopQuery query){
        if(!ValidateHelper.isEmpty(query.getRentList())){
            for(Integer rent: query.getRentList()){
                if(rent == null || rent < 1 || rent > 6)
                    return "租金参数错误";
            }
        }
        if(!ValidateHelper.isEmpty(query.getTransferList())){
            for(Integer transfer: query.getTransferList()){
                if(transfer == null || transfer < 1 || transfer > 6)
                    return "转让费参数错误";
            }
        }
        if(!ValidateHelper.isEmpty(query.getAreaList())){
            for(Integer area: query.getAreaList()){
                if(area == null || area < 1 || area > 7)
                    return "面积参数错误";
            }
        }
        return null;
    }
}
