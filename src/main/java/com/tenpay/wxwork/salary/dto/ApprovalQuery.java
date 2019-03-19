package com.tenpay.wxwork.salary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApprovalQuery {

		@JsonProperty("starttime")
		private long starttime;
		
		@JsonProperty("endtime")
		private long endtime;
		
		@JsonProperty("next_spnum")
		private String next_spnum;
		
		@JsonProperty("access_token")
		private String access_token;

		@JsonIgnore
		public long getStarttime() {
			return starttime;
		}
		
		@JsonIgnore
		public void setStarttime(long starttime) {
			this.starttime = starttime;
		}
		
		@JsonIgnore
		public long getEndtime() {
			return endtime;
		}
		
		@JsonIgnore
		public void setEndtime(long endtime) {
			this.endtime = endtime;
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
		public String getAccess_token() {
			return access_token;
		}

		@JsonIgnore
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}		
}
