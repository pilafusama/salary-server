package com.tenpay.wxwork.salary.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.dto.h5.*;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.presvr.sender.bean.*;
import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;
import com.tenpay.wxwork.salary.presvr.sender.bean.BalanceReq;
import com.tenpay.wxwork.salary.presvr.sender.bean.BalanceRes;
import com.tenpay.wxwork.salary.presvr.sender.bean.CurrentDetailReq;
import com.tenpay.wxwork.salary.presvr.sender.bean.CurrentDetailRes;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenpay.bap.relay.context.RelaySessionInfo;
import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.bap.relay.protocol.RelayMessage;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.CorpAppRelationDTO;
import com.tenpay.wxwork.salary.dto.CorpBindAuthenReq;
import com.tenpay.wxwork.salary.dto.CorpBindAuthenReqParam;
import com.tenpay.wxwork.salary.dto.CorpBindAuthenRes;
import com.tenpay.wxwork.salary.dto.CorpBindConfirmReq;
import com.tenpay.wxwork.salary.dto.CorpBindConfirmReqParam;
import com.tenpay.wxwork.salary.dto.CorpBindConfirmRes;
import com.tenpay.wxwork.salary.dto.FlowSubmitReq;
import com.tenpay.wxwork.salary.dto.FlowSubmitRes;
import com.tenpay.wxwork.salary.info.RequestType;
import com.tenpay.wxwork.salary.model.CorpApprovalInfo;
import com.tenpay.wxwork.salary.util.MsgnoGenerator;

import java.io.IOException;

@Service
public class BankProxyRequestService {
    @Autowired
    private RelayRequestService relayService;

    private static final Logger logger = LoggerFactory.getLogger(BankProxyRequestService.class);

    public CorpBindAuthenRes CorpBindAuthen(CorpBindAuthenReqParam request, String appid) {
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", request.getBankType(), Integer.toString(RequestType.CorpBindAuthen), msgno);

        CorpBindAuthenReq corpBindAuthenReq = new CorpBindAuthenReq(new BankProxyRelayRequestMsg(requestBankProxy));
        corpBindAuthenReq.setRequest(request, request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, corpBindAuthenReq);
        handleBankProxyResponse(relayRsp);
        CorpBindAuthenRes corpBindAuthenRes = new CorpBindAuthenRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        corpBindAuthenRes.setResponse(relayRsp.getValueByKey("corp_auth_id"), relayRsp.getValueByKey("retcode"), relayRsp.getValueByKey("errMsg"));
        return corpBindAuthenRes;
    }

    public CorpBindConfirmRes CorpBindConfirm(CorpBindConfirmReqParam request, String appid) {
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", request.getBankType(), Integer.toString(RequestType.CorpBindConfirm), msgno);
        CorpBindConfirmReq corpBindConfirmReq = new CorpBindConfirmReq(new BankProxyRelayRequestMsg(requestBankProxy));
        corpBindConfirmReq.setRequest(request, request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, corpBindConfirmReq);
        handleBankProxyResponse(relayRsp);
        CorpBindConfirmRes corpBindAuthenRes = new CorpBindConfirmRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        corpBindAuthenRes.setResponse(relayRsp.getValueByKey("corp_auth_id"), relayRsp.getValueByKey("retcode"), relayRsp.getValueByKey("errMsg"));
        return corpBindAuthenRes;
    }

