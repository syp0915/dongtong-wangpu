package com.dongtong.customer.dao;

import com.dongtong.customer.domain.CustomerSign;
import com.dongtong.customer.dto.TenantAndLandlordDTO;
import com.dongtong.customer.dto.req.SignReqDTO;
import com.dongtong.customer.dto.resp.SignRespDTO;
import com.shfc.mybatis.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.customer.dao.CustomerSignMapper.java
 * @Description: 商铺签约表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:45
 * version v1.0.0
 */
@Repository
public interface CustomerSignMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:45
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:45
     * @param record
     * @return int
     * @throws []
     */
    int insert(CustomerSign record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:45
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(CustomerSign record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:45
     * @param id
     * @return com.dongtong.customer.domain.CustomerSign
     * @throws []
     */
    CustomerSign selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:45
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(CustomerSign record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:45
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(CustomerSign record);

    List<SignRespDTO> selectByPage(@Param("signReqDTO") SignReqDTO signReqDTO, Page page);

    SignRespDTO selectById(Long id);


    CustomerSign querySign(@Param("customerId")Long customerId,@Param("shopId") long shopId);

   int getDeadTimeNum(SignReqDTO signReqDTO);

    List<SignRespDTO> selectNeedByPage(@Param("signReqDTO") SignReqDTO signReqDTO, Page page);

    List<CustomerSign> pendingList();

    TenantAndLandlordDTO getTenantAndLandlord(@Param("id") Long id);
}