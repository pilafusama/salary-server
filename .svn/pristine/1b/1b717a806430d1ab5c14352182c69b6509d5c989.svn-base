package com.tenpay.wxwork.salary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApprovalResponseData {	
		@JsonProperty("spname")
		private String spname; //审批名称(请假，报销，自定义审批名称)
		
		@JsonProperty("apply_name")
		private String apply_name; //申请人姓名

		@JsonProperty("apply_org")
		private String apply_org; //申请人部门

		@JsonProperty("approval_name")
		private String[] approval_name; //审批人姓名

		@JsonProperty("notify_name")
		private String[] notify_name; //抄送人姓名

		@JsonProperty("sp_status")
		private int sp_status; //审批状态：1审批中；2 已通过；3已驳回；4已取消；6通过后撤销；10已支付
		
		@JsonProperty("sp_num")
		private String sp_num; //审批单号
		
		@JsonProperty("apply_time")
		private String apply_time; //审批单提交时间
		
		@JsonProperty("apply_user_id")
		private String apply_user_id; //审批单提交者的userid
		
		@JsonProperty("mediaids")
		private String[] mediaids; //审批的附件media_id，可使用media/get获取附件
		
		@JsonProperty("comm")
		private ApprovalResponseDataComm comm; //模板通用数据

		@JsonIgnore
		public String getSpname() {
			return spname;
		}
		
		@JsonIgnore
		public void setSpname(String spname) {
			this.spname = spname;
		}
		
		@JsonIgnore
		public String getApply_name() {
			return apply_name;
		}
		
		@JsonIgnore
		public void setApply_name(String apply_name) {
			this.apply_name = apply_name;
		}
		
		@JsonIgnore
		public String getApply_org() {
			return apply_org;
		}

		@JsonIgnore
		public void setApply_org(String apply_org) {
			this.apply_org = apply_org;
		}

		@JsonIgnore
		public String[] getApproval_name() {
			return approval_name;
		}

		@JsonIgnore
		public void setApproval_name(String[] approval_name) {
			this.approval_name = approval_name;
		}

		@JsonIgnore
		public String[] getNotify_name() {
			return notify_name;
		}

		@JsonIgnore
		public void setNotify_name(String[] notify_name) {
			this.notify_name = notify_name;
		}

		@JsonIgnore
		public int getSp_status() {
			return sp_status;
		}

		@JsonIgnore
		public void setSp_status(int sp_status) {
			this.sp_status = sp_status;
		}

		@JsonIgnore
		public String getSp_num() {
			return sp_num;
		}

		@JsonIgnore
		public void setSp_num(String sp_num) {
			this.sp_num = sp_num;
		}

		@JsonIgnore
		public String getApply_time() {
			return apply_time;
		}

		@JsonIgnore
		public void setApply_time(String apply_time) {
			this.apply_time = apply_time;
		}

		@JsonIgnore
		public String getApply_user_id() {
			return apply_user_id;
		}

		@JsonIgnore
		public void setApply_user_id(String apply_user_id) {
			this.apply_user_id = apply_user_id;
		}

		@JsonIgnore
		public String[] getMediaids() {
			return mediaids;
		}

		@JsonIgnore
		public void setMediaids(String[] mediaids) {
			this.mediaids = mediaids;
		}

		@JsonIgnore
		public ApprovalResponseDataComm getComm() {
			return comm;
		}

		@JsonIgnore
		public void setComm(ApprovalResponseDataComm comm) {
			this.comm = comm;
		}				
}