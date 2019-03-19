package com.tenpay.wxwork.salary.provider.admin;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.admin.SalaryFieldsParam;
import com.tenpay.wxwork.salary.dto.admin.SalaryFieldsResponse;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.IdGenerator;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.admin.SalaryFieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * 工资字段入库
 */
@RestController
@RequestMapping("/admin")
public class SalaryFieldsController {

    @Autowired
    private SalaryFieldsService salaryFieldsService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private IdGenerator idGenerator;


    /**
     这是第一次配置发薪表模板or修改模板调用操作
     */
    @PostMapping(value = "/salary_fields",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object insertSalaryFields(@CookieValue("ssid") String ssid, @RequestBody SalaryFieldsParam salaryFieldsParam)
    {

        //1、验证
        if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = session.getCorpId();

        salaryFieldsService.InsertSalaryFields(corpId, salaryFieldsParam);

        return new FrontEndResponse();
    }


    /**
     这是查询发薪表模板
     */
    @PostMapping(value = "/select_salary_fields",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object selectSalaryFields(@CookieValue("ssid") String ssid)
    {

        //1、验证
        if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = session.getCorpId();
        SalaryFieldsResponse salaryFieldsResponse = salaryFieldsService.SelectSalaryFields(corpId);
        return salaryFieldsResponse;
    }

    /**
     * 查询企业是否配置了发薪模板
     * @param ssid
     * @return
     */
    @PostMapping(value = "/select_is_config_template", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object SelectIsCOnfigTemplate (@CookieValue("ssid") String ssid){
        //1、验证
        if (!idGenerator.isAdminSessionId(ssid)) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = session.getCorpId();
        SalaryFieldsResponse salaryFieldsResponse = salaryFieldsService.SelectIsCOnfigTemplate(corpId);
        return salaryFieldsResponse;
    }
}
