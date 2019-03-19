package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.service.KeyCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenpay.wxwork.salary.service.IdGenerator;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.OpenSalaryAccountReqParam;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.presvr.sender.bean.OpenSalaryAccountRes;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;

/**
 * Created by hxyh on 2018/8/21.
 */
@Service
public class OpenSalaryAccountService {

    @Autowired
    private BankProxyRequestService bankProxyRequestService;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;
    @Autowired
    private KeyCacheService keyCacheService;

    public OpenSalaryAccountReqParam queryOpenSalaryAccountRes(String userId,String corpId){
        return salaryAccountDAO.queryOpenSalaryAccountRes(userId ,corpId);
    }

    public OpenSalaryAccountRes toBankMethod(OpenSalaryAccountReqParam request){
        request.setCrdt_no(AesUtil.decrypt(request.getCrdt_no(),keyCacheService.getAccountSecret()));
        request.setCst_nm(AesUtil.decrypt(request.getCst_nm(),keyCacheService.getAccountSecret()));
        request.setCtc_adr(AesUtil.decrypt(request.getCtc_adr(),keyCacheService.getAccountSecret()));
        request.setMbsh_no(idGenerator.genSalaryOpenid(request.getCorpid(), request.getUserid()));
        if (request.getGnd_cd().equals("MALE")){
            request.setGnd_cd(Constant.MALE);
        }else if(request.getGnd_cd().equals("FEMALE")){
            request.setGnd_cd(Constant.FEMALE);
        }else if(request.getGnd_cd().equals("UNKNOWN")){
            request.setGnd_cd(Constant.UNKNOWN);
        }
        if (request.getCrdt_tpcd().equals("ID_CARD")){
            request.setCrdt_tpcd(Constant.ID_CARD_CODE_1010);
        }else {
            request.setCrdt_tpcd(Constant.ID_CARD_CODE_9999);
        }
        request.setCrdt_efdt(request.getCrdt_efdt().replace("-", ""));
        request.setCrdt_exdat(request.getCrdt_exdat().replace("-", ""));
        OpenSalaryAccountRes openSalaryAccountRes = bankProxyRequestService.openSalaryAccount(request);
        return openSalaryAccountRes;
    }

    public void updateOpenSalaryAccountRes(String userId, String corpId ,String mobileNumber,String Rsrv_tpcd,String bankName,OpenSalaryAccountRes openSalaryAccountRes){
        int fstate = SalaryAccount.State.ACCOUNT_OPENED.toInt();
        String fopening_type = SalaryAccount.OpeningType.CREATE.name();
        String dbcrd_cardno = AesUtil.encrypt(openSalaryAccountRes.getDbcrd_cardno(),keyCacheService.getAccountSecret());
        String dtbs_tms = openSalaryAccountRes.getDtbs_tms();
        dtbs_tms = dtbs_tms.substring(0, 4)+"-"+dtbs_tms.substring(4, 6)+"-"+dtbs_tms.substring(6, 8)+" "+dtbs_tms.substring(8, 10)+":"+dtbs_tms.substring(10, 12)+":"+dtbs_tms.substring(12, 14);
        String cst_nm = AesUtil.encrypt(openSalaryAccountRes.getCst_nm(),keyCacheService.getAccountSecret());
        String rltv_accno = AesUtil.encrypt(openSalaryAccountRes.getRltv_accno(),keyCacheService.getAccountSecret());
        mobileNumber = AesUtil.encrypt(mobileNumber,keyCacheService.getAccountSecret());
        String dpbkbkcd = openSalaryAccountRes.getDpbkbkcd();
        salaryAccountDAO.updateOpenSalaryAccountRes(userId,corpId,mobileNumber,fstate,fopening_type,dbcrd_cardno,dtbs_tms,cst_nm,rltv_accno,dpbkbkcd,
                Rsrv_tpcd.equals("0") ? SalaryAccount.CardBanksRelation.SAME_BANK.toString() : SalaryAccount.CardBanksRelation.DIFF_BANK.toString(),bankName);
    }

    public void updateFstate(String userId, String corpId){
        salaryAccountDAO.updateFstate(SalaryAccount.State.OPEN_REQ_SENT.toInt(),corpId,userId,SalaryAccount.State.OPEN_SMS_SENT.toInt());
    }

}
