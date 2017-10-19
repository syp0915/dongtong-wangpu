package com.dongtong.topic.manager;

import com.dongtong.customer.dto.CustomerInfoDTO;
import com.dongtong.customer.service.CustomerInfoService;
import com.dongtong.topic.dao.PretendUserMapper;
import com.dongtong.topic.domain.PretendUser;
import com.dongtong.topic.enums.UserType;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description
 * @package com.dongtong.topic.manager
 * @company dongtong
 * @copyright Copyright (c) 2016
 * @author chenxs
 * @date 2017/8/14 0014 11:36
 * @version v1.0.0
 */
@Service
public class UserInfoManager{

    @Autowired
    private PretendUserMapper pretendUserMapper;
    @Autowired
    private CustomerInfoService customerInfoService;
    /**
     * @description 用户信息内部类
     * @package com.dongtong.topic.manager
     * @company dongtong
     * @copyright Copyright (c) 2016
     * @author chenxs
     * @date 2017/8/14 0014 10:56
     * @version v1.2.0
     *
     * v1.2新增
     */
    class UserInfo {
        private String headPicture;
        private String nickName;
        private String signature;

        public String getHeadPicture() {
            return headPicture;
        }

        public void setHeadPicture(String headPicture) {
            this.headPicture = headPicture;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }

    /**
     * @description 根据帖子获取用户信息
     * @package com.dongtong.topic.manager
     * @author chenxs
     * @date 2017/8/14 0014 10:57
     * @param publisherType, publisherId
     * @return UserInfo
     *
     * v1.2新增
     */
    public UserInfo getUserInfo(Integer publisherType, Long publisherId){
        UserInfo userInfo = new UserInfo();
        if(publisherType != null){
            if(publisherType== UserType.MERCHANT.getValue()){//商户
                ResultDO<CustomerInfoDTO> customerinfo = customerInfoService.queryCustomerInfo(publisherId);//dubbo接口调用用户
                if(customerinfo.isSuccess()){
                    CustomerInfoDTO customerInfoRespDTO = customerinfo.getData();
                    if(!ValidateHelper.isEmpty(customerInfoRespDTO)) {
                        userInfo.setHeadPicture(customerInfoRespDTO.getHeadPortrait());
                        userInfo.setNickName(customerInfoRespDTO.getNickName());     //v1.2新增-昵称
                        userInfo.setSignature(customerInfoRespDTO.getSignature());    //v1.2新增-签名
                    }
                }
            }else if(publisherType== UserType.PRETEND.getValue()){    //v1.2修改-关联马甲
                PretendUser pretendUser = pretendUserMapper.selectPretendUserById(publisherId);
                if(pretendUser != null){
                    userInfo.setHeadPicture(pretendUser.getAvatar());
                    userInfo.setNickName(pretendUser.getName());     //v1.2新增-昵称
                    userInfo.setSignature(pretendUser.getSign());    //v1.2新增-签名
                }
            }
        }

        return userInfo;
    }
}
