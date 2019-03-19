package com.tenpay.wxwork.salary.provider.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.admin.GridConfigReq;
import com.tenpay.wxwork.salary.dto.admin.GridConfigResponse;
import com.tenpay.wxwork.salary.model.GridInfo;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.admin.GridConfigService;

/**
 * 九宫格管理
 * @author v_dongzhao
 *
 */
@RestController
@RequestMapping("/admin")
public class GridConfigController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(GridConfigController.class);
	
	@Autowired
	private GridConfigService gridConfigService;

	/**
	 * 查询已有配置列表
	 * @param gridConfigReq
	 * @return
	 */
	@PostMapping(value = "/list_grids", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object listGrids(/*@CookieValue("ssid") String ssid*/) {		
		// TODO 管理员身份验证
		// 从cookie获取ssid，从session获取bank_sname
		/*if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }*/
		
		// TODO 登陆态验证
		/*SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }*/		
//		String bank_sname = session.getbankSname();
//		List<GridInfo> gridList = gridConfigService.listGrids(bank_sname);
		List<GridInfo> gridList = gridConfigService.listGrids("CCB");
		
		return new GridConfigResponse(gridList);
	}
	
	/**
	 * 新增
	 * @param gridConfigReq
	 * @return
	 */
	@PostMapping(value = "/insert_grid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object insertGrid(/*@CookieValue("ssid") String ssid,*/ @RequestBody GridConfigReq gridConfigReq) {
		/*if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
		SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        String bank_sname = session.getbankSname();*/
        
		String code = gridConfigService.insertGrid(gridConfigReq, "CCB");
		return new FrontEndResponse(code, "");
	}
	
	/**
	 * 删除
	 * @param gridConfigReq
	 * @return
	 */
	@PostMapping(value = "/delete_grid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object deleteGrid(/*@CookieValue("ssid") String ssid,*/ @RequestBody GridConfigReq gridConfigReq) {
		/*if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
		SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }*/
		String code = gridConfigService.deleteGrid(gridConfigReq);
		return new FrontEndResponse(code, "");
	}
	
	/**
	 * 修改
	 * @param gridConfigReq
	 * @return
	 */
	@PostMapping(value = "/update_grid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object updateGrid(/*@CookieValue("ssid") String ssid,*/ @RequestBody GridConfigReq gridConfigReq) {
		/*if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
		SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }*/
		String code = gridConfigService.updateGrid(gridConfigReq);
		return new FrontEndResponse(code, "");
	}	

	
}
