package com.dongtong.app.ao;

import com.dongtong.app.constant.ErrorConstant;
import com.dongtong.app.constant.OwnerTypeEnum;
import com.dongtong.app.constant.RuleTypeEnum;
import com.dongtong.app.utils.DateValidate;
import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.basic.dto.req.ServiceNoticeReqDTO;
import com.dongtong.basic.enums.ReceiveType;
import com.dongtong.basic.enums.ServiceNoticeType;
import com.dongtong.basic.service.NoticePushService;
import com.dongtong.basic.service.SmsService;
import com.dongtong.clerk.bo.ClerkHintBO;
import com.dongtong.clerk.domain.Clerk;
import com.dongtong.clerk.dto.*;
import com.dongtong.clerk.dto.ShopImgDTO;
import com.dongtong.clerk.query.*;
import com.dongtong.clerk.service.ClerkHintService;
import com.dongtong.clerk.service.ClerkRemarkService;
import com.dongtong.clerk.service.ClerkService;
import com.dongtong.customer.domain.Customer;
import com.dongtong.customer.domain.CustomerSchedule;
import com.dongtong.customer.service.CustomerService;
import com.dongtong.customer.service.ScheduleService;
import com.dongtong.shop.dto.*;
import com.shfc.common.base.ValidateHelper;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Package com.dongtong.app.ao.ClerkHintAO
 * @Description: TODO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author zhanghz
 * @date 2017/5/9 15:05
 * version V1.0.0
 */
@Slf4j
@Service
public class ClerkHintAO {
	@Autowired
	private ClerkHintService clerkHintService;
	@Autowired
	private NoticePushService noticePushService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private ClerkService clerkService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private ClerkRemarkService clerkRemarkService;

	/**
	 * 发布线索
	 * @param clerkHintDTO
	 * @return
	 */
	public ResultDO<Long> publishHint(ClerkHintDTO clerkHintDTO) {
		ResultDO checkResult = checkPublishHint(clerkHintDTO);
		if(!checkResult.isSuccess()){
			return checkResult;
		}
		if(!ValidateHelper.isEmpty(clerkHintDTO.getIssuerId())){
			ResultDO<Clerk> clerkInfo = clerkService.getClerkInfoById(clerkHintDTO.getIssuerId());
			if(clerkInfo.isSuccess()){
				Clerk clerk = clerkInfo.getData();
				if(!ValidateHelper.isEmpty(clerk.getId()) && clerk.getRoleType()!= RuleTypeEnum.EXPAND_SHOP_CLERK.getValue()){
					checkResult.setSuccess(false);
					checkResult.setErrCode(ErrorConstant.CLERK_RULE_ERROR.getCode());
					checkResult.setErrMsg(ErrorConstant.CLERK_RULE_ERROR.getMsg());
					return checkResult;
				}
			}else{
				checkResult.setSuccess(false);
				checkResult.setErrCode(ErrorConstant.CLERK_RULE_ERROR.getCode());
				checkResult.setErrMsg(ErrorConstant.CLERK_RULE_ERROR.getMsg());
				return checkResult;
			}
		}else{
			checkResult.setSuccess(false);
			checkResult.setErrCode(ErrorConstant.CLERK_RULE_ERROR.getCode());
			checkResult.setErrMsg(ErrorConstant.CLERK_RULE_ERROR.getMsg());
			return checkResult;
		}
		ResultDO<Long> result = clerkHintService.publishHintByClerk(clerkHintDTO);
		return result;
	}

	private ResultDO checkPublishHint(ClerkHintDTO clerkHintDTO) {
		ResultDO checkDO = new ResultDO();
		if(clerkHintDTO==null){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_REQ_DATA.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_REQ_DATA.getMsg());
			return checkDO;
		}

