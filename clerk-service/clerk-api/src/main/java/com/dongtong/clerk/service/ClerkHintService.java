package com.dongtong.clerk.service;

import com.dongtong.clerk.bo.ClerkHintBO;
import com.dongtong.clerk.domain.ClerkHint;
import com.dongtong.clerk.dto.ClerkHint4MapDTO;
import com.dongtong.clerk.dto.ClerkHintComfirmDTO;
import com.dongtong.clerk.dto.ClerkHintDTO;
import com.dongtong.clerk.dto.ClerkHintImgsDTO;
import com.dongtong.clerk.dto.*;
import com.dongtong.clerk.dto.resp.ReleaseTotalRespDTO;
import com.dongtong.clerk.query.*;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author sunyaping
 * @Package com.dongtong.clerk.service
 * @Description：线索相关Service
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-08 11:08
 * version V1.0.0
 **/
@Service
public interface ClerkHintService {
    /**
     * 发布金铺寻租线索
     * @return
     */
    ResultDO<Long> issueClue(ClerkHintDTO clerkHintDTO);

    /**
     * 业务员发布线索
     * @param clerkHintDTO
     * @return
     */
    ResultDO<Long> publishHintByClerk(ClerkHintDTO clerkHintDTO);

    /**
     * 根据线索id查找线索
     * @param clerkHintId
     * @return
     */
    ResultDO<ClerkHint> getClerkHintInfoById(Long clerkHintId);

    /**
     * 寻租老板人数统计
     * @return
     */
    ResultDO<ReleaseTotalRespDTO> releaseTotal();

    /**
     * 收铺线索列表接口
     */
    ResultDO <Page<ClerkHintBO>> getShopClueList(ClerkHintDTO clerkHintDTO,Page page);

    /**
     * 店铺线索详情
     * @param clerkHintDTO
     * @return
     */
    ResultDO<ClerkHintDTO> getShopClueInfo(ClerkHintDTO clerkHintDTO);

    /**
     * 店铺线索认领
     * @param clerkHintDTO
     * @return
     */
    ResultDO shopClaim(ClerkHintDTO clerkHintDTO);

    /**
     * 店铺线索修改时间
     * @param clerkHintDTO
     * @return
     */
    ResultDO updateSubscribeTime(ClerkHintDTO clerkHintDTO) throws ParseException;

    /***
     *  店铺线索废弃
     * @param clerkHintDTO
     * @return
     */
    ResultDO updateClueStatus(ClerkHintDTO clerkHintDTO);

    /**
     * 查询线索详情信息
     * @param clerkHintQuery
     * @return
     */
    ResultDO<ClerkHintDTO> queryClerkHintDetail(ClerkHintQuery clerkHintQuery);

    /**
     * 线索筛选列表查询
     * @return
     */
    ResultDO <Page<ClerkHintDTO>> queryClerkHintList(ClerkHintListQuery clerkHintListQuery);

    /**
     * 商铺地图线索列表查询
     * @param clerkHintList4MapQuery
     * @return
     */
    ResultDO <List<ClerkHint4MapDTO>> queryClerkHintListForMap(ClerkHintList4MapQuery clerkHintList4MapQuery);

    /**
     * 待实堪列表
     * @return
     */
    ResultDO<List<ClerkHintBO>> selectSchedule();

    /**
     * 查询过期数量
     * @param clerkHintDTO
     * @return
     */
    ResultDO<Integer> getDeadTimeCount(ClerkHintDTO clerkHintDTO);
    /**
     * 查询待办列表
     */
    ResultDO <Page<ClerkHintBO>> selectNeedByPage(ClerkHintDTO clerkHintDTO,Page page);

    /**
     * 修改线索状态
     * @param clerkHintDTO
     * @return
     */
    public ResultDO<Boolean> updateStatus(ClerkHintDTO clerkHintDTO);

    ResultDO updateClerkHintStatus(ClerkHintDTO clerkHintDTO);

    /**
     * 线索经营状态修改
     * @param clerkHintImgsDTO
     * @return
     */
    ResultDO updateShopImgInfo(ClerkHintImgsDTO clerkHintImgsDTO);

    /**
     * 线索位置信息修改
     * @param clerkHintDTO
     * @return
     */
    ResultDO updatePositionInfo(ClerkHintDTO clerkHintDTO);

    /**
     * 线索客户信息修改
     * @param clerkHintDTO
     * @return
     */
    ResultDO updateCustomerInfo(ClerkHintDTO clerkHintDTO);

    /**
     * 线索建筑信息修改
     * @param clerkHintDTO
     * @return
     */
    ResultDO updateBuildingInfo(ClerkHintDTO clerkHintDTO);

    /**
     * 线索数量统计
     * @param userId
     * @return
     */
    ResultDO hintStatistics(Long userId);

    /**
     * 线索确认有效
     * @param clerkHintComfirmDTO
     * @return
     */
    ResultDO confirmHint(ClerkHintComfirmDTO clerkHintComfirmDTO);

    /**
     * 交易员认领线索
     * @param clerkHintComfirmDTO
     * @return
     */
    ResultDO tradeClerkClaim(ClerkHintComfirmDTO clerkHintComfirmDTO);

    /*
     * 待确认、待核准线索列表
     * @param clerkHintTypeQuery
     * @Author zhoumin
     * @return
     */
    ResultDO <Page<ClerkHintList>> queryHintList(ClerkHintTypeQuery clerkHintTypeQuery);

    /**
     * 待确认、待核准详情
     * @param reqDTO
     * @Author zhoumin
     * @return
     */
    ResultDO<ClerkHintDetailDTO> getHintDetailInfo(ClerkHintDetailReqDTO reqDTO);

    /**
     * @description 业务员签约/约看列表v1.2
     * @package com.dongtong.clerk.service
     * @author liaozm
     * @date 2017/8/7 15:30
     * @params
     * @return
     */
    ResultDO<Page<ClerkScheduleListDTO>> querySignOrViewList(ClerkSignTypeQuery clerkSignTypeQuery);

    /**
     * @description 业务员签约/约看列表v1.2
     * @package com.dongtong.clerk.service
     * @author liaozm
     * @date 2017/8/8 10:15
     * @params
     * @return
     */
    ResultDO<ClerkSignOrViewDetailDTO> querySignOrViewDetail(SignOrViewQuery query);
}
