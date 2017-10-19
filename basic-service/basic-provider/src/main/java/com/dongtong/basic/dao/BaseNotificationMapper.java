package com.dongtong.basic.dao;

import com.dongtong.basic.domain.BaseNotification;
import com.dongtong.basic.dto.req.BaseNoticeReqDTO;
import com.dongtong.basic.dto.resp.NoticeRespDTO;
import com.dongtong.basic.query.NoticeQuery;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.basic.dao.BaseNotificationMapper.java
 * @Description: 通知表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:07
 * version v1.0.0
 */
@Repository
public interface BaseNotificationMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:07
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:07
     * @param record
     * @return int
     * @throws []
     */
    int insert(BaseNotification record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:07
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(BaseNotification record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:07
     * @param id
     * @return com.dongtong.basic.domain.BaseNotification
     * @throws []
     */
    BaseNotification selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:07
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(BaseNotification record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:07
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(BaseNotification record);

    /**
     * 查询未读消息数
     * @param baseNoticeDTO
     * @return
     */
    int selectUnReadByReceiver(BaseNoticeReqDTO baseNoticeDTO);

    /**
     * 根据消息类型更新
     * @param record
     * @return
     */
    int updateStatusByType(BaseNotification record);

    /**
     * 根据接收人类型查询通知列表
     * @param baseNoticeDTO
     * @return
     */
    List<NoticeRespDTO> selectNoticeList(BaseNoticeReqDTO baseNoticeDTO);

    /**
     * 根据通知类型和接收人查询通知列表
     * @param page
     * @return
     */
    List<BaseNotification> selectNoticeListByType(Page<NoticeQuery> page);

    /**
     * 评论帖子的人数
     * @param baseNoticeReqDTO
     * @return
     */
    int selectBusNoticeCount(BaseNoticeReqDTO baseNoticeReqDTO);

    /**
     * 根据Id更新状态
     * @param msgId
     * @return
     */
    int updateById(Long msgId);

    /**
     * @description
     * @package com.dongtong.basic.dao
     * @author chenxs
     * @date 2017/8/22 0022 16:06
     * @param
     * @return
     */
    int updateByBusId(BaseNotification baseNotification);
}