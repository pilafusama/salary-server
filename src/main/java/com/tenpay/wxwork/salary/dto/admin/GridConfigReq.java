package com.tenpay.wxwork.salary.dto.admin;

public class GridConfigReq {

	private int grid_id; // 宫格id
	private int grid_num; // 宫格序号（在9宫格中的位置）
	private String grid_title; // 宫格名称
	private String grid_entry; // 宫格入口
	private String grid_entry_type; // 宫格入口类型（暂时只有h5）
	private String grid_icon; // 宫格图片src值
	private String corp_type; // 企业类型（拼接）
	private String user_tag; // 用户标签（拼接）

	public int getGrid_id() {
		return grid_id;
	}

	public void setGrid_id(int grid_id) {
		this.grid_id = grid_id;
	}

	public int getGrid_num() {
		return grid_num;
	}

	public void setGrid_num(int grid_num) {
		this.grid_num = grid_num;
	}

	public String getGrid_title() {
		return grid_title;
	}

	public void setGrid_title(String grid_title) {
		this.grid_title = grid_title;
	}

	public String getGrid_entry() {
		return grid_entry;
	}

	public void setGrid_entry(String grid_entry) {
		this.grid_entry = grid_entry;
	}

	public String getGrid_entry_type() {
		return grid_entry_type;
	}

	public void setGrid_entry_type(String grid_entry_type) {
		this.grid_entry_type = grid_entry_type;
	}

	public String getGrid_icon() {
		return grid_icon;
	}

	public void setGrid_icon(String grid_icon) {
		this.grid_icon = grid_icon;
	}

	public String getCorp_type() {
		return corp_type;
	}

	public void setCorp_type(String corp_type) {
		this.corp_type = corp_type;
	}

	public String getUser_tag() {
		return user_tag;
	}

	public void setUser_tag(String user_tag) {
		this.user_tag = user_tag;
	}

}
