package com.dongtong.customer.dao;

import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.dto.CustomerDTO;
import com.dongtong.customer.dto.CustomerInfoDTO;
import com.dongtong.customer.dto.ServiceDetailDTO;
import com.dongtong.customer.dto.ServiceListDTO;
import com.dongtong.customer.dto.resp.IndexStatisticsRespDTO;
import com.dongtong.customer.query.CustomerServiceQuery;
import com.shfc.mybatis.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.customer.dao.CustomerMapper.java
 * @Description: 用户账户表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:39
 * version v1.0.0
 */
@Repository
public interface CustomerMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:39
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:39
     * @param record
     * @return int
     * @throws []
     */
    int insert(Customer record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:39
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(Customer record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:39
     * @param id
     * @return com.dongtong.customer.domain.Customer
     * @throws []
     */
    Customer selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:39
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:39
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(Customer record);

    /**
     * 根据用户手机号查找用户信息
     * @param userPhone
     * @return
     */
    Customer findCustomerInfoByUserPhone(String userPhone);

    /**
     * 根据用户Id查找用户信息
     * @param customerId
     * @return
     */
    Customer selectByCustomerId(Long customerId);

    /**
     * 根据用户Id查找用户信息
     * @param customerId
     * @return
     */
    CustomerInfoDTO queryInfoByCustomerId(Long customerId);

    /**
     * 根据用户Id查找用户信息
     * @param customerId
     * @return
     */
    CustomerDTO queryCustomerInfo(Long customerId);

    /**
     * 根据用户手机号获取用户信息
     * @param userPhone
     * @return
     */
    Customer getCustomerInfoByUserPhone(String userPhone);

    /**
     * 首页-我的发布、约看、收藏、浏览、未来日程 总数统计接口
     * @param customerId
     * @return
     */
    IndexStatisticsRespDTO indexStatistics(@Param("customerId") Long customerId);


    /**
     * @description 我的服务列表
     * @package com.dongtong.customer.dao
     * @author liaozm
     * @date 2017/8/11 15:06
     * @params
     * @return
     */
    List<ServiceListDTO> selectServiceList(Page<ServiceListDTO> page);

    /**
     * @description 线索服务详情
     * @package com.dongtong.customer.dao
     * @author liaozm
     * @date 2017/8/11 16:56
     * @params
     * @return
     */
    ServiceDetailDTO selectServiceHintDetail(CustomerServiceQuery query);

    /**
     * @description 预约服务详情
     * @package com.dongtong.customer.dao
     * @author liaozm
     * @date 2017/8/11 16:56
     * @params
     * @return
     */
    ServiceDetailDTO selectServiceVisitDetail(CustomerServiceQuery query);

    /**
     * @description 签约服务详情
     * @package com.dongtong.customer.dao
     * @author liaozm
     * @date 2017/8/11 16:56
     * @params
     * @return
     */
    ServiceDetailDTO selectServiceSignDetail(CustomerServiceQuery query);

    /**
     * @description 确认约看
     * @package com.dongtong.customer.dao
     * @author liaozm
     * @date 2017/8/11 17:41
     * @params
     * @return
     */
    int confirmVisit(Long id);

    /**
     * @description 确认签约
     * @package com.dongtong.customer.dao
     * @author liaozm
     * @date 2017/8/11 17:43
     * @params
     * @return
     */
    int confirmSign(Long id);

    /**
     * @description 撤销约看
     * @package com.dongtong.customer.dao
     * @author liaozm
     * @date 2017/8/11 17:41
     * @params
     * @return
     */
    int revokedVisit(Long id);

    /**
     * @description 撤销签约
     * @package com.dongtong.customer.dao
     * @author liaozm
     * @date 2017/8/11 17:43
     * @params
     * @return
     */
    int revokedSign(Long id);

    /**
     * @description 取消线索
     * @package com.dongtong.customer.dao
     * @author liaozm
     * @date 2017/8/11 17:48
     * @params
     * @return
     */
    int revokedHint(Long id);

}