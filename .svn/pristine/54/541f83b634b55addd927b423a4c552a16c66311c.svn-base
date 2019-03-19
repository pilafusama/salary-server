package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.model.GetUserInfo;
import com.tenpay.wxwork.salary.model.OpenAccountInfo;

public class GetUserInfoResponse extends FrontEndResponse
{
	@JsonProperty("is_gesture_password_set")
	private boolean gesturePasswordSet;// 手势密码是否已设置(R)
	@JsonProperty("is_account_open")
	private boolean accountOpen;// 是否已开户(R)
	@JsonProperty("final_finished_step")
	private String finalFinishedStep;// 最后完成的开户步骤(R)
	@JsonProperty("open_account_info")
	private OpenAccountInfo openAccountInfo;// 开户信息(R) 
	@JsonProperty("is_open_account_allowed")
	private boolean openAccountAllowed;// 当前是否允许开户(R)
	@JsonProperty("open_account_start_time")
	private String openAccountStartTime;// 开户起始时间(R)
	@JsonProperty("open_account_end_time")
	private String openAccountEndTime;// 开户截止时间(R)
	@JsonProperty("bank_name")
	private String bank_name;// 一类户银行名称
	@JsonProperty("bank_name_two")
	private String bank_name_two;// 一类户银行名称

	public GetUserInfoResponse(String retCode, String errMsg)
	{
		super(retCode, errMsg);
	}

	public GetUserInfoResponse(GetUserInfo userInfo,String bank_name,String bank_name_two)
	{
		this.gesturePasswordSet = userInfo.isGesturePasswordSet();
		this.accountOpen = userInfo.isAccountOpen();
		this.finalFinishedStep = userInfo.getFinalFinishedStep();
		this.openAccountInfo = userInfo.getOpenAccountInfo();
		this.openAccountAllowed = userInfo.isOpenAccountAllowed();
		this.openAccountStartTime = userInfo.getOpenAccountStartTime();
		this.openAccountEndTime = userInfo.getOpenAccountEndTime();
		this.bank_name = bank_name;
		this.bank_name_two = bank_name_two;
	}

	public boolean getGesturePasswordSet()
	{
		return gesturePasswordSet;
	}

	public void setGesturePasswordSet(boolean gesturePasswordSet)
	{
		this.gesturePasswordSet = gesturePasswordSet;
	}

	public boolean getAccountOpen()
	{
		return accountOpen;
	}

	public void setAccountOpen(boolean accountOpen)
	{
		this.accountOpen = accountOpen;
	}

	public String getFinalFinishedStep()
	{
		return finalFinishedStep;
	}

	public void setFinalFinishedStep(String finalFinishedStep)
	{
		this.finalFinishedStep = finalFinishedStep;
	}

	public OpenAccountInfo getOpenAccountInfo()
	{
		return openAccountInfo;
	}

	public void setOpenAccountInfo(OpenAccountInfo openAccountInfo)
	{
		this.openAccountInfo = openAccountInfo;
	}

	public boolean getOpenAccountAllowed()
	{
		return openAccountAllowed;
	}

	public void setOpenAccountAllowed(boolean openAccountAllowed)
	{
		this.openAccountAllowed = openAccountAllowed;
	}

	public String getOpenAccountStartTime()
	{
		return openAccountStartTime;
	}

	public void setOpenAccountStartTime(String openAccountStartTime)
	{
		this.openAccountStartTime = openAccountStartTime;
	}

	public String getOpenAccountEndTime()
	{
		return openAccountEndTime;
	}

	public void setOpenAccountEndTime(String openAccountEndTime)
	{
		this.openAccountEndTime = openAccountEndTime;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_name_two() {
		return bank_name_two;
	}

	public void setBank_name_two(String bank_name_two) {
		this.bank_name_two = bank_name_two;
	}
}
