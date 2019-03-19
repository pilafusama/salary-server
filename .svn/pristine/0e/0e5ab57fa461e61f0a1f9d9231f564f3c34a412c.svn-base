package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.OpenSalaryAccountReqParam;
import com.tenpay.wxwork.salary.dto.h5.OpenSalaryAccountResponse;
import com.tenpay.wxwork.salary.model.CardBin;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.presvr.sender.bean.OpenSalaryAccountRes;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.h5.OpenSalaryAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hxyh on 2018/8/21.
 */
@RestController
public class OpenSalaryAccountController {

    @Autowired
    private OpenSalaryAccountService openSalaryAccountService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    @PostMapping(value = "/h5/open_salary_account",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object openSalaryAccount(@CookieValue("ssid") String ssid,@RequestBody OpenSalaryAccountReqParam request){
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        String rltv_accno = request.getRltv_accno().replace(" ","");
        String userId = session.getUserId();
        String corpId = session.getCorpId();
        String mobileNumber = request.getMobileNumber();
        // 从db中获取相应的数据，
        OpenSalaryAccountReqParam queryData = openSalaryAccountService.queryOpenSalaryAccountRes(userId,corpId);
        queryData.setSms(request.getSmsCode());//短信验证码
        queryData.setMblph_no(mobileNumber);//客户申请绑定手机号
        queryData.setRltv_accno(rltv_accno);//开立E账户时需绑定的实体账号

        // 从session中取得
        String bankNum = session.getBankCardNumBindOne();
//        FinGateClient finGateClient = new FinGateClient();
//        if(session.getBankCardNumBindOne()!= null || !"".equals(session.getBankCardNumBindOne())){
//            bankNum = session.getBankCardNumBindOne();
//        }else {
//            NationalBankNumber nationalBankNumber = new NationalBankNumber();
//            CardBin cardBin = finGateClient.queryCardBin(rltv_accno);
//            String bankName = cardBin.getBankSname();
//            bankNum = nationalBankNumber.query(bankName);
//            session.setBankCardNumBindOne(bankNum);
//            session.setBankChNameOne(bankName);
//            session.setBankSnameOne(bankName);
//            sessionService.setSession(session);
//        }
        queryData.setDpbkbkcd(bankNum);//绑定实体账号的开户行联行号
        //申请开立E账户客户本他行标志（0 - 本行，1 - 他行）需要根据开户行行联号去查询
        if (bankNum.equals(Constant.CCB)){
            queryData.setRsrv_tpcd(Constant.BANK_TYPE_MARK_0);
        }else {
            queryData.setRsrv_tpcd(Constant.BANK_TYPE_MARK_1);
        }
        queryData.setOcp_cd(Constant.OCP_CD);//职业代码
        queryData.setPlat_flow_no(session.getBankVerifySn());//进行客户识别的流水号
        //queryData.setBankType(queryData.getBankType());
        queryData.setBankType(session.getBankType());
        //更新状态已提交至银行
        openSalaryAccountService.updateFstate(userId,corpId);
        
        // 调用银行接口“开户激活”
        OpenSalaryAccountRes openSalaryAccountRes = openSalaryAccountService.toBankMethod(queryData);
        //银行返回成功后，写入db，开户成功
        if (openSalaryAccountRes != null){
            openSalaryAccountService.updateOpenSalaryAccountRes(userId,corpId,mobileNumber,queryData.getRsrv_tpcd(),session.getBankSnameOne(),openSalaryAccountRes);
        }

        //获取二类户的行名
        String cardNo = openSalaryAccountRes.getDbcrd_cardno().replace(" ", "");
        FinGateClient finGateClient = new FinGateClient();
        CardBin cardBin2 = finGateClient.queryCardBin(cardNo);
        String bankName2 = cardBin2.getBankSname();
        NationalBankNumber nb2 = new NationalBankNumber(bankName2);
        String chName2 = nb2.queryChName(bankName2);
        session.setBankChNameTwo(chName2);
        sessionService.setSession(session);
        return  new OpenSalaryAccountResponse(cardNo,openSalaryAccountRes.getAr_gen_dt(),openSalaryAccountRes.getDtbs_tms(),openSalaryAccountRes.getCst_nm(),
                openSalaryAccountRes.getRltv_accno(),openSalaryAccountRes.getDpbkbkcd(),session.getBankChNameOne(),session.getBankChNameTwo());
    }

    @PostMapping(value = "/h5/cardnum_for_bankname",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object cardNumForBankName(@CookieValue("ssid") String ssid,@RequestBody OpenSalaryAccountReqParam request){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if(sessionInfo == null){
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        String rltv_accno = request.getRltv_accno().replace(" ","");
        if("".equals(rltv_accno) || rltv_accno == null){
            throw new BizException(BizError.BANK_CARD_ERROR.errorCode(), BizError.BANK_CARD_ERROR.errorMsg());
        }
        // 通过卡bin查询开户行联行号以及银行名称
        FinGateClient finGateClient = new FinGateClient();
        NationalBankNumber nationalBankNumber = new NationalBankNumber();
        CardBin cardBin = finGateClient.queryCardBin(rltv_accno);
        String bankName = cardBin.getBankSname();
        NationalBankNumber nb = new NationalBankNumber(bankName);
        String chName = null;
        String bankNum = null;
        try {
            bankNum = nationalBankNumber.query(bankName);
            chName = nb.queryChName(bankName);
        }catch (BizException e){
            return new OpenSalaryAccountResponse("0",e.getErrMsg(),"1",rltv_accno,"目前只支持中农工建交五大银行");
        }

        salaryAccountDAO.updateFstate(SalaryAccount.State.BIND_CARD_RECOGNIZED.toInt(),sessionInfo.getCorpId(),sessionInfo.getUserId(),SalaryAccount.State.USER_VERIFIED.toInt());

        sessionInfo.setBankChNameOne(chName);
        sessionInfo.setBankCardNumBindOne(bankNum);
        sessionInfo.setBankSnameOne(bankName);
        sessionService.setSession(sessionInfo);
        return new OpenSalaryAccountResponse("0","OK","0",rltv_accno, chName);
    }
}
