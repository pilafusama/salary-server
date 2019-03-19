package com.tenpay.wxwork.salary.dto.h5;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.model.GridInfo;

public class HomepageEntriesResponse extends FrontEndResponse {
	
	@JsonProperty("grid_topic")
	private String grid_topic;
	
	@JsonProperty("gridList")
	private List<GridInfo> gridList;
	
	public HomepageEntriesResponse(String grid_topic, List<GridInfo> gridList) {
		super();
		this.grid_topic = grid_topic;
		this.gridList = gridList;
	}

	public String getGrid_topic() {
		return grid_topic;
	}

	public void setGrid_topic(String grid_topic) {
		this.grid_topic = grid_topic;
	}

	public List<GridInfo> getGridList() {
		return gridList;
	}

	public void setGridList(List<GridInfo> gridList) {
		this.gridList = gridList;
	}
	
	
}
