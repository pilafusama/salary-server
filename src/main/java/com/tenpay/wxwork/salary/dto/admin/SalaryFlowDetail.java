package com.tenpay.wxwork.salary.dto.admin;

public class SalaryFlowDetail {
	private String id;
	private String applyName;
	private String applyTime;
	private String receiveName;
	private String receiveAccount;
	private long totalFee;
	private int crossFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveAccount() {
		return receiveAccount;
	}

	public void setReceiveAccount(String receiveAccount) {
		this.receiveAccount = receiveAccount;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public int getCrossFlag() {
		return crossFlag;
	}

	public void setCrossFlag(int crossFlag) {
		this.crossFlag = crossFlag;
	}

	@Override
	public String toString() {
		return "SalaryFlowDetail{" +
				"id='" + id + '\'' +
				", applyName='" + applyName + '\'' +
				", applyTime='" + applyTime + '\'' +
				", receiveName='" + receiveName + '\'' +
				", receiveAccount='" + receiveAccount + '\'' +
				", totalFee=" + totalFee +
				", crossFlag=" + crossFlag +
				'}';
	}
}
