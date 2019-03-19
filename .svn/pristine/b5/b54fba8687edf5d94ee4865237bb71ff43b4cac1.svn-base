package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.model.GetUserInfo;
import com.tenpay.wxwork.salary.model.OpenAccountInfo;
import com.tenpay.wxwork.salary.model.OpenAccountModeInfo;

public class GetOpenAccountModeResponse extends FrontEndResponse
{
	@JsonProperty("open_account_mode")
	private String openAccountMode;// 绑卡开户模式
	@JsonProperty("open_account_entry")
	private String openAccountEntry;// 绑卡开户入口
	@JsonProperty("mode_info")
	private OpenAccountModeInfo modeInfo;// 模式信息

	public GetOpenAccountModeResponse(String retCode, String errMsg)
	{
		super(retCode, errMsg);
	}

	public GetOpenAccountModeResponse(String open_account_mode, String open_account_entry, OpenAccountModeInfo mode_info) {
		this.openAccountMode = open_account_mode;
		this.openAccountEntry = open_account_entry;
		this.modeInfo = mode_info;
	}


	public String getOpenAccountMode() {
		return openAccountMode;
	}

	public void setOpenAccountMode(String openAccountMode) {
		this.openAccountMode = openAccountMode;
	}

	public String getOpenAccountEntry() {
		return openAccountEntry;
	}

	public void setOpenAccountEntry(String openAccountEntry) {
		this.openAccountEntry = openAccountEntry;
	}

	public OpenAccountModeInfo getModeInfo() {
		return modeInfo;
	}

	public void setModeInfo(OpenAccountModeInfo modeInfo) {
		this.modeInfo = modeInfo;
	}
}
