package com.tenpay.wxwork.salary.service.h5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.common.utils.BankConfKey;
import com.tenpay.wxwork.salary.common.utils.CorpConfKey;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.dao.AppInfoDAO;
import com.tenpay.wxwork.salary.dao.SalaryBankConfDAO;
import com.tenpay.wxwork.salary.dao.SalaryCorpConfDAO;
import com.tenpay.wxwork.salary.model.*;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.QueryEBindReqParam;
import com.tenpay.wxwork.salary.model.SalaryAccount.State;
import com.tenpay.wxwork.salary.presvr.sender.bean.QueryEBindRes;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.SessionService;

/**
 * @author : CZK
 * @description :
 * @date : 2018/8/22
 */

@Service
public class GetUserInfoService
{
	@Autowired
	private SessionService sessionService;

	@Autowired
	private BankProxyRequestService bankProxyRequestService;

	@Autowired
	private SalaryAccountDAO dao;

	@Autowired
	private AppInfoDAO appInfoDAO;

	@Autowired
	private KeyCacheService keyCacheService;

	@Autowired
	private SalaryCorpConfDAO salaryCorpConfDAO;

	@Autowired
	private SalaryBankConfDAO salaryBankConfDAO;

	public GetUserInfo getUserInfo(String ssid)
	{
		GetUserInfo getUserInfo = null;
		SessionInfo session = sessionService.getSession(ssid);
		if (session == null)
		{
			throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
		}

		//查询企业配置信息
		SalaryCorpConfInfo salaryCorpConfInfo = salaryCorpConfDAO.querySalaryCorpConfInfo(session.getCorpId(), CorpConfKey.CORP_TO_BANK,SalaryCorpConfInfo.State.AUDITED.name());
		if(salaryCorpConfInfo == null){
			throw new BizException(BizError.CORP_CONF_ERROR.errorCode(),BizError.CORP_CONF_ERROR.errorMsg());
		}
		//查询银行配置信息
		SalaryBankConfInfo salaryBankConfInfo = salaryBankConfDAO.querySalaryCorpConfInfo(salaryCorpConfInfo.getValue(), BankConfKey.BANK_TYPE, SalaryBankConfInfo.State.AUDITED.name());
		if(salaryBankConfInfo == null){
			throw new BizException(BizError.BANK_CONF_ERROR.errorCode(),BizError.BANK_CONF_ERROR.errorMsg());
		}
		//存入session
		session.setBankSname(salaryCorpConfInfo.getValue());
		session.setBankType(salaryBankConfInfo.getValue());
		sessionService.setSession(session);


		// 通过企业id和用户id查询用户信息
		SalaryAccount salaryAccount = dao.queryByUserId(session.getCorpId(), session.getUserId());
		if (salaryAccount != null)
		{
			State fstate = State.fromInt(salaryAccount.getState());
			if (State.OPEN_REQ_SENT.equals(fstate) || State.REUSE_REQ_SENT.equals(fstate))
			{
				// 如果未开户成功且Fstate是已请求银行,发送请求到前置,验证用户是否开户成功,若验证失败,重设为11,客户重新开户
				QueryEBindReqParam quertERequest = new QueryEBindReqParam();
				//获取银行标识
				String bankType = session.getBankType();
				//quertERequest.setBankType(salaryAccount.getSalary_card_bank_type());//银行标识
				quertERequest.setBankType(bankType);
				quertERequest.setFunc_code("1");//功能号
				quertERequest.setCrdt_No(AesUtil.decrypt(salaryAccount.getCre_id(),keyCacheService.getAccountSecret()));//证件号

				QueryEBindRes queryEBindRes = bankProxyRequestService.queryEBind(quertERequest);
				if(queryEBindRes != null && queryEBindRes.getEbnk_Sign_Accno() !=null && !queryEBindRes.getEbnk_Sign_Accno().equals("")){
//					dao.updateFstate(State.ACCOUNT_OPENED.toInt(), session.getCorpId(), session.getUserId(), salaryAccount.getState());
					String dtbs_tms = queryEBindRes.getSign_Dt().substring(0, 4)+"-"+queryEBindRes.getSign_Dt().substring(4, 6)+"-"+queryEBindRes.getSign_Dt().substring(6, 8);
                    
					// 通过卡bin查询开户行名称
					FinGateClient finGateClient = new FinGateClient();
                    String accountNo = queryEBindRes.getRltv_AccNo().replace(" ", "");
					CardBin cardBin = finGateClient.queryCardBin(accountNo);
					String bankName = cardBin.getBankSname();
					NationalBankNumber nationalBankNumber = new NationalBankNumber(bankName);
					String bankChName = nationalBankNumber.queryChName(bankName);
					session.setBankChNameOne(bankChName);
					dao.updateOpenSalaryAccountRes(session.getUserId(),
							session.getCorpId(),
							AesUtil.encrypt(queryEBindRes.getMblPh_No(),keyCacheService.getAccountSecret()),
							SalaryAccount.State.ACCOUNT_OPENED.toInt(),
							SalaryAccount.OpeningType.REUSE.name(),
							AesUtil.encrypt(queryEBindRes.getEbnk_Sign_Accno(),keyCacheService.getAccountSecret()),
							dtbs_tms,
							AesUtil.encrypt(queryEBindRes.getCst_Nm(),keyCacheService.getAccountSecret()),
							AesUtil.encrypt(accountNo,keyCacheService.getAccountSecret()),
							queryEBindRes.getDpBkBkCD(),
							queryEBindRes.getRsrv_TpCd().equals("0") ? SalaryAccount.CardBanksRelation.SAME_BANK.toString() : SalaryAccount.CardBanksRelation.DIFF_BANK.toString(),
							bankName
					);
				}else{
					dao.updateFstate(State.INIT.toInt(), session.getCorpId(), session.getUserId(), salaryAccount.getState());
				}
				salaryAccount = dao.queryByUserId(session.getCorpId(), session.getUserId());
			}else if (State.ACCOUNT_OPENED.equals(fstate)) {
				String chName = "";
				String chName2 = "";
				FinGateClient finGateClient = new FinGateClient();
				//获取一类户银行名称
				if(session.getBankChNameOne() == null || "".equals(session.getBankChNameOne())){
					String bindCardNum = AesUtil.decrypt(salaryAccount.getBind_card_number(),keyCacheService.getAccountSecret());//一类户卡号
					if (bindCardNum != null && !bindCardNum.equals("")){
						CardBin cardBin = finGateClient.queryCardBin(bindCardNum);
						String bankName = cardBin.getBankSname();
						NationalBankNumber nb = new NationalBankNumber(bankName);
						chName = nb.queryChName(bankName);
						session.setBankChNameOne(chName);
					}
				}

				//获取二类户银行名称
				if(session.getBankChNameTwo() == null || "".equals(session.getBankChNameTwo())){
					String salaryCardNumber = AesUtil.decrypt(salaryAccount.getSalary_card_number(),keyCacheService.getAccountSecret());//二类户卡号
					if (salaryCardNumber != null && !salaryCardNumber.equals("")){
						CardBin cardBin2 = finGateClient.queryCardBin(salaryCardNumber);
						String bankName2 = cardBin2.getBankSname();
						NationalBankNumber nb2 = new NationalBankNumber(bankName2);
						chName2 = nb2.queryChName(bankName2);
						session.setBankChNameTwo(chName2);
					}
				}
				sessionService.setSession(session);
			}else {
				//未开户成功初始化状态
				dao.updateFstate(State.INIT.toInt(), session.getCorpId(), session.getUserId(), salaryAccount.getState());
			}
			getUserInfo = GetUserInfoBulid(salaryAccount);
		}else {
			/*AppInfo appInfo = appInfoDAO.queryByMchAndTypeAndBusiness(Constant.CCB_MCH_ID, // TODO 先写死，以后再提供企业设置，同时需要根据 t_corp_app_relation 表判断是否有权限。
					AppInfo.TYPE_SALARY,
					AppInfo.BUSINESS_STYLE_OPEN_ACCOUNT);
			if (null == appInfo) {
				throw new BizException(BizError.NO_APP_INFO);
			}

			dao.insertNewAccount(session.getCorpId(), session.getUserId(),
					SalaryAccount.State.INIT.toInt(),
					appInfo.getBankType(), appInfo.getBankSname());*/
			dao.insertNewAccount(session.getCorpId(), session.getUserId(),
					SalaryAccount.State.INIT.toInt(),
					Integer.parseInt(session.getBankType()), session.getBankSname());
			getUserInfo = new GetUserInfo();
			getUserInfo.setAccountOpen(false);
			getUserInfo.setGesturePasswordSet(false);

			// 当前是否允许开户
			getUserInfo.setOpenAccountAllowed(belongCalender());
			// 开户起始时间
			getUserInfo.setOpenAccountStartTime(Constant.ALLOWED_OPEN_ACCOUNT_START_TIME);
			// 开户截止时间
			getUserInfo.setOpenAccountEndTime(Constant.ALLOWED_OPEN_ACCOUNT_END_TIME);
		}
		return getUserInfo;
	}

