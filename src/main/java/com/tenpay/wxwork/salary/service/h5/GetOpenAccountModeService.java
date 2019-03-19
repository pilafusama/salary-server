package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.common.utils.BankConfKey;
import com.tenpay.wxwork.salary.common.utils.CorpConfKey;
import com.tenpay.wxwork.salary.dao.SalaryBankConfDAO;
import com.tenpay.wxwork.salary.dto.h5.GetOpenAccountModeResponse;
import com.tenpay.wxwork.salary.model.OpenAccountModeInfo;
import com.tenpay.wxwork.salary.model.SalaryBankConfInfo;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class GetOpenAccountModeService
{
	@Autowired
	private SessionService sessionService;

	@Autowired
	private SalaryBankConfDAO dao;

	public GetOpenAccountModeResponse getOpenAccountMode(SessionInfo sessionInfo){
		//查询企业合作银行的配置信息
		List<SalaryBankConfInfo> bankConfInfos = dao.querySalaryBankConf(sessionInfo.getBankSname(),BankConfKey.AUDITED_STATE);
		String openAccountMode = ""; // 绑卡开户模式
		String openAccountEntry = ""; // 绑卡开户入口
		OpenAccountModeInfo modeInfo = null; // 模式信息，开户模式为BIND_AND_OPEN_MODE 绑卡并开户时有效
		Boolean isOpenAccountAllowed = false; // 当前是否允许开户
		String openAccountStartTime = ""; // 开户起始时间
		String openAccountEndTime = ""; // 开户截止时间
		Boolean isLivenessDetectionNeeded = false; // 是否需要人脸识别
		for(int i = 0; i < bankConfInfos.size(); i ++){
			SalaryBankConfInfo info = bankConfInfos.get(i);
			if(info.getKey().equals(BankConfKey.OPEN_ACCOUNT_MODE)){
				openAccountMode = info.getValue();
				//将开户模式存入session方便后续判断
				sessionInfo.setOpenAccountMode(openAccountMode);
				sessionService.setSession(sessionInfo);
			}else if(info.getKey().equals(BankConfKey.OPEN_ACCOUNT_ENTRY)){
				openAccountEntry = info.getValue();
			}else if(info.getKey().equals(BankConfKey.OPEN_ACCOUNT_START_TIME)){
				openAccountStartTime = info.getValue();
			}else if(info.getKey().equals(BankConfKey.OPEN_ACCOUNT_END_TIME)){
				openAccountEndTime = info.getValue();
			}else if(info.getKey().equals(BankConfKey.IS_LIVENESS_DETECTION_NEEDED)){
				isLivenessDetectionNeeded = Boolean.parseBoolean(info.getValue());
			}
		}

		if(openAccountMode.equals(BankConfKey.BIND_AND_OPEN_MODE)){
			// 判断是否允许开户
			isOpenAccountAllowed = isOpenTime(openAccountStartTime, openAccountEndTime);
			modeInfo = new OpenAccountModeInfo(isOpenAccountAllowed, openAccountStartTime, openAccountEndTime,
					isLivenessDetectionNeeded);

		}
		return new GetOpenAccountModeResponse(openAccountMode, openAccountEntry, modeInfo);
	}

	public boolean isOpenTime(String startTime, String endTime)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			long now = sdf.parse(sdf.format(new Date())).getTime();
			long start = sdf.parse(startTime).getTime();
			long end = sdf.parse(endTime).getTime();
			return start <= now && now <= end;
		} catch (ParseException e)
		{
			return false;
		}
	}


}
