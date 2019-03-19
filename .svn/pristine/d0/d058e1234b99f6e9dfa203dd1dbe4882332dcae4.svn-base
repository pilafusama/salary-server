package com.tenpay.wxwork.salary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonArray;

public class ApprovalResponse {

		@JsonProperty("errcode")
		private int errcode; //错误码编号
		
		@JsonProperty("errmsg")
		private String errmsg; //错误码信息
		
		@JsonProperty("count")
		private int count; //拉取的审批单个数，最大值为100，当total参数大于100时，可运用next_spnum参数进行多次拉取
		
		@JsonProperty("total")
		private int total;  //时间段内的总审批单个数
		
		@JsonProperty("next_spnum")
		private String next_spnum; //拉取列表的最后一个审批单号
		
		@JsonProperty("data")
		private JsonArray dataArr;		

		
		@JsonIgnore
		public int getErrcode() {
			return errcode;
		}

		@JsonIgnore
		public void setErrcode(int errcode) {
			this.errcode = errcode;
		}

		@JsonIgnore
		public String getErrmsg() {
			return errmsg;
		}

		@JsonIgnore
		public void setErrmsg(String errmsg) {
			this.errmsg = errmsg;
		}

		@JsonIgnore
		public int getCount() {
			return count;
		}
		
		@JsonIgnore
		public void setCount(int count) {
			this.count = count;
		}

		@JsonIgnore
		public int getTotal() {
			return total;
		}

		@JsonIgnore
		public void setTotal(int total) {
			this.total = total;
		}

		@JsonIgnore
		public String getNext_spnum() {
			return next_spnum;
		}
		
		@JsonIgnore
		public void setNext_spnum(String next_spnum) {
			this.next_spnum = next_spnum;
		}

		@JsonIgnore
		public JsonArray getDataArr() {
			return dataArr;
		}

		@JsonIgnore
		public void setDataArr(JsonArray dataArr) {
			this.dataArr = dataArr;
		}						
}