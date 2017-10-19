package com.dongtong.basic.dao;

import com.dongtong.basic.dto.ClerkDTO;
import com.dongtong.basic.dto.RankingDTO;
import com.dongtong.basic.dto.RankingInfoDTO;
import com.shfc.mybatis.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @Package com.dongtong.basic.dao.RankingMapper
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author wliao
 * @date 2017/5/15 10:03
 * version V1.0.0
 */
@Repository
public interface RankingMapper {
    /**
     * @Description: 根据邀请码查询用户邀请码数量
     * @Title deleteByPrimaryKey
     * @Author liaozm
     * @Date 2017/05/08 15:09
     * @param startDate
     * @return int
     * @throws []
     */
    List<RankingInfoDTO> getCostomerByInviCode(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查看历史最好名次 周
     * @param userid
     * @param classify
     * @return
     */
    Integer getClerkHistoryBestRankingByWeek(@Param("userid") String userid,@Param("classify") int classify);

    /**
     * 查询历史最好名次 月
     * @param userid
     * @param classify
     * @return
     */
    Integer getClerkHistoryBestRankingByMonth(@Param("userid") String userid,@Param("classify") int classify);

    /**
     * 查询新增商铺排名
     * @param startDate
     * @param endDate
     * @return
     */
    List<HashMap> getClerkShop(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询新增核准被转化为店铺排名
     * @param startDate
     * @param endDate
     * @return
     */
    List<HashMap> getClerkHitToShop(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询新增线索被确认为有效
     * @param startDate
     * @param endDate
     * @return
     */
    List<HashMap> getClerkHit(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询约看表 状态是已踩盘的
     * @param startDate
     * @param endDate
     * @return
     */
    List<HashMap> getClerkVisitShop(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询签约表 状态是已签约上传合同
     * @param startDate
     * @param endDate
     * @return
     */
    List<HashMap> getClerkSign(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询新增商铺排名 分页
     * @param page
     * @return
     */
    List<RankingDTO> getClerkShopToPage(Page<RankingDTO> page);

    /**
     * 查询新增商铺排名 分页
     * @param page
     * @return
     */
    List<RankingDTO> getClerkVisitShopToPage(Page<RankingDTO> page);

    /**
     * 查询新增商铺排名 分页
     * @param page
     * @return
     */
    List<RankingDTO> getClerkHitToShopPage(Page<RankingDTO> page);

    /**
     * 查询新增商铺排名 分页
     * @param page
     * @return
     */
    List<RankingDTO> getClerkHitPage(Page<RankingDTO> page);

    /**
     * 查询新增商铺排名 分页
     * @param page
     * @return
     */
    List<RankingDTO> getClerkSignToPage(Page<RankingDTO> page);

    /**
     * 查询新增商铺排名 分页
     * @param page
     * @return
     */
    List<RankingDTO> getClerkCustomerToPage(Page<RankingDTO> page);

    ClerkDTO getClerkInfoById(Long userId);
}
