package com.tenpay.wxwork.salary.service.h5;

import static com.tenpay.wxwork.salary.common.BizError.NOT_GET_ACCONT_INFO;

import com.tenpay.wxwork.salary.service.KeyCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.VerifyUserReqParam;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.presvr.sender.bean.VerifyUserRes;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.KeyCacheService;

@Service
public class VerifyUserService {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private BankProxyRequestService bankProxyRequestService;
    @Autowired
    private SalaryAccountDAO salaryAccountDAO;
    @Autowired
    private KeyCacheService keyCacheService;

    public void verifyUser(String ssid){
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        VerifyUserReqParam request = new VerifyUserReqParam();
        SalaryAccount salaryAccount = salaryAccountDAO.queryByUserId(session.getCorpId(), session.getUserId());
        if(salaryAccount==null){
            throw new BizException(NOT_GET_ACCONT_INFO.errorCode(), NOT_GET_ACCONT_INFO.errorMsg());
        }
        request.setCst_nm(AesUtil.decrypt(salaryAccount.getCre_name(),keyCacheService.getAccountSecret()));
        request.setCrdt_tpcd(salaryAccount.getCre_type().name()==SalaryAccount.CreType.ID_CARD.name()?Constant.ID_CARD_CODE_1010:"");
        request.setCrdt_no(AesUtil.decrypt(salaryAccount.getCre_id(),keyCacheService.getAccountSecret()));
//        request.setBankType(salaryAccount.getSalary_card_bank_type());
        request.setBankType(session.getBankType());
        if(SalaryAccount.CreGender.MALE.name().equals(salaryAccount.getCre_gender().name())){
            request.setGnd_cd(Constant.MALE_CN);
        }else if(SalaryAccount.CreGender.FEMALE.name().equals(salaryAccount.getCre_gender().name())){
            request.setGnd_cd(Constant.FEMALE_CN);
        }else{
            request.setGnd_cd("");
        }
        request.setEthnct_cd(salaryAccount.getCre_nationality());
        request.setBrth_dt(salaryAccount.getCre_birthdate());
        request.setAvldt_dt(salaryAccount.getCre_valid_date());
        request.setAvldt_eddt(salaryAccount.getCre_expire_date());
        request.setInst_chn_fullnm(salaryAccount.getCre_issue_authority());
        request.setDtl_adr(AesUtil.decrypt(salaryAccount.getCre_address(),keyCacheService.getAccountSecret()));
        request.setSign_orcd(Constant.SIGN_ORCD);
        request.setFile_nm(session.getIdCardFrontFileName());
        request.setUpload_flnm(session.getIdCardBackFileName());
        request.setUserid(session.getUserId());
        request.setCorpid(session.getCorpId());

        String[] keys = AesUtil.getAesKey(keyCacheService.getAccountSecret());
        request.setPassword(keys[0]);
        request.setVectorKey(keys[1]);

        VerifyUserRes res = bankProxyRequestService.verifyUser(request);

        //更新状态
        salaryAccountDAO.updateFstate(SalaryAccount.State.USER_VERIFIED.toInt(),session.getCorpId(), session.getUserId(),SalaryAccount.State.LIVENESS_DETECTED.toInt());

        //将银行返回的交易流水号放入session
        session.setBankVerifySn(res.getPlat_flow_no());
        sessionService.setSession(session);
    }
}
