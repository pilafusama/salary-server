package com.tenpay.wxwork.salary.service.admin;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenpay.wxwork.salary.dao.GridConfigDao;
import com.tenpay.wxwork.salary.dto.admin.GridConfigReq;
import com.tenpay.wxwork.salary.model.GridInfo;

@Service
public class GridConfigService {

	@Autowired
	private GridConfigDao gridConfigDao;
	
	public List<GridInfo> listGrids(String bank_sname) {
		return gridConfigDao.listGrids(bank_sname);
//		List<GridInfo> list = gridConfigDao.listGrids(bank_sname);
//		if (list != null) {
//			for (GridInfo gridInfo : list) {
//				String type = gridInfo.getGrid_icon_type();
//				byte[] bytes = gridInfo.getGrid_icon_base64();
//				if (StringUtils.isNotEmpty(type) && bytes != null) {
//					String src = "data:" + type + ";base64," + Base64.encodeBase64String(bytes);
//					gridInfo.setGrid_icon_src(src);
//					gridInfo.setGrid_icon_base64(null);
//				}
//			}
//		}
//		return list;
	}

	@Transactional
	public String insertGrid(GridConfigReq gridConfigReq, String bank_sname) {
		int num = gridConfigReq.getGrid_num();
		int count = gridConfigDao.getGridCount(bank_sname);
		if (count > 0) {			
			int max = gridConfigDao.getMaxNum(bank_sname);
			
			// 新增宫格的序号小于等于已有的最大序号时，才需要移动
			if (num <= max) {
				gridConfigDao.backwardNum(num, bank_sname);
			}
		}
		
//		setIcon(gridConfigReq);
		gridConfigDao.insertGrid(bank_sname, num, gridConfigReq.getGrid_title(),gridConfigReq.getGrid_entry(), 
				gridConfigReq.getGrid_entry_type(), gridConfigReq.getGrid_icon());
		
		return "0";
	}

	@Transactional
	public String deleteGrid(GridConfigReq gridConfigReq) {
		int num = gridConfigDao.getGridNum(gridConfigReq.getGrid_id());
		// TODO bank_sname 改成从session中获取并传递过来
		String bank_sname = gridConfigDao.getBankSname(gridConfigReq.getGrid_id());
		int max = gridConfigDao.getMaxNum(bank_sname);
		
		// 被删除的宫格序号小于最大序号时，才需要移动
		if (num < max) {
			gridConfigDao.forwardNum(num, bank_sname);
		}
		gridConfigDao.deleteGrid(gridConfigReq.getGrid_id());
		return "0";
	}

	@Transactional
	public String updateGrid(GridConfigReq gridConfigReq) {
		int originNum = gridConfigDao.getGridNum(gridConfigReq.getGrid_id());
		int num = gridConfigReq.getGrid_num();
		// TODO bank_sname 改成从session中获取并传递过来
		String bank_sname = gridConfigDao.getBankSname(gridConfigReq.getGrid_id());
		
		if (originNum < num) {
			gridConfigDao.forwardNumUpdate(originNum, num, bank_sname);
		} else if (originNum > num) {
			gridConfigDao.backwardNumUpdate(originNum, num, bank_sname);
		}
		
//		setIcon(gridConfigReq);
		gridConfigDao.updateGrid(gridConfigReq.getGrid_id(), num, gridConfigReq.getGrid_title(),gridConfigReq.getGrid_entry(), 
				gridConfigReq.getGrid_entry_type(), gridConfigReq.getGrid_icon());
		return "0";
	}
	
	/**
	 * 处理图片base64码
	 * @param gridConfigReq
	 */
//	public void setIcon(GridConfigReq gridConfigReq) {
//		String src = gridConfigReq.getGrid_icon_src();
//		String grid_icon_type = null;
//		byte[] grid_icon_base64 = null; 
//		if (StringUtils.isNotEmpty(src)) {
//			// 图片类型
//			String type = src.split(";")[0];
//			grid_icon_type = type.split(":")[1];
//			
//			// 图片base64码
//			String base64Str = src.split(",")[1];
//			grid_icon_base64 = Base64.decodeBase64(base64Str);				
//		}
//		gridConfigReq.setGrid_icon_type(grid_icon_type);
//		gridConfigReq.setGrid_icon_base64(grid_icon_base64);
//	}
	
//	@Transactional
//	public void updateBankConfig() {
//		listGrids();
//	}

}
