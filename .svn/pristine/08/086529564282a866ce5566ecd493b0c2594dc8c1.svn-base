package com.tenpay.wxwork.salary.model;

public class GetUserInfo
{
	private boolean isGesturePasswordSet;//手势密码是否已设置(R)
	private boolean isAccountOpen;//是否已开户(R)
	private String finalFinishedStep;//最后完成的开户步骤(R)
	private OpenAccountInfo openAccountInfo;//开户信息(R)
	private boolean isOpenAccountAllowed;//当前是否允许开户(R)
	private String openAccountStartTime;//开户起始时间(R)
	private String openAccountEndTime;//开户截止时间(R)
	public boolean isGesturePasswordSet()
	{
		return isGesturePasswordSet;
	}
	public void setGesturePasswordSet(boolean isGesturePasswordSet)
	{
		this.isGesturePasswordSet = isGesturePasswordSet;
	}
	public boolean isAccountOpen()
	{
		return isAccountOpen;
	}
	public void setAccountOpen(boolean isAccountOpen)
	{
		this.isAccountOpen = isAccountOpen;
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
	public void setOpenAccountInfo(SalaryAccount salaryAccount,String accountSecret)
	{
		if(salaryAccount != null){
			this.openAccountInfo = new OpenAccountInfo(salaryAccount,accountSecret);
		}
	}
	public boolean isOpenAccountAllowed()
	{
		return isOpenAccountAllowed;
	}
	public void setOpenAccountAllowed(boolean isOpenAccountAllowed)
	{
		this.isOpenAccountAllowed = isOpenAccountAllowed;
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
	public void setOpenAccountEndTime(String openAccountEndTime) {
		this.openAccountEndTime = openAccountEndTime;
	}
}