	/*
	 * 根据数据库查询的数据,装配数据成前端要求的数据格式
	 */
	public GetUserInfo GetUserInfoBulid(SalaryAccount salaryAccount)
	{
		GetUserInfo getUserInfo = new GetUserInfo();
		State fstate = State.fromInt(salaryAccount.getState());
		// 手势密码是否已设置

		if (salaryAccount.getGesture_password() != null && !"".equals(salaryAccount.getGesture_password()))
		{
			getUserInfo.setGesturePasswordSet(true);
		} else
		{
			getUserInfo.setGesturePasswordSet(false);
		}
		// 是否已开户
		if (State.ACCOUNT_OPENED.equals(fstate))
		{
			getUserInfo.setAccountOpen(true);
		} else
		{
			getUserInfo.setAccountOpen(false);
		}
		// 最后完成的开户步骤(R)
		if(fstate != null){
			getUserInfo.setFinalFinishedStep(fstate.name());
		}

		// 开户信息(R)
		getUserInfo.setOpenAccountInfo(salaryAccount,keyCacheService.getAccountSecret());
		// 当前是否允许开户,处理规则：在默认的工作时间段内允许开户
		getUserInfo.setOpenAccountAllowed(belongCalender());
		// 开户起始时间
		getUserInfo.setOpenAccountStartTime(Constant.ALLOWED_OPEN_ACCOUNT_START_TIME);
		// 开户截止时间
		getUserInfo.setOpenAccountEndTime(Constant.ALLOWED_OPEN_ACCOUNT_END_TIME);
		return getUserInfo;
	}
	
	/*
	 * 当前是否允许开户
	 */
	public boolean belongCalender()
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			long now = sdf.parse(sdf.format(new Date())).getTime();
			long start = sdf.parse(Constant.ALLOWED_OPEN_ACCOUNT_START_TIME).getTime();
			long end = sdf.parse(Constant.ALLOWED_OPEN_ACCOUNT_END_TIME).getTime();
			return start <= now && now <= end;
		} catch (ParseException e)
		{
			return false;
		}
	}
}
