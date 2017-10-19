package com.dongtong.customer.service;

import com.dongtong.customer.dto.CustomerDTO;
import com.dongtong.customer.dto.CustomerInfoDTO;
import com.dongtong.customer.dto.req.AttentionReqDTO;
import com.dongtong.customer.dto.resp.StatisticDTO;
import com.shfc.common.result.ResultDO;

/**
 * Copyright: Copyright (c) 2016
 * Company:上海房产
 *
 * @author wky
 * @version V1.0
 * @create 2017-05-10 10:22
 **/
public interface CustomerInfoService {


    /**
     * @Description: 用户信息查询
     * @Title customerInfo
     * @Author  wuky
     * @Date 2017/5/9 17:17
     * @param customerId
     * @return ResultDO<LoanRespInfoDTO>
     * @throws
     */
    ResultDO<CustomerDTO> customerInfo(Long customerId);

    /**
     * @Description: 修改头像
     * @Title updateHeadPortrait
     * @Author  wuky
     * @Date 2017/5/9 17:17
     * @param customerId
     * @param headPortrait
     * @return ResultDO<LoanRespInfoDTO>
     *
     * v1.2废弃
     *    --修改用户头像改为调用修改用户信息接口
     */
    @Deprecated
    ResultDO<String> updateHeadPortrait(Long customerId,String headPortrait);

    /**
     * @Description: 用户信息查询(包括关注信息)
     * @Title queryCustomerInfo
     * @Author  wuky
     * @Date 2017/5/9 17:17
     * @param customerId
     * @return ResultDO<LoanRespInfoDTO>
     * @throws
     */
    ResultDO<CustomerInfoDTO> queryCustomerInfo(Long customerId);

    /**
     * 我收藏的金铺-收藏总数和排名统计接口
     * @param customerId
     * @return
     * add by xiehb
     */
    ResultDO<StatisticDTO> myCollectStatistic(Long customerId);

    /**
     * 取消收藏的商铺
     * @param shopId
     * @param customerId
     * @return
     * add by xiehb
     */
    ResultDO<Boolean> cancelShopCollected(Long shopId,Long customerId);

    /**
     * @Description: 商铺关注设置
     * @Title updateFollow
     * @Author  wuky
     * @Date 2017/5/11 10:17
     * @param dto
     * @return ResultDO<LoanRespInfoDTO>
     *
     * v1.2
     *    --修改入参
     *    --关注板块和关注行业可以多选
     */
    ResultDO<String> updateFollow(AttentionReqDTO dto);


    /**
     * 我浏览的商铺-排名统计
     * @param customerId
     * @return
     * add by xiehb
     */
    ResultDO<StatisticDTO> myBrowseStatistic(Long customerId);
    /**
     * 删除浏览的商铺
     * @param shopId
     * @param customerId
     * @return
     * add by xiehb
     */
    ResultDO<Boolean> deleteShopBrowse(Long shopId,Long customerId);


    /**
     * 我约看的店铺-约看总数和排名统计接口
     * @param customerId
     * @return
     * add by wuky
     */
    ResultDO<StatisticDTO> myVisitStatistic(Long customerId);


    /**
     * @description
     * @package com.dongtong.customer.service
     * @author chenxs
     * @date 2017/8/9 0009 15:48
     * @param dto
     * @return ResultDO
     *
     * v1.2新增
     *    --修改用户个人信息接口
     */
    ResultDO<String> updateCustomerInfo(CustomerInfoDTO dto);

}
