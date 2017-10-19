package com.dongtong.topic.dao;

import com.dongtong.topic.domain.Content;
import java.util.List;

import com.dongtong.topic.dto.req.ContentDetailReqDTO;
import com.dongtong.topic.dto.req.ContentGuideDetailReqDTO;
import com.dongtong.topic.dto.resp.*;
import com.dongtong.topic.query.ContentGuideListQuery;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.topic.dao.ContentMapper.java
 * @Description: 
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author chen xiushen
 * @date 2017/08/04 16:36
 * version v1.0.0
 */
@Repository
public interface ContentMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/08/04 16:36
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author chen xiushen
     * @Date 2017/08/04 16:36
     * @param record
     * @return int
     * @throws []
     */
    int insert(Content record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author chen xiushen
     * @Date 2017/08/04 16:36
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(Content record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/08/04 16:36
     * @param id
     * @return com.dongtong.topic.domain.Content
     * @throws []
     */
    Content selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author chen xiushen
     * @Date 2017/08/04 16:36
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(Content record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/08/04 16:36
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(Content record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author chen xiushen
     * @Date 2017/08/04 16:36
     * @return null
     * @throws []
     */
    List<Content> selectByPage(Page<Content> page);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/7 0007 13:59
     * @param page
     * @return List<ContentListRespDTO>
     */
    List<ContentListRespDTO> selectContentList(Page<ContentListRespDTO> page);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/7 0007 14:00
     * @param page
     * @return List<ContentListRespDTO>
     */
    List<ContentListRespDTO> selectContentListByTagId(Page<ContentListRespDTO> page);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/7 0007 14:38
     * @param contentDetailReqDTO
     * @return ContentDetailRespDTO
     */
    ContentDetailRespDTO selectContentDetailById(ContentDetailReqDTO contentDetailReqDTO);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/7 0007 14:00
     * @param content
     * @return Integer
     */
    Integer updateViewCount(Content content);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/7 0007 14:00
     * @param content
     * @return Integer
     */
    Integer updateGoodCount(Content content);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/7 0007 14:00
     * @param content
     * @return Integer
     */
    Integer updateBadCount(Content content);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/8 0008 11:07
     * @param query
     * @return List<HomePageGuideListRespDTO>
     */
    List<HomePageGuideListRespDTO> selectHomePageGuideList(Page<HomePageGuideListRespDTO> query);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/8 0008 11:07
     * @param query
     * @return List<ContentGuideListRespDTO>
     */
    List<ContentGuideListRespDTO> selectGuideList(Page<ContentGuideListRespDTO> query);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/8 0008 11:07
     * @param contentDetailReqDTO
     * @return ContentGuideDetailRespDTO
     */
    ContentGuideDetailRespDTO selectGuideDetail(ContentGuideDetailReqDTO contentDetailReqDTO);

    /**
     * @description
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/11 0011 17:50
     * @param contentId
     * @return List<ShopListRespDTO>
     */
    List<ShopListRespDTO> selectShopListByContent(Long contentId);
}