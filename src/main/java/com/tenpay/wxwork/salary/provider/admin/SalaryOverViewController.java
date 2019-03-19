package com.tenpay.wxwork.salary.provider.admin;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.admin.SalaryOverViewInfo;
import com.tenpay.wxwork.salary.dto.admin.ViewSalaryAllDetail;
import com.tenpay.wxwork.salary.dto.admin.ViewSalaryAllDetailResponse;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.IdGenerator;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.admin.SalaryOverViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class SalaryOverViewController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SalaryOverViewController.class);

    @Autowired
    private SalaryOverViewService salaryOverViewService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private IdGenerator idGenerator;

    /**
     * 查询企业发薪工资总览的信息
     * @param ssid
     * @param salaryOverViewInfo
     * @return
     */
    @RequestMapping(value = "/salary_overe_view_list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object sendMsgToNoticeUser(@CookieValue("ssid") String ssid, @RequestBody SalaryOverViewInfo salaryOverViewInfo) {
        if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        SessionInfo session = sessionService.getSession(ssid);
        String month = salaryOverViewInfo.getMonth();
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        if (month == null || "".equals(month)) {
            throw new BizException(BizError.PLEASE_SELECT_MONTH.errorCode(), BizError.PLEASE_SELECT_MONTH.errorMsg());
        }
        String corpid = session.getCorpId();
        List<ViewSalaryAllDetail> viewSalaryAllDetails = salaryOverViewService.viewSalaryAllDetailForList(corpid, month);
        return new ViewSalaryAllDetailResponse(viewSalaryAllDetails);
    }

    /**
     * 删除企业发薪总览信息
     * @param ssid
     * @param salaryOverViewInfo
     * @return
     */
    @RequestMapping(value = "/update_salary_over_view_for_batchno", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object updateSalaryOverViewForBatchno(@CookieValue("ssid") String ssid, @RequestBody SalaryOverViewInfo salaryOverViewInfo) {
        if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        SessionInfo session = sessionService.getSession(ssid);
        String month = salaryOverViewInfo.getMonth();
        String batch_no = String.valueOf(salaryOverViewInfo.getBatch_no());
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        if (month == null || "".equals(month)) {
            throw new BizException(BizError.PLEASE_SELECT_MONTH.errorCode(), BizError.PLEASE_SELECT_MONTH.errorMsg());
        }
        String corpid = session.getCorpId();
        String result = salaryOverViewService.updateSalaryOverViewForBatchno(corpid, month, batch_no);
        if ("0".equals(result)){
            return new FrontEndResponse("0","删除成功！");
        }else {
            return new FrontEndResponse("0","删除失败！");
        }
    }
}
