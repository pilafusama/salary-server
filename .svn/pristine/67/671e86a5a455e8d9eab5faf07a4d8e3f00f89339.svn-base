package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.model.CardBin;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import com.tenpay.wxwork.salary.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.ReuseSalaryAccountReqParam;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.presvr.sender.bean.ReuseSalaryAccountRes;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.IdGenerator;

@Service
public class ReuseSalaryAccountService {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private BankProxyRequestService bankProxyRequestService;
    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private KeyCacheService keyCacheService;

    public ReuseSalaryAccountRes reuseSalaryAccount(String ssid, ReuseSalaryAccountReqParam reqParam){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        //SalaryAccount salaryAccount = salaryAccountDAO.queryByUserId(sessionInfo.getCorpId(),sessionInfo.getUserId());
//        reqParam.setBankType(salaryAccount.getSalary_card_bank_type());
        reqParam.setBankType(sessionInfo.getBankType());
        reqParam.setMbsh_no(idGenerator.genSalaryOpenid(sessionInfo.getCorpId(), sessionInfo.getUserId()));
        reqParam.setSign_dt(DateUtil.getDate());
        //发送给银行更新状态
        salaryAccountDAO.updateFstate(SalaryAccount.State.REUSE_REQ_SENT.toInt(), sessionInfo.getCorpId(), sessionInfo.getUserId(),SalaryAccount.State.REUSE_SMS_SENT.toInt());

        ReuseSalaryAccountRes res =  bankProxyRequestService.reuseSalaryAccount(reqParam);

        //返回成功后数据写入db
        if(res!=null){
            String dtbs_tms = res.getDtbs_tms();
            dtbs_tms = dtbs_tms.substring(0, 4)+"-"+dtbs_tms.substring(4, 6)+"-"+dtbs_tms.substring(6, 8)+" "+dtbs_tms.substring(8, 10)+":"+dtbs_tms.substring(10, 12)+":"+dtbs_tms.substring(12, 14);

            // 可能包含空格
            String bindCardNo = res.getRltv_accno().replace(" ", "");
            res.setRltv_accno(bindCardNo); // 外部用到了

            // 通过卡bin查询开户行名称 一类户
            FinGateClient finGateClient = new FinGateClient();
            CardBin cardBin = finGateClient.queryCardBin(bindCardNo);
            String bankSname = cardBin.getBankSname();
            NationalBankNumber nb = new NationalBankNumber(bankSname);
            String chName = nb.queryChName(bankSname);
            sessionInfo.setBankSnameOne(bankSname);
            sessionInfo.setBankChNameOne(chName);
            salaryAccountDAO.updateOpenSalaryAccountRes(sessionInfo.getUserId(),
                    sessionInfo.getCorpId(),
                    AesUtil.encrypt(reqParam.getMblph_no(),keyCacheService.getAccountSecret()),
                    SalaryAccount.State.ACCOUNT_OPENED.toInt(),
                    SalaryAccount.OpeningType.REUSE.name(),
                    AesUtil.encrypt(reqParam.getEbnk_sign_accno(),keyCacheService.getAccountSecret()),
                    dtbs_tms,
                    AesUtil.encrypt(res.getCst_nm(),keyCacheService.getAccountSecret()),
                    AesUtil.encrypt(bindCardNo,keyCacheService.getAccountSecret()),
                    res.getDpbkbkcd(),
                    reqParam.getRsrv_tpcd().equals("0") ? SalaryAccount.CardBanksRelation.SAME_BANK.toString() : SalaryAccount.CardBanksRelation.DIFF_BANK.toString(),
                    bankSname
            );
            //获取二类户银行名称
            String ebnk_sign_accno = reqParam.getEbnk_sign_accno();//二类户卡号
            CardBin cardBin2 = finGateClient.queryCardBin(ebnk_sign_accno);
            String bankName2 = cardBin2.getBankSname();
            NationalBankNumber nb2 = new NationalBankNumber(bankName2);
            String chName2 = nb2.queryChName(bankName2);
            sessionInfo.setBankChNameTwo(chName2);
            sessionService.setSession(sessionInfo);
        }
        return res;
    }
}