    public FlowSubmitRes FlowSubmit(CorpAppRelationDTO corpAppRelationDTO, CorpApprovalInfo corpApprovalInfo, String appid) {
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", corpAppRelationDTO.getBankType(), Integer.toString(RequestType.FlowSubmit), msgno);
        logger.debug("requestBankProxy:" + requestBankProxy);
        FlowSubmitReq flowSubmitReq = new FlowSubmitReq(new BankProxyRelayRequestMsg(requestBankProxy));
        flowSubmitReq.setRequest(corpAppRelationDTO, corpApprovalInfo);

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, flowSubmitReq);
        handleBankProxyResponse(relayRsp);
        FlowSubmitRes flowSubmitRes = new FlowSubmitRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        flowSubmitRes.setResponse(relayRsp.getValueByKey("bank_listid"), relayRsp.getValueByKey("retcode"), relayRsp.getValueByKey("errMsg"));
        return flowSubmitRes;
    }

    public ExampleRes example(ExampleReqParam request) {
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", request.getBankType(), Integer.toString(RequestType.ExampleRequestType), msgno);

        ExampleReq exampleReq = new ExampleReq(new BankProxyRelayRequestMsg(requestBankProxy));
        exampleReq.setRequest(request, request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, exampleReq);
        handleBankProxyResponse(relayRsp);
        ExampleRes exampleRes = new ExampleRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        exampleRes.setResponse(relayRsp.getValueByKey("myMoney"));
        return exampleRes;
    }

    public OpenSalaryAccountRes openSalaryAccount(OpenSalaryAccountReqParam request) {
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", request.getBankType(), Integer.toString(RequestType.OpenSalaryAccountRequestType), msgno);

        OpenSalaryAccountReq openSalaryAccountReq = new OpenSalaryAccountReq(new BankProxyRelayRequestMsg(requestBankProxy));
        openSalaryAccountReq.setRequest(request, request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, openSalaryAccountReq);
        handleBankProxyResponse(relayRsp);
        OpenSalaryAccountRes openSalaryAccountRes = new OpenSalaryAccountRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        openSalaryAccountRes.setResponse(relayRsp.getValueByKey("dbcrd_cardno"),relayRsp.getValueByKey("ar_gen_dt"),relayRsp.getValueByKey("dtbs_tms"),
                relayRsp.getValueByKey("cst_nm"),relayRsp.getValueByKey("rltv_accno"),relayRsp.getValueByKey("dpbkbkcd"));
        return openSalaryAccountRes;
    }

    public BalanceRes balance(SalaryAccount fsalaryCard, BalanceReqParam request) {
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", fsalaryCard.getSalary_card_bank_type(), Integer.toString(RequestType.BalanceRequestType), msgno);

        //包装银行请求接口
        BalanceReq balanceReq = new BalanceReq(new BankProxyRelayRequestMsg(requestBankProxy));
        balanceReq.setRequest(fsalaryCard, request);

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = null;

        relayRsp = relayService.sendRequest(sessionInfo, balanceReq);

        handleBankProxyResponse(relayRsp);
        //响应信息映射成Bean
        BalanceRes balanceRes = new BalanceRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        balanceRes.setResponse(relayRsp);
        return balanceRes;
    }

    public QueryExistCategory2AccountsRes queryExistCategory2Accounts(QueryExistCategory2AccountsReqParam request){
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", request.getBankType(), Integer.toString(RequestType.QueryExistCategory2Accounts), msgno);

        QueryExistCategory2AccountsReq queryExistCategory2AccountsReq = new QueryExistCategory2AccountsReq(new BankProxyRelayRequestMsg(requestBankProxy));
        queryExistCategory2AccountsReq.setRequest(request, request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, queryExistCategory2AccountsReq);
        handleBankProxyResponse(relayRsp);
        QueryExistCategory2AccountsRes queryExistCategory2AccountsRes = new QueryExistCategory2AccountsRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        queryExistCategory2AccountsRes.setResponse(relayRsp.getValueByKey("Vld_Rcrd_Cnt"),
                relayRsp.getValueByKey("Ebnk_Cst_Pltfrm_ID"),
                relayRsp.getValueByKey("ea02_group_str"));

        return queryExistCategory2AccountsRes;
    }

    public VerifyUserRes verifyUser(VerifyUserReqParam request){
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", request.getBankType(), Integer.toString(RequestType.VerifyUser), msgno);

        VerifyUserReq verifyUserReq = new VerifyUserReq(new BankProxyRelayRequestMsg(requestBankProxy));
        verifyUserReq.setRequest(request,request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, verifyUserReq);
        handleBankProxyResponse(relayRsp);

        VerifyUserRes verifyUserRes = new VerifyUserRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        verifyUserRes.setResponse(relayRsp.getValueByKey("plat_flow_no"));

        return verifyUserRes;
    }

    public BankProxyRelayResponseMsg sendSmsCode(SendSmsCodeReqParam request){
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", request.getBankType(), Integer.toString(RequestType.SendSmsCode), msgno);

        SendSmsCodeReq SendSmsCodeReq = new SendSmsCodeReq(new BankProxyRelayRequestMsg(requestBankProxy));
        SendSmsCodeReq.setRequest(request,request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, SendSmsCodeReq);
        handleBankProxyResponse(relayRsp);

        BankProxyRelayResponseMsg res = new BankProxyRelayResponseMsg(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        return res;
    }

    public BankProxyRelayResponseMsg uploadIdCardPhotos(UploadIdCardPhotosReqParam request){
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", request.getBankType(), Integer.toString(RequestType.UploadIdCardPhotos), msgno);

        UploadIdCardPhotosReq uploadIdCardPhotosReq = new UploadIdCardPhotosReq(new BankProxyRelayRequestMsg(requestBankProxy));
        uploadIdCardPhotosReq.setRequest(request,request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, uploadIdCardPhotosReq);
        handleBankProxyResponse(relayRsp);

        BankProxyRelayResponseMsg res = new BankProxyRelayResponseMsg(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        return res;
    }

    public ReuseSalaryAccountRes reuseSalaryAccount(ReuseSalaryAccountReqParam request){
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", request.getBankType(), Integer.toString(RequestType.ReuseSalaryAccount), msgno);

        ReuseSalaryAccountReq reuseSalaryAccountReq = new ReuseSalaryAccountReq(new BankProxyRelayRequestMsg(requestBankProxy));
        reuseSalaryAccountReq.setRequest(request,request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, reuseSalaryAccountReq);
        handleBankProxyResponse(relayRsp);

        ReuseSalaryAccountRes ReuseSalaryAccountRes = new ReuseSalaryAccountRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        ReuseSalaryAccountRes.setResponse(relayRsp.getValueByKey("ar_gen_dt"),
                relayRsp.getValueByKey("dtbs_tms"),
                relayRsp.getValueByKey("cst_nm"),
                relayRsp.getValueByKey("rltv_accno"),
                relayRsp.getValueByKey("dpbkbkcd"));

        return ReuseSalaryAccountRes;
    }

    public CurrentDetailRes currentDetail(CurrentDetailReqParam reqParam, SalaryAccount fsalaryCardInfo) {
        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", fsalaryCardInfo.getSalary_card_bank_type(), Integer.toString(RequestType.CurrentDetailRequestType), msgno);

        //包装银行请求接口
        CurrentDetailReq currentDetailReq = new CurrentDetailReq(new BankProxyRelayRequestMsg(requestBankProxy));
        currentDetailReq.setRequest(reqParam, fsalaryCardInfo.getSalary_card_number());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = null;
        relayRsp = relayService.sendRequest(sessionInfo, currentDetailReq);

        handleBankProxyResponse(relayRsp);
        //响应信息映射成Bean
        String bankStr = relayRsp.getValueByKey("bank_res_info");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CurrentDetailRes bankCurrentDetailRes;
        try
        {
            //返回JSON结果映射成bean
            bankCurrentDetailRes = mapper.readValue(bankStr, CurrentDetailRes.class);
        }
        catch(JsonMappingException e)
        {
            throw new BizException(BizError.JSON_CONVERT_ERROR.errorCode(),"Json converted to object error.");
        }catch(JsonParseException e)
        {
            throw new BizException(BizError.JSON_CONVERT_ERROR.errorCode(),"Json converted to object error.");
        }
        catch(IOException e)
        {
            throw new BizException(BizError.JSON_CONVERT_ERROR.errorCode(),"Json converted to object error.");
        }

        return bankCurrentDetailRes;
    }
    private String BankProxyRelayRequestBuild(String request, String bank_type, String request_type, String MSG_NO) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("MSG_NO=");
        stringBuffer.append(MSG_NO);
        stringBuffer.append("&bank_type=");
        stringBuffer.append(bank_type);
        stringBuffer.append("&request_type=");
        stringBuffer.append(request_type);
        if (!StringUtils.isBlank(request)) {
            stringBuffer.append("&");
            stringBuffer.append(request);
        }
        return stringBuffer.toString();
    }

    private void handleBankProxyResponse(RelayMessage relayRsp) {
        if (!relayRsp.getValueByKey("bank_result").equals("0")) {
//            throw new BizException(Integer.valueOf(relayRsp.getValueByKey("bank_result")), relayRsp.getValueByKey("bank_res_info"));
            //银行返回的错误信息，错误码是String型
            throw new BizException(BizError.BANK_ERROR_MES.errorCode(), relayRsp.getValueByKey("bank_res_info"));
        }

        if (!relayRsp.getValueByKey("result").equals("0")) {
            throw new BizException(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("res_info"));
        }

    }


    public FetchAmountRes fetchAmount(FetchAmountReqParam request) {

        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("",request.getBankType() , Integer.toString(RequestType.FetchAmountQuery), msgno);
        logger.debug("requestBankProxy:" + requestBankProxy);
        FetchAmountReq fetchAmountReq = new FetchAmountReq(new BankProxyRelayRequestMsg(requestBankProxy));
        fetchAmountReq.setRequest(request, request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, fetchAmountReq);
        handleBankProxyResponse(relayRsp);

        FetchAmountRes fetchAmountRes = new FetchAmountRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");

        fetchAmountRes.setResponse(
                relayRsp.getValueByKey("SYS_EVT_TRACE_ID"),
                relayRsp.getValueByKey("result"),
                relayRsp.getValueByKey("res_info")
        );

        return fetchAmountRes;
    }


    public QueryEBindRes queryEBind(QueryEBindReqParam request) {

        String msgno = MsgnoGenerator.getMsgnoFromMDC();
        String requestBankProxy = BankProxyRelayRequestBuild("", request.getBankType(), Integer.toString(RequestType.QueryEBindQuery), msgno);
        logger.debug("requestBankProxy:" + requestBankProxy);
        QueryEBindReq queryEBindReq = new QueryEBindReq(new BankProxyRelayRequestMsg(requestBankProxy));
        queryEBindReq.setRequest(request,request.getBankType());

        RelaySessionInfo sessionInfo = new RelaySessionInfo();
        sessionInfo.setMsgNo(msgno);
        RelayMessage relayRsp = relayService.sendRequest(sessionInfo, queryEBindReq);
        handleBankProxyResponse(relayRsp);
        QueryEBindRes queryEBindRes = new QueryEBindRes(Integer.valueOf(relayRsp.getValueByKey("result")), relayRsp.getValueByKey("resInfo"), "", "");
        queryEBindRes.setResponse(relayRsp.getValueByKey("Ebnk_Sign_Accno"),
                relayRsp.getValueByKey("CoPlf_ID"),
                relayRsp.getValueByKey("Pltfrm_Nm"),
                relayRsp.getValueByKey("Hdl_InsID"),
                relayRsp.getValueByKey("Sign_Dt"),
                relayRsp.getValueByKey("MblPh_No"),
                relayRsp.getValueByKey("Mbsh_No"),
                relayRsp.getValueByKey("Crdt_TpCd"),
                relayRsp.getValueByKey("Crdt_No"),
                relayRsp.getValueByKey("Cst_Nm"),
                relayRsp.getValueByKey("Rltv_AccNo"),
                relayRsp.getValueByKey("Rsrv_TpCd"),
                relayRsp.getValueByKey("DpBkBkCD"),
                relayRsp.getValueByKey("Lv1_InsID"),
                relayRsp.getValueByKey("Lvl2_InsID"),
                relayRsp.getValueByKey("result"),
                relayRsp.getValueByKey("res_info")
        );

        return queryEBindRes;
    }


}
