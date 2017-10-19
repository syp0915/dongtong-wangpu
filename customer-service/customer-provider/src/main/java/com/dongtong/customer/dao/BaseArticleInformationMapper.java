package com.dongtong.customer.dao;

import com.dongtong.customer.domain.BaseArticleInformation;
import com.dongtong.customer.dto.req.ArticleReqDTO;
import com.dongtong.customer.dto.resp.ArticleRespDTO;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.customer.dao.BaseArticleInformationMapper.java
 * @Description: 文章资讯
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author lv bin
 * @date 2017/05/24 10:08
 * version v1.0.0
 */
@Repository
public interface BaseArticleInformationMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author lv bin
     * @Date 2017/05/24 10:08
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author lv bin
     * @Date 2017/05/24 10:08
     * @param record
     * @return int
     * @throws []
     */
    int insert(BaseArticleInformation record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author lv bin
     * @Date 2017/05/24 10:08
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(BaseArticleInformation record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author lv bin
     * @Date 2017/05/24 10:08
     * @param id
     * @return com.dongtong.customer.domain.BaseArticleInformation
     * @throws []
     */
    BaseArticleInformation selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author lv bin
     * @Date 2017/05/24 10:08
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(BaseArticleInformation record);

    /**
     * @Title updateByPrimaryKeyWithBLOBs
     * @Author lv bin
     * @Date 2017/05/24 10:08
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeyWithBLOBs(BaseArticleInformation record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author lv bin
     * @Date 2017/05/24 10:08
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(BaseArticleInformation record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author lv bin
     * @Date 2017/05/24 10:08
     * @return null
     * @throws []
     */
    List<BaseArticleInformation> selectByPage(Page<BaseArticleInformation> page);

    ArticleRespDTO selectById(ArticleReqDTO articleReqDTO);
}