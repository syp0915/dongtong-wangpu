package com.dongtong.clerk.dto.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 合同
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/11 19:38
 * @since 1.0
 */
@Data
public class ClerkAgreementDTO implements Serializable {
    @NotBlank(message = "店铺地址不能为空")
    private String shopAddress;
    @NotBlank(message = "开始时间不能为空")
    private String startTime;
    @NotBlank(message = "结束时间不能为空")
    private String endTime;
    @NotNull(message = "租金不能为空")
    private Double rent;
    @NotNull(message = "转让费不能为空")
    private Double transferFee;
    @NotBlank(message = "出租方姓名不能为空")
    private String rentName;
    @Length(message = "手机号码长度最大11位",max = 11)
    private String rentMobile;
    @NotBlank(message = "承租方姓名不能为空")
    private String lesseeName;
    @Length(message = "手机号码长度最大11位",max = 11)
    private String lesseeMobile;
    @NotEmpty(message = "图片url不能为空")
    private List<String> agreementUrl;
    @NotNull(message = "收租方式不能为空")
    private Integer rentWay;
    @NotNull(message = "签约id不能为空")
    private Long signId;
    @NotNull(message = "区域id不能为空")
    private Long districtId;
    @NotBlank(message = "区域名称不能为空")
    private String districtName;
    @NotNull(message = "板块id不能为空")
    private Long blockId;
    @NotBlank(message = "板块名称不能为空")
    private String blockName;
    private  Long clerkId;
}
