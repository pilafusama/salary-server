package com.tenpay.wxwork.salary.service.h5;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tenpay.wxwork.salary.dao.HomepageEntriesDao;
import com.tenpay.wxwork.salary.model.GridInfo;
import com.tenpay.wxwork.salary.model.SalaryBankConfInfo;
import com.tenpay.wxwork.salary.service.admin.GridConfigService;

@Service
public class HomepageEntriesService {
	
	@Autowired
	private HomepageEntriesDao homepageEntriesDao;
	
	@Autowired
	private GridConfigService gridConfigService;

	public List<GridInfo> getGridsInfo(String bankSname) {
		List<GridInfo> list = gridConfigService.listGrids(bankSname);		
		if (list == null) {
			return null;
		} else if (list.size() < 10) {
			return list;
		} else {
			return list.subList(0, 9);
		}
		
//		List<GridInfo> gridInfoList = new ArrayList<GridInfo>();
//		List<SalaryBankConfInfo> bankConfList = homepageEntriesDao.getGridsInfo(bankSname);
//		if (bankConfList != null) {
//			for (SalaryBankConfInfo bankConfInfo : bankConfList) {
//				String value = bankConfInfo.getValue();
//				if (StringUtils.isNotEmpty(value)) {
//					JSONObject jsonObject = JSON.parseObject(value);
//					
//					GridInfo gridInfo = new GridInfo();
//					gridInfo.setGrid_num(jsonObject.getIntValue("grid_num"));
//					gridInfo.setGrid_title(jsonObject.getString("grid_title"));
//					gridInfo.setGrid_entry_type(jsonObject.getString("grid_entry_type"));
//					gridInfo.setGrid_entry(jsonObject.getString("grid_entry"));
//					gridInfo.setGrid_icon_src(jsonObject.getString("grid_icon_src"));
//					
//					gridInfoList.add(gridInfo);
//				}
//			}
//		}
		
//		return gridInfoList;
	}

	public String getGridTopic(String bankSname) {
		SalaryBankConfInfo info = homepageEntriesDao.getGridTopic(bankSname);
		return info.getValue();
	}

}