		//商铺地址
		String address=clerkHintDTO.getShopAddress();
		if(ValidateHelper.isEmptyString(address)){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_SHOP_ADDRESS.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_SHOP_ADDRESS.getMsg());
			return checkDO;
		}

		//联系人
		String linkmanName=clerkHintDTO.getLinkmanName();
		if(ValidateHelper.isEmptyString(linkmanName)){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_LINKMAN_NAME.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_LINKMAN_NAME.getMsg());
			return checkDO;
		}

		//联系电话
		String linkmanPhone=clerkHintDTO.getLinkmanPhone();
		if(ValidateHelper.isEmptyString(linkmanPhone)){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_LINKMAN_PHONE.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_LINKMAN_PHONE.getMsg());
			return checkDO;
		}

		//经纬度
		if(ValidateHelper.isEmptyString(clerkHintDTO.getLatitude())||ValidateHelper.isEmptyString(clerkHintDTO.getLongitude())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_LONGITUDE_LATITUDE.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_LONGITUDE_LATITUDE.getMsg());
			return checkDO;
		}

		//线索来源
		if(ValidateHelper.isEmpty(clerkHintDTO.getHintFrom())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_HINT_FROM.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_HINT_FROM.getMsg());
			return checkDO;
		}

		//线索图片
		if(ValidateHelper.isEmpty(clerkHintDTO.getShopImgList())||clerkHintDTO.getShopImgList().size()==0){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_HINT_SHOP_IMG.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_HINT_SHOP_IMG.getMsg());
			return checkDO;
		}

		//楼层信息
		if(ValidateHelper.isEmpty(clerkHintDTO.getTotalFloor()) || ValidateHelper.isEmpty(clerkHintDTO.getFloor())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_FLOOR.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_FLOOR.getMsg());
			return checkDO;
		}

		//是否张贴海报
		if(ValidateHelper.isEmpty(clerkHintDTO.getHasPoster())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_HAS_POSTER.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_HAS_POSTER.getMsg());
			return checkDO;
		}

		//展示手机信息标志
		if(ValidateHelper.isEmpty(clerkHintDTO.getIsShow())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_IS_SHOW.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_IS_SHOW.getMsg());
			return checkDO;
		}

		//展示地址信息标志
		if(ValidateHelper.isEmpty(clerkHintDTO.getAddrIsShow())){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_ADDR_IS_SHOW.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_ADDR_IS_SHOW.getMsg());
			return checkDO;
		}

		checkDO.setSuccess(true);
		return checkDO;
	}

	/**
	 * 线索详情查询
	 * @param clerkHintQuery
	 * @return
	 */
	public ResultDO<ClerkHintDTO> queryHintDetail(ClerkHintQuery clerkHintQuery) {
		ResultDO checkResult = checkQueryHintDetail(clerkHintQuery);
		if(!checkResult.isSuccess()){
			return checkResult;
		}
		ResultDO<ClerkHintDTO> result = clerkHintService.queryClerkHintDetail(clerkHintQuery);
		return result;
	}

	/**
	 * 线索详情查询校验
	 * @param clerkHintQuery
	 * @return
	 */
	private ResultDO checkQueryHintDetail(ClerkHintQuery clerkHintQuery) {
		ResultDO checkDO = new ResultDO();
		if(clerkHintQuery==null){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_REQ_DATA.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_REQ_DATA.getMsg());
			return checkDO;
		}

		//线索ID非空校验
		Long clerkId=clerkHintQuery.getClerkId();
		if(ValidateHelper.isEmpty(clerkId)){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_CLERK_HINT_ID.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_CLERK_HINT_ID.getMsg());
			return checkDO;
		}
		checkDO.setSuccess(true);
		return checkDO;
	}

	/**
	 * 线索筛选列表
	 * @param clerkHintListQuery
	 * @return
	 */
	public ResultDO<Page<ClerkHintDTO>> queryHintList(ClerkHintListQuery clerkHintListQuery) {
//		ResultDO checkResult = checkQueryHintList(clerkHintListQuery);
//		if(!checkResult.isSuccess()){
//			return checkResult;
//		}
		ResultDO<Page<ClerkHintDTO>> result = clerkHintService.queryClerkHintList(clerkHintListQuery);
		return result;
	}

