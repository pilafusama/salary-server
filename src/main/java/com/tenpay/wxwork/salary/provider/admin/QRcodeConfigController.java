package com.tenpay.wxwork.salary.provider.admin;

import java.util.List;

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
import com.tenpay.wxwork.salary.dto.admin.BankInfo;
import com.tenpay.wxwork.salary.dto.admin.QRcodeConfigParam;
import com.tenpay.wxwork.salary.dto.admin.QRcodeDownloadResponse;
import com.tenpay.wxwork.salary.dto.admin.QRcodeInsertResponse;
import com.tenpay.wxwork.salary.dto.admin.QRcodeListResponse;
import com.tenpay.wxwork.salary.dto.admin.QRcodeQueryResponse;
import com.tenpay.wxwork.salary.dto.admin.QueryBankListResponse;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.IdGenerator;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.admin.QRcodeConfigService;

@RestController
@RequestMapping("/bank_admin")
public class QRcodeConfigController {
	
	@Autowired
	private QRcodeConfigService qRcodeConfigService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private IdGenerator idGenerator;
	
    /**
     * 二维码列表
     * @return
     */
	@PostMapping(value = "/list_promotion_qrcodes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object listQRcodes(/*@CookieValue("ssid") String ssid*/) {
//		if (!idGenerator.isAdminSessionId(ssid)) {
//            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//        }
//        SessionInfo session = sessionService.getSession(ssid);
//        if (session == null) {
//            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//        }
		List<QRcodeConfigParam> list = qRcodeConfigService.listQRcodes();
		return new QRcodeListResponse(list);
	}
	
	/**
	 * 查询二维码详情
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/get_promotion_qrcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object queryQRcodeById(/*@CookieValue("ssid") String ssid, */@RequestBody QRcodeConfigParam request) {
//		if (!idGenerator.isAdminSessionId(ssid)) {
//            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//        }
//        SessionInfo session = sessionService.getSession(ssid);
//        if (session == null) {
//            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//        }
		
		QRcodeConfigParam configParam = qRcodeConfigService.queryQRcodeById(request.getPromotion_qrcode_id());
		return new QRcodeQueryResponse(configParam);
	}
	
	/**
	 * 创建二维码
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/create_promotion_qrcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object insertQRcode(/*@CookieValue("ssid") String ssid, */@RequestBody QRcodeConfigParam request) {
//		if (!idGenerator.isAdminSessionId(ssid)) {
//            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//        }
//        SessionInfo session = sessionService.getSession(ssid);
//        if (session == null) {
//            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//        }
		
		int promotion_qrcode_id = qRcodeConfigService.insertQRcode(request);
		return new QRcodeInsertResponse(promotion_qrcode_id);
	}
	
	/**
	 * 不同尺寸的二维码下载
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/download_promotion_qrcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object downloadQRcodeImage(/*@CookieValue("ssid") String ssid, */@RequestBody QRcodeConfigParam request) {
//		if (!idGenerator.isAdminSessionId(ssid)) {
//			throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//		}
//		SessionInfo session = sessionService.getSession(ssid);
//		if (session == null) {
//		    throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//		}
		
		QRcodeConfigParam configParam = qRcodeConfigService.downloadQRcodeImage(request);
		return new QRcodeDownloadResponse(configParam.getQrcode_name(), configParam.getPromotion_qrcode());
	}
	
	/**
	 * 更新二维码配置参数
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/update_promotion_qrcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object updateQRcode(/*@CookieValue("ssid") String ssid, */@RequestBody QRcodeConfigParam request) {
//		if (!idGenerator.isAdminSessionId(ssid)) {
//			throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//		}
//		SessionInfo session = sessionService.getSession(ssid);
//		if (session == null) {
//		    throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//		}
		String retcode = qRcodeConfigService.updateQRcode(request);
		return new FrontEndResponse(retcode, "");
	}
	
	//获取银行列表
	@PostMapping(value = "/query_bank_list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object queryBankList(/*@CookieValue("ssid") String ssid*/) {
//		if (!idGenerator.isAdminSessionId(ssid)) {
//            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//        }
//        SessionInfo session = sessionService.getSession(ssid);
//        if (session == null) {
//            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
//        }
		List<BankInfo> list = qRcodeConfigService.queryBankList();
		return new QueryBankListResponse(list);
	}
	
	//获取应用列表
	
}
