package com.dongtong.customer.service;

import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.dto.CustomerSignDTO;
import com.dongtong.customer.dto.ServiceDetailDTO;
import com.dongtong.customer.dto.ServiceListDTO;
import com.dongtong.customer.dto.resp.IndexStatisticsRespDTO;
import com.dongtong.customer.dto.resp.LoginRespDTO;
import com.dongtong.customer.query.CustomerServiceQuery;
import com.dongtong.customer.query.ServiceListListQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/4 下午2:12.
 */
public interface CustomerService {

    /**
     * 用户登录
     * @param userPhone 登录手机号
     * @param smsVerifyCode 短信验证码
     * @param picVerifyCode 图片验证码
     * @param messageId 短信验证码消息id
     * @param inviteCode 邀请码
     * @return
     */
    public ResultDO<LoginRespDTO> customerLogin(String userPhone, String smsVerifyCode, Long messageId, String picVerifyCode, Long picVerifyId, String inviteCode, Integer osType,String deviceId);


    /**
     * 用户退出登录
     * @param customerId
     * @return
     */
    public ResultDO customerLogout(Long customerId, String token);

    /**
     * 更新用户deviceId
     * @param customerId
     * @param deviceId
     * @return
     */
    public ResultDO updateDeviceId(Long customerId, String deviceId);

    /**
     * 根据手机号获取用户信息
     * @param userPhone
     * @return
     */
    public ResultDO<Customer> getCustomerInfoByUserPhone(String userPhone);


    /**
     * 用户申请签约
     * @param dto
     * @return
     */
    ResultDO<Long> applySign(CustomerSignDTO dto,Long clerkId,String district,String shopAddress);

    /**
     * 预约看铺
     * @param dto
     * @return
     */
    ResultDO<Long> applyVisit(CustomerSignDTO dto, Long clerkId, String district, String shopAddress);

    /**
     *删除约看
     * @param shopId
     * @param customerId
     * @return
     */
    ResultDO<Boolean> cancelVisit(Long shopId,Long customerId);


    /**
     * @Description: 约看统计数据
     * @Title VisitStatistics
     * @Author  wuky
     * @Date 2017/5/15 9:17
     * @param
     * @return ResultDO<Integer>
     * @throws
     */
    Integer visitStatistics();

    /**
     * 首页-我的发布、约看、收藏、浏览、未来日程 总数统计接口
     * add by xiehb
     * @param customerId
     * @return
     */
    IndexStatisticsRespDTO indexStatistics(Long customerId);

    /**
     * 根据用户Id查询用户信息
     * @param customerId
     * @return
     */
    ResultDO<Customer> findCustomerInfoByCustomerId(Long customerId);

    /**
     * 找服务首页---是否新用户
     * add by xiehb
     * @param customerId
     * @return
     */
    Integer isNew(Long customerId);

    /**
     * @description 我的服务列表
     * @package com.dongtong.customer.service
     * @author liaozm
     * @date 2017/8/10 11:20
     * @params
     * @return
     */
    ResultDO<Page<ServiceListDTO>> myServiceList(ServiceListListQuery query);

    /**
     * @description 服务详情
     * @package com.dongtong.customer.service
     * @author liaozm
     * @date 2017/8/10 11:21
     * @params
     * @return
     */
    ResultDO<ServiceDetailDTO> queryServiceDetail(CustomerServiceQuery query);

    /**
     * @description 服务确认
     * @package com.dongtong.customer.service
     * @author liaozm
     * @date 2017/8/10 11:22
     * @params
     * @return
     */
    ResultDO<Boolean> confirmService(CustomerServiceQuery query);

    /**
     * @description 服务撤销
     * @package com.dongtong.customer.service
     * @author liaozm
     * @date 2017/8/10 11:22
     * @params
     * @return
     */
    ResultDO<Boolean> revokedService(CustomerServiceQuery query);
}
