package com.tenpay.wxwork.salary.provider.admin;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.admin.*;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.IdGenerator;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.admin.SingleCardInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by v_lifangguo on 2018/11/14.
 * 单笔银行卡信息添加
 */
@RestController
@RequestMapping("/admin")
public class SingleCardInfoController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SingleCardInfoController.class);

    @Autowired
    private SingleCardInfoService cardService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private IdGenerator idGenerator;

    @PostMapping(value = "/find_department",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object findDepartment(@CookieValue("ssid") String ssid, @RequestBody UserDepartmentReq req) throws Exception {
        if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = session.getCorpId();
        return cardService.findDepartment(req.getName(), corpId);

    }

    @PostMapping(value = "/save_single_card",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object saveCardInfo(@CookieValue("ssid") String ssid, @RequestBody SingleCardInfoReq req) throws Exception {
        if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = session.getCorpId();
        cardService.saveSingleCard(req, corpId);
        return new FrontEndResponse();

    }

}
