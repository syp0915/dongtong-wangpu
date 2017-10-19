package com.dongtong.topic.dao;

import com.dongtong.topic.domain.ContentTags;
import java.util.List;

import com.dongtong.topic.dto.req.HotTagsReqDTO;
import com.dongtong.topic.dto.resp.HotTagsRespDTO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.topic.dao.ContentTagsMapper.java
 * @Description: 
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author chen xiushen
 * @date 2017/08/04 17:24
 * version v1.0.0
 */
@Repository
public interface ContentTagsMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/08/04 17:24
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author chen xiushen
     * @Date 2017/08/04 17:24
     * @param record
     * @return int
     * @throws []
     */
    int insert(ContentTags record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author chen xiushen
     * @Date 2017/08/04 17:24
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ContentTags record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/08/04 17:24
     * @param id
     * @return com.dongtong.topic.domain.ContentTags
     * @throws []
     */
    ContentTags selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author chen xiushen
     * @Date 2017/08/04 17:24
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ContentTags record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author chen xiushen
     * @Date 2017/08/04 17:24
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ContentTags record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author chen xiushen
     * @Date 2017/08/04 17:24
     * @return null
     * @throws []
     */
    List<ContentTags> selectByPage(Page<ContentTags> page);

    /**
     * @description 获取指定文章的标签列表
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/7 0007 14:44
     * @param contentId
     * @return List<HotTagsRespDTO>
     */
    List<HotTagsRespDTO> selectTagListByContentId(Long contentId);

    /**
     * @description 获取热门标签列表
     * @package com.dongtong.topic.dao
     * @author chenxs
     * @date 2017/8/7 0007 14:44
     * @param hotTagsReqDTO
     * @return List<HotTagsRespDTO>
     */
    List<HotTagsRespDTO> selectTagListByType(HotTagsReqDTO hotTagsReqDTO);
}