//	private ResultDO checkQueryHintList(ClerkHintListQuery clerkHintListQuery) {
//		ResultDO checkDO = new ResultDO();
//		if(ValidateHelper.isEmpty(clerkHintListQuery.getLongitude())
//				&& ValidateHelper.isEmpty(clerkHintListQuery.getLatitude())){
//			checkDO.setSuccess(false);
//			checkDO.setErrCode(ErrorConstant.NULL_LON_OR_LAT.getCode());
//			checkDO.setErrMsg(ErrorConstant.NULL_LON_OR_LAT.getMsg());
//			return checkDO;
//		}
//		checkDO.setSuccess(true);
//		return checkDO;
//	}

	/**
	 * 商铺地图线索列表查询
	 * @param clerkHintList4MapQuery
	 * @return
	 */
	public ResultDO<List<ClerkHint4MapDTO>> queryClerkHintListForMap(ClerkHintList4MapQuery clerkHintList4MapQuery) {
		ResultDO checkResult = checkClerkHintList4Map(clerkHintList4MapQuery);
		if(!checkResult.isSuccess()){
			return checkResult;
		}

		if(ValidateHelper.isEmpty(clerkHintList4MapQuery.getOwnType())){
			clerkHintList4MapQuery.setOwnType(OwnerTypeEnum.ALL.getValue());
		}
		if(clerkHintList4MapQuery.getOwnType() == OwnerTypeEnum.MY.getValue()){
			Long ownerId =  HttpSessionUtils.getCurrentAppUserId();
			clerkHintList4MapQuery.setOwnerId(ownerId);
		}

		ResultDO<List<ClerkHint4MapDTO>> result = clerkHintService.queryClerkHintListForMap(clerkHintList4MapQuery);
		return result;
	}

	private ResultDO checkClerkHintList4Map(ClerkHintList4MapQuery clerkHintList4MapQuery) {
		ResultDO checkDO = new ResultDO();
		if(ValidateHelper.isEmpty(clerkHintList4MapQuery)||ValidateHelper.isEmpty(clerkHintList4MapQuery.getBlockId())
				&& (ValidateHelper.isEmpty(clerkHintList4MapQuery.getMaxLat())
				|| ValidateHelper.isEmpty(clerkHintList4MapQuery.getMinLat())
				|| ValidateHelper.isEmpty(clerkHintList4MapQuery.getMaxLon())
				|| ValidateHelper.isEmpty(clerkHintList4MapQuery.getMinLon()))){
			checkDO.setSuccess(false);
			checkDO.setErrCode(ErrorConstant.NULL_BLOCKID_OR_LON_LAT.getCode());
			checkDO.setErrMsg(ErrorConstant.NULL_BLOCKID_OR_LON_LAT.getMsg());
			return checkDO;
		}
		checkDO.setSuccess(true);
		return checkDO;

	}

	/**
	 * 线索列表
	 * @param clerkHintDTO
     * @return
     */
	public ResultDO <Page<ClerkHintBO>> getShopClueList(ClerkHintDTO clerkHintDTO) {
		Page page =new Page();
		ResultDO res=new ResultDO();
		if(clerkHintDTO.getPageSize()==0){
			res.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
			res.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			return res;
		}
		if(clerkHintDTO.getPageNumber()==0){
			res.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
			res.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			return res;
		}
		page.setPageNumber(clerkHintDTO.getPageNumber());
		page.setPageSize(clerkHintDTO.getPageSize());
		clerkHintDTO.setOwnerId(HttpSessionUtils.getCurrentAppUserId());
		return clerkHintService.getShopClueList(clerkHintDTO,page);
	}

	/**
	 * 线索详情
	 * @param clerkHintDTO
	 * @return
     */
	public ResultDO<ClerkHintDTO> getShopClueInfo(ClerkHintDTO clerkHintDTO) {
		ResultDO res=new ResultDO();
		if(clerkHintDTO.getId()==null){
			res.setErrCode(ErrorConstant.NULL_CLERK_HINT_ID.getCode());
			res.setErrMsg(ErrorConstant.NULL_CLERK_HINT_ID.getMsg());
			return res;
		}
		return clerkHintService.getShopClueInfo(clerkHintDTO);
	}

	/**
	 * 线索认领
	 * @param clerkHintDTO
     * @return
     */
	public ResultDO shopClaim(ClerkHintDTO clerkHintDTO) throws ParseException {
		ResultDO resp=new ResultDO();
		if(clerkHintDTO.getId()==null){
			resp.setErrCode(ErrorConstant.NULL_CLERK_HINT_ID.getCode());
			resp.setErrMsg(ErrorConstant.NULL_CLERK_HINT_ID.getMsg());
			return resp;
		}
		ResultDO<ClerkHintDTO> res=clerkHintService.getShopClueInfo(clerkHintDTO);
		if(res.getData()==null){
			resp.setErrCode(ErrorConstant.NO_EXSIT_CLERK_HINT.getCode());
			resp.setErrMsg(ErrorConstant.NO_EXSIT_CLERK_HINT.getMsg());
			return resp;
		}
		if(res.getData().getStatus()==1){
			resp.setErrCode(ErrorConstant.CLAIM_CLERK_HINT.getCode());
			resp.setErrMsg(ErrorConstant.CLAIM_CLERK_HINT.getMsg());
			return resp;
		}
		/*根据bizId获取日程*/
//		ResultDO<CustomerSchedule>schedule=	scheduleService.queryScheduleByBizId(res.getData().getId(),0);
//		CustomerSchedule customerSchedule=new CustomerSchedule();
//		/*组装日程表*/
//		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		clerkHintDTO.setOwnerId(HttpSessionUtils.getCurrentAppUserId());
//		customerSchedule.setAddress(res.getData().getShopAddress());
//		customerSchedule.setClerkId(clerkHintDTO.getOwnerId());
//		customerSchedule.setCustomerId(res.getData().getIssuerId());
//		customerSchedule.setType(0);
//		customerSchedule.setId(schedule.getData().getId());
//		customerSchedule.setBizId(res.getData().getId());
//		customerSchedule.setMeetTime(format1.parse(res.getData().getSubscribeTime()));
//		ResultDO resultDO=scheduleService.updatScheduleStatus(customerSchedule);
		ResultDO resClerk= clerkHintService.shopClaim(clerkHintDTO);
//		if(resClerk.isSuccess()&&resClerk.isSuccess()){
//			resp.setSuccess(true);
//		}
		return resClerk;
	}

	/**
	 * 修改线索实勘时间
	 * @param clerkHintDTO
	 * @return
	 * @throws ParseException
     */
	public ResultDO updateSubscribeTime(ClerkHintDTO clerkHintDTO) throws ParseException {
		ResultDO resultDO=new ResultDO();
		if(clerkHintDTO.getId()==null){
			resultDO.setErrCode(ErrorConstant.NULL_CLERK_HINT_ID.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_CLERK_HINT_ID.getMsg());
			return resultDO;
		}
		if(ValidateHelper.isEmptyString(clerkHintDTO.getSubscribeTime())){
			resultDO.setErrCode(ErrorConstant.NULL_SUBSCRIBE_TIME.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_SUBSCRIBE_TIME.getMsg());
			return resultDO;
		}
		String subscribeTime=clerkHintDTO.getSubscribeTime();
		if(!DateValidate.isValidDate(subscribeTime,"yyyy-MM-dd HH:mm")){
			resultDO.setErrCode(ErrorConstant.ERROR_DATE_FORMAT.getCode());
			resultDO.setErrMsg(ErrorConstant.ERROR_DATE_FORMAT.getMsg());
			return resultDO;
		}

		ResultDO<ClerkHintDTO> res=clerkHintService.getShopClueInfo(clerkHintDTO);
		if(res.getData()==null){
			resultDO.setErrCode(ErrorConstant.NO_EXSIT_CLERK_HINT.getCode());
			resultDO.setErrMsg(ErrorConstant.NO_EXSIT_CLERK_HINT.getMsg());
			return resultDO;
		}

		if(res.getData().getStatus()!=1){
			resultDO.setErrCode(ErrorConstant.ERROR_CLERK_HINT_STATUS.getCode());
			resultDO.setErrMsg(ErrorConstant.ERROR_CLERK_HINT_STATUS.getMsg());
			return resultDO;
		}
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(res.getData()!=null) {
		/*根据bizId获取日程*/
			if (res.getData().getIssuerType() == 0) {
				ResultDO<CustomerSchedule> schedule = scheduleService.queryScheduleByBizId(res.getData().getId(), 0);
				if (schedule.getData() == null) {
					resultDO.setErrMsg(ErrorConstant.NULL_SCHEDULE.getMsg());
					resultDO.setErrCode(ErrorConstant.NULL_SCHEDULE.getCode());
					return resultDO;
				}
				if (res.getData().getIssuerType() == 0) {
					sendSmsNotice(clerkHintDTO, res, schedule, 1);
				}

		/*修改日程表*/
				CustomerSchedule customerSchedule = new CustomerSchedule();
				customerSchedule.setId(schedule.getData().getId());
				customerSchedule.setMeetTime(format1.parse(subscribeTime));
				customerSchedule.setOldMeetTime(format1.parse(res.getData().getSubscribeTime()));
				try {
					scheduleService.updatScheduleStatus(customerSchedule);
					resultDO.setSuccess(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			try {
				clerkHintService.updateSubscribeTime(clerkHintDTO);
				resultDO.setSuccess(true);
			} catch (Exception e) {
				resultDO.setSuccess(false);
				e.printStackTrace();
			}
		}

		return resultDO;
	}

	/**
	 * 线索废弃
	 * @param clerkHintDTO
	 * @return
     */
	public ResultDO updateClueStatus(ClerkHintDTO clerkHintDTO) {
		ResultDO resultDO=new ResultDO();

		if(clerkHintDTO.getId()==null){
			resultDO.setErrCode(ErrorConstant.NULL_CLERK_HINT_ID.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_CLERK_HINT_ID.getMsg());
			return resultDO;
		}

//		if(res.getData()!=null){
		/*根据bizId获取日程*/
//			if(res.getData().getIssuerType()==0){//用户
//			ResultDO<CustomerSchedule>schedule=	scheduleService.queryScheduleByBizId(res.getData().getId(),0);
//			if(schedule.getData()==null){
//				resultDO.setErrMsg(ErrorConstant.NULL_SCHEDULE.getMsg());
//				resultDO.setErrCode(ErrorConstant.NULL_SCHEDULE.getCode());
//				return resultDO;
//			}
//			if(res.getData().getIssuerType()==0){//
//				sendSmsNotice(clerkHintDTO,res,schedule,2);
//			}

		/*修改日程表*/
//			CustomerSchedule customerSchedule=new CustomerSchedule();
//			customerSchedule.setId(schedule.getData().getId());
//			customerSchedule.setStatus(2);
//
//			try{
//				scheduleService.updatScheduleStatus(customerSchedule);
//				resultDO.setSuccess(true);
//			}catch(Exception e){
//				e.printStackTrace();
//				log.info("线索废弃失败,e:{}",e);
//			}
//			}
			try{
				ResultDO updateDO = clerkHintService.updateClueStatus(clerkHintDTO);
				return updateDO;
			}catch(Exception e){
				resultDO.setSuccess(false);
				resultDO.setErrCode(ErrorConstant.CLERK_HINT_CANCEL_FAIL.getCode());
				resultDO.setErrMsg(ErrorConstant.CLERK_HINT_CANCEL_FAIL.getMsg());
				e.printStackTrace();
				log.info("线索废弃失败,e:{}",e);
			}
//		}
		return resultDO;

	}

	@Async
	public void sendSmsNotice(ClerkHintDTO clerkHintDTO, ResultDO<ClerkHintDTO> res, ResultDO<CustomerSchedule> schedule,Integer tagId){
		/**短信**/
		ResultDO<Clerk> clerk=clerkService.getClerkInfoById(res.getData().getOwnerId());
		String clerkName=clerk.getData().getRealName();
		String userPhone=res.getData().getLinkmanPhone();
		ServiceNoticeReqDTO serviceNoticeDTO=new ServiceNoticeReqDTO();
		serviceNoticeDTO.setServiceType(ServiceNoticeType.SHOP_RENT_SEEKING.getValue());
		serviceNoticeDTO.setShopAddress(res.getData().getShopAddress());
		serviceNoticeDTO.setServiceTel(clerk.getData().getPhone());
		serviceNoticeDTO.setServiceType(ServiceNoticeType.SHOP_RENT_SEEKING.getValue());
		serviceNoticeDTO.setBussinessId(schedule.getData().getId());
		serviceNoticeDTO.setReceiveId(res.getData().getIssuerId());
		serviceNoticeDTO.setReceiveType(ReceiveType.CUSTOMER.getValue());
		serviceNoticeDTO.setServiceName(clerkName);
		ResultDO<Customer> customer=customerService.findCustomerInfoByCustomerId(res.getData().getIssuerId());
		String deviceId=customer.getData().getDeviceId();
		Integer osType=customer.getData().getOsType();
		log.info("serviceNoticeDTO:{}",serviceNoticeDTO.toString());
		if(tagId==1){
			serviceNoticeDTO.setOldTime(res.getData().getSubscribeTime());
			serviceNoticeDTO.setCurrentTime(clerkHintDTO.getSubscribeTime());
			try{
			/*通知*/
				noticePushService.pushScheduleChangeNotify(serviceNoticeDTO,userPhone,deviceId, osType);
			}catch(Exception e){
				log.info("e",e);
				e.printStackTrace();
			}
		}else{
			if(clerkHintDTO.getTagId()==0){
				serviceNoticeDTO.setReason("无该商铺");
			}else if(clerkHintDTO.getTagId()==1){
				serviceNoticeDTO.setReason("暂不出租");
			}else if(clerkHintDTO.getTagId()==2){
				serviceNoticeDTO.setReason("无联系人");
			}else{
				serviceNoticeDTO.setReason("其他");
			}
			serviceNoticeDTO.setCurrentTime(res.getData().getSubscribeTime());

			try{
			/*通知*/
				noticePushService.pushServiceRevokeNotify(serviceNoticeDTO,userPhone,deviceId, osType);
			}catch(Exception e){
				log.info("e",e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 交易员认领线索
	 * @param clerkHintComfirmDTO
	 * @return
	 */
	public ResultDO tradeClerkClaim(ClerkHintComfirmDTO clerkHintComfirmDTO) {
		ResultDO resp=new ResultDO();
		if(clerkHintComfirmDTO.getHintId()==null){
			resp.setErrCode(ErrorConstant.NULL_CLERK_HINT_ID.getCode());
			resp.setErrMsg(ErrorConstant.NULL_CLERK_HINT_ID.getMsg());
			return resp;
		}

		ResultDO resClerk= clerkHintService.tradeClerkClaim(clerkHintComfirmDTO);
		return resClerk;
	}

	/**
	 * 拓铺员确认线索有效
	 * @param clerkHintComfirmDTO
	 * @return
	 */
	public ResultDO confirmHint(ClerkHintComfirmDTO clerkHintComfirmDTO) {
		ResultDO resp=new ResultDO();
		if(clerkHintComfirmDTO.getHintId()==null){
			resp.setErrCode(ErrorConstant.NULL_CLERK_HINT_ID.getCode());
			resp.setErrMsg(ErrorConstant.NULL_CLERK_HINT_ID.getMsg());
			return resp;
		}
		ResultDO resultDO = clerkHintService.confirmHint(clerkHintComfirmDTO);
		return resultDO;
	}

	/**
	 * 线索位置信息修改
	 * @param clerkHintDTO
	 * @return
	 */
	public ResultDO updatePositionInfo(ClerkHintDTO clerkHintDTO) {
		ResultDO resultDO = null;
		if(ValidateHelper.isEmpty(clerkHintDTO)
				&& ValidateHelper.isEmpty(clerkHintDTO.getModifier())
				&& ValidateHelper.isEmpty(clerkHintDTO.getId())){
			resultDO = new ResultDO();
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
			return resultDO;
		}
		resultDO = clerkHintService.updatePositionInfo(clerkHintDTO);
		return resultDO;
	}

	/**
	 * 线索客户信息修改
	 * @param clerkHintDTO
	 * @return
	 */
	public ResultDO updateCustomerInfo(ClerkHintDTO clerkHintDTO) {
		ResultDO resultDO = null;
		if(ValidateHelper.isEmpty(clerkHintDTO)
				&& ValidateHelper.isEmpty(clerkHintDTO.getModifier())
				&& ValidateHelper.isEmpty(clerkHintDTO.getId())){
			resultDO = new ResultDO();
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
			return resultDO;
		}
		resultDO = clerkHintService.updateCustomerInfo(clerkHintDTO);
		return resultDO;
	}

	/**
	 * 线索建筑信息修改
	 * @param clerkHintDTO
	 * @return
	 */
	public ResultDO updateBuildingInfo(ClerkHintDTO clerkHintDTO) {
		ResultDO resultDO = null;
		if(ValidateHelper.isEmpty(clerkHintDTO)
				&& ValidateHelper.isEmpty(clerkHintDTO.getModifier())
				&& ValidateHelper.isEmpty(clerkHintDTO.getId())){
			resultDO = new ResultDO();
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
			return resultDO;
		}
		resultDO = clerkHintService.updateBuildingInfo(clerkHintDTO);
		return resultDO;
	}

	/**
	 * 线索经营状态修改
	 * @param clerkHintImgsDTO
	 * @return
	 */
	public ResultDO updateShopImgInfo(ClerkHintImgsDTO clerkHintImgsDTO) {
		ResultDO resultDO = null;
		if(ValidateHelper.isEmpty(clerkHintImgsDTO)
				&& ValidateHelper.isEmpty(clerkHintImgsDTO.getModifier())
				&& ValidateHelper.isEmpty(clerkHintImgsDTO.getId())){
			resultDO = new ResultDO();
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
			return resultDO;
		}

		String imgCheckErr = checkShopImg(clerkHintImgsDTO.getShopImgList());
		if(!ValidateHelper.isEmpty(imgCheckErr)){
			resultDO = new ResultDO();
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			resultDO.setErrMsg(imgCheckErr);
			return resultDO;
		}

		resultDO = clerkHintService.updateShopImgInfo(clerkHintImgsDTO);
		return resultDO;
	}

	/**
	 * check 商铺图片
	 * @param shopImgList
	 * @return
	 */
	private String checkShopImg(List<ShopImgDTO> shopImgList){
		if(shopImgList==null || shopImgList.size()==0){
			return "商铺图片不能为空！";
		}
		if(shopImgList.size()>10){
			return "商铺图片不能大于10张！";
		}
		List<String> tempList = new ArrayList<>();//临时变量 保存是否设置封面
		Iterator<ShopImgDTO> iterator = shopImgList.iterator();
		while (iterator.hasNext()){
			ShopImgDTO dto = iterator.next();
			String imgUrl = dto.getImgUrl();
			if(ValidateHelper.isEmptyString(imgUrl)){
				return "图片地址不能为空！";
			}
			Integer isCover = dto.getIsCover();//是否为封面(0-否 1-是)
			if(isCover==null){
				return "商铺图片是否为封面必填！";
			}
			tempList.add(String.valueOf(isCover));
		}
		if(tempList.remove("1")){
			if(tempList.contains("1")){
				return "商铺图片只能设置一张封面！";
			}
		}else {
			return "商铺图片是否为封面必填！";
		}
		return null;
	}

	/**
	 * 线索数量统计
	 * @param userId
	 * @return
	 */
	public ResultDO<ClerkHintStatisticsDTO> hintStatistics(Long userId) {
		ResultDO<ClerkHintStatisticsDTO> resultDO = clerkHintService.hintStatistics(userId);
		return resultDO;
	}

	/**
	 * 待确认/待核准的线索列表
	 * @param query
	 * @Author zhoumin
	 * @return
	 */
	public ResultDO <Page<ClerkHintList>> queryHintListByStatus(ClerkHintTypeQuery query) {
		ResultDO <Page<ClerkHintList>> resultDO = new ResultDO<>();
		if (null == query){
			resultDO.setErrMsg(ErrorConstant.NULL_REQ_DATA.getMsg());
			resultDO.setErrCode(ErrorConstant.NULL_REQ_DATA.getCode());
			return resultDO;
		}
		Long user = HttpSessionUtils.getCurrentAppUserId();
		query.setId(user);
		resultDO = clerkHintService.queryHintList(query);
		return resultDO;
	}

	/**
	 * 待确认、待核准详情
	 * @param reqDTO
	 * @ author zhoumin
	 * @return
	 */
	public ResultDO<ClerkHintDetailDTO> getHintDetailInfo(ClerkHintDetailReqDTO reqDTO){
		ResultDO<ClerkHintDetailDTO> resultDO = new ResultDO<>();
		if (null == reqDTO){
			resultDO.setErrMsg(ErrorConstant.NULL_REQ_DATA.getMsg());
			resultDO.setErrCode(ErrorConstant.NULL_REQ_DATA.getCode());
			return resultDO;
		}
		Long user = HttpSessionUtils.getCurrentAppUserId();
		reqDTO.setClerkId(user);
		resultDO = clerkHintService.getHintDetailInfo(reqDTO);
		return resultDO;
	}

	/**
	 * 签约，约看列表
	 * @param query
	 * @Author zhoumin
	 * @return
	 */
	public ResultDO <Page<ClerkScheduleListDTO>> clerkSign(ClerkSignTypeQuery query) {
		ResultDO <Page<ClerkScheduleListDTO>> resultDO = new ResultDO<>();
		if(ValidateHelper.isEmpty(query.getType())){
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
			return resultDO;
		}
		Long user = HttpSessionUtils.getCurrentAppUserId();
		query.setId(user);
		resultDO = clerkHintService.querySignOrViewList(query);
		return resultDO;
	}

	/**
	 * 签约，约看准详情
	 * @param query
	 * @ author zhoumin
	 * @return
	 */
	public ResultDO<ClerkSignOrViewDetailDTO> querySignOrViewDetail(SignOrViewQuery query){
		ResultDO<ClerkSignOrViewDetailDTO> resultDO = new ResultDO<>();
		if(ValidateHelper.isEmpty(query.getId()) || ValidateHelper.isEmpty(query.getType())){
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
			return resultDO;
		}
		resultDO = clerkHintService.querySignOrViewDetail(query);
		return resultDO;
	}

	/**
	 * @description 添加备注
	 * @package com.dongtong.app.ao
	 * @author liaozm
	 * @date 2017/8/15 14:05
	 * @params
	 * @return
	 */
	public ResultDO<Boolean> addRemark(ClerkRemarkDTO clerkRemarkDTO){
		ResultDO<Boolean> resultDO = new ResultDO<>();
		Long userId = HttpSessionUtils.getCurrentAppUserId();
		clerkRemarkDTO.setClerkId(userId);
		if(ValidateHelper.isEmpty(clerkRemarkDTO.getId()) || ValidateHelper.isEmpty(clerkRemarkDTO.getType())
				|| ValidateHelper.isEmpty(clerkRemarkDTO.getContent())){
			resultDO.setSuccess(false);
			resultDO.setErrCode(ErrorConstant.NULL_PARAMETER.getCode());
			resultDO.setErrMsg(ErrorConstant.NULL_PARAMETER.getMsg());
			return resultDO;
		}
		resultDO = clerkRemarkService.addRemark(clerkRemarkDTO);
		return resultDO;
	}
}
