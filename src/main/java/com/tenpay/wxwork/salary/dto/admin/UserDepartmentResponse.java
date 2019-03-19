package com.tenpay.wxwork.salary.dto.admin;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class UserDepartmentResponse extends FrontEndResponse {
	private String name;
	private int departmentId;
	private String departmentName;

	public UserDepartmentResponse(String name, int departmentId, String departmentName) {
		super();
		this.name = name;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
