package com.dongtong.shop.service;

import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.query.*;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;

import java.util.List;

/**
 * @Package com.dongtong.shop.service.ShopService
 * @Description: 商铺接口
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 15:12
 * version V1.0.0
 */
public interface ShopService {

    /**
     * 客户端查询旺铺列表
     * @Author lv bin
     * @param query
     * @param customerId
     * @return
     */
    ResultDO<Page<ShopCustomerDTO>> queryShopByCustomer(ShopCustomerQuery query, Long customerId);

    /**
     * 业务端查询旺铺列表
     * @Author lv bin
     * @param query
     * @return
     */
    ResultDO<Page<ShopClerkDTO>> queryShopByClerk(ShopClerkQuery query);

    /**
     * 显示最近正在转让的一个旺铺
     * @Author lv bin
     * @return
     */
    ResultDO<LatestShopDTO> getLatestShop();

    /**
     * 查询某个用户最近正在转让的一个旺铺
     * @Author lv bin
     * @return
     */
    ResultDO<LatestShopDTO> getLatestShopByPhone(String phone);

    /**
     * 旺铺被看总数和排名统计
     * @Author lv bin
     * @param phone-用户手机号
     * @return
     */
    ResultDO<ShopScanCountDTO> queryMyShopScanCount(String phone);

    /**
     * 发布商铺
     * add by xiehb
     * @param shopDTO
     * @return
     */
    ResultDO<Long> issueShop(IssueShopDTO shopDTO);

    /**
     * 修改商铺-租售信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    ResultDO<Boolean> updateShopRent(IssueShopDTO shopDTO);
    /**
     * 修改商铺-客户信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    ResultDO<Boolean> updateShopContacter(IssueShopDTO shopDTO);
    /**
     * 修改商铺-经营状况信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    ResultDO<Boolean> updateShopOperateState (IssueShopDTO shopDTO);
    /**
     * 修改商铺-租赁相关的信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    ResultDO<Boolean> updateShopRentParam (IssueShopDTO shopDTO);
    /**
     * 修改商铺-位置相关的信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    ResultDO<DistrictBlockDTO> updateShopSite (IssueShopDTO shopDTO);
    /**
     * 修改商铺-建筑相关的信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    ResultDO<Boolean> updateShopBuilding (IssueShopDTO shopDTO);
    /**
     * 修改商铺-营运相关的信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    ResultDO<Boolean> updateShopService (IssueShopDTO shopDTO);
    /**
     * 修改商铺-配套设施相关的信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    ResultDO<Boolean> updateShopSupport (IssueShopDTO shopDTO);

    /**
     * 修改商铺-临铺信息
     * add by xiehb
     * @param shopDTO
     * @return
     */
    ResultDO<List<ShopNearDetailDTO>> updateShopNear (IssueShopDTO shopDTO);

    /**
     * 删除临铺
     * add by xiehb
     * @param nearId
     * @return
     */
    ResultDO<Boolean> delShopNear(Long nearId,Long currentId);

    /**
     * 查询临铺图片
     * add by xiehb
     * @param nearId
     * @return
     */
    ResultDO<List<ShopImgDTO>> shopNearImg(Long nearId);

    /**
     * 客户端 客户收藏的商铺列表
     * add by xiehb
     */
    ResultDO<Page<ShopCollectedCustomerDTO>> shopCollectedList(ShopCollectedQuery query);

    /**
     * 客户端 客户浏览的商铺列表
     * add by xiehb
     */
    ResultDO<Page<ShopBrowseCustomerDTO>> shopBrowseList(ShopCollectedQuery query);


    /**
     * 我发布的金铺列表接口
     * @Author lv bin
     * @param query-分页
     * @param phone-用户手机号
     * @return
     */
    ResultDO<Page<ShopCustomerPublishDTO>> queryMyPublishShop(BaseQuery query, String phone);

    /**
     * 商铺下架
     * @param shopUndoDTO
     * @return
     */
    ResultDO<Boolean> unDoShop(ShopUndoDTO shopUndoDTO);

    /**
     * 商铺上架
     * @param clerkId
     * @param shopId
     * @return
     */
    ResultDO<Boolean> putAwayShop(Long clerkId, Long shopId);

    /**
     * 客户端：查询旺铺总数统计接口
     * @Author zhoumin
     * @return
     */
    ResultDO<ShopCountDTO> countShopFromClient();

    /**
     * 业务端：查询全部商铺以及本月发布商铺数目接口
     * @Author zhoumin
     * @param clerkId
     * @return
     */
    ResultDO<ShopCountDTO> countShopFromService(Long clerkId);

    /**
     * 铺位照片查询
     * @Author zhoumin
     * @param shopId
     * @return
     */
    ResultDO<List<ShopImgDTO>> queryShopImg(Long shopId);

    /**
     * 商铺详情
     * @Author zhoumin
     * @param shopId
     * @return
     */
    ResultDO<ShopDetailDTO> queryShopDetail(Long shopId);

    /**
     * 查询业务员信息
     * @param shopId
     * @return
     */
    ClerkDTO queryClerkByShopId (Long shopId);

    /**
     * 查询商铺和业务员相关信息
     * @param shopId
     * @return
     * add by xiehb
     */
    ClerkAndShopInfoDTO queryClerkAndShopInfoByShopId(Long shopId);

    /**
     * 客户端 客户约看的商铺列表
     */
    ResultDO<Page<ShopVisitCustomerDTO>> shopVisitedList(ShopVisitQuery query);

    /**
     * 客户端 店铺数统计
     */
    Integer countShops();

    /**
     * 客户端 根据二维码编号查找商铺id
     * @param shopCode
     * @return
     */
    ResultDO<Long> getShopId(String shopCode);

    /**
     * 业务端 更新商铺中认领人信息
     * @Author zhoumin
     * @param shopId
     * @param claimId
     * @return 认领时间
     */
    ResultDO<String> updateClaimInfo(Long shopId,Long claimId);

    /**
     * 二维码生成接口
     * @return
     */
    ResultDO<String> shopCodeGenerate(ShopCodeDTO dto);

    /**
     * 根据id获取商铺信息
     * @param id
     * @return
     */
    ResultDO<Shop> getShopById(Long id);

    /**
     * @description
     * @package com.dongtong.shop.service
     * @author chenxs
     * @date 2017/8/11 0011 16:29
     * @param shopRecomendQuery
     * @return ResultDO<ShopRecomendListDTO>
     */
    ResultDO<ShopRecomendListDTO> getRecomendShop(ShopRecomendQuery shopRecomendQuery);
    /**
     * 根据id获取商铺信息
     * @param hitId
     * @return
     */
    ResultDO<Shop> getShopByHitId(Long hitId);

    /**
     * 定时跑批商铺
     */
    void calculateShopScore();
}
