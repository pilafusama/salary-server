package com.tenpay.wxwork.salary.service.h5;
import com.tenpay.fingate.util.Sha256;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.dao.AppInfoDAO;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.h5.GestCheckReqParam;
import com.tenpay.wxwork.salary.dto.h5.GestCheckResponse;
import com.tenpay.wxwork.salary.model.AppInfo;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import com.tenpay.wxwork.salary.service.SessionService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GestureCheckService {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private KeyCacheService keyCacheService;

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    @Autowired
    private AppInfoDAO appInfoDAO;

    public FrontEndResponse setFgesturePassword (String ssid, GestCheckReqParam request) {


        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        SalaryAccount salaryAccount = salaryAccountDAO.querySalaryAccountByUserId(session.getCorpId(),
                                                                                  session.getUserId());
        if (null == salaryAccount) {
            AppInfo appInfo = appInfoDAO.queryByMchAndTypeAndBusiness(Constant.CCB_MCH_ID, // TODO 先写死，以后再提供企业设置，同时需要根据 t_corp_app_relation 表判断是否有权限。
                                                                      AppInfo.TYPE_SALARY,
                                                                      AppInfo.BUSINESS_STYLE_OPEN_ACCOUNT);
            if (null == appInfo) {
                throw new BizException(BizError.NO_APP_INFO);
            }

            salaryAccountDAO.insertNewAccount(session.getCorpId(), session.getUserId(),
                                              SalaryAccount.State.INIT.toInt(),
                                              appInfo.getBankType(), appInfo.getBankSname());
        }

        request.setUserId(session.getUserId());
        request.setFcorpId(session.getCorpId());
        request.setFgesturePassword(Sha256.sha256(keyCacheService.getPasswordSalt() + request.getFgesturePassword()));
        request.setFinitSatatus(SalaryAccount.State.ACCOUNT_OPENED.toInt());
        salaryAccountDAO.setdataFgesture(request);

        return new GestCheckResponse("0","OK","0");
    }

   //更新手势密码
    public FrontEndResponse updateFgesture(String ssid,GestCheckReqParam request) {

        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        request.setUserId(session.getUserId());
        request.setFcorpId(session.getCorpId());
        request.setFgesturePassword(Sha256.sha256(keyCacheService.getPasswordSalt() + request.getFgesturePassword()));

        salaryAccountDAO.updataFgesture(request);

        return new GestCheckResponse("0","OK","0");
    }
    //校验手势密码
    public GestCheckResponse checkFgesture(String ssid,GestCheckReqParam request) {

        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        request.setUserId(session.getUserId());
        request.setFcorpId(session.getCorpId());
        String password = Sha256.sha256(keyCacheService.getPasswordSalt() + request.getFgesturePassword());
        String origPassword = salaryAccountDAO.getPasswordById(request);

        if(password.equals(origPassword)){
            return new GestCheckResponse("0","OK","0");
        }else{
            return new GestCheckResponse("0",BizError.PASSWORD_ERROR.errorMsg(),"1");
        }
    }
}
