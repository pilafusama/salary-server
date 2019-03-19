package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.dto.h5.UploadBankCardPhotoRequest;
import com.tenpay.wxwork.salary.dto.h5.UploadBankCardPhotoResponse;
import com.tenpay.wxwork.salary.model.CardBin;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.BankCardService;
import com.tenpay.wxwork.salary.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by win7 on 2018/9/11.
 */
@Service
public class UploadBankCardPhotoService {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private BankCardService bankCardService;

    public UploadBankCardPhotoResponse uploadBankCardPhoto(String ssid, UploadBankCardPhotoRequest uploadBankCardPhotoRequest){
        //1、参数判断，前置条件检查
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        //2、调用AI实验室银行卡OCR接口，识别银行卡号
        String cardNumber = bankCardService.processUpload(ssid,uploadBankCardPhotoRequest.getBankCardImage());
        //3、调用金融网关卡bin接口，取得银行卡发卡行和卡类型。目前仅支持中农工建交五大行
        FinGateClient finGateClient = new FinGateClient();
        CardBin cardBin = finGateClient.queryCardBin(cardNumber);

        //4、取得发卡行的总行联行号
        NationalBankNumber nationalBankNumber = new NationalBankNumber();
        String bankName = cardBin.getBankSname();
        String bankNum = null;
        String chName = null;
        try {
            bankNum = nationalBankNumber.query(bankName);
            NationalBankNumber nb = new NationalBankNumber(bankName);
            chName = nb.queryChName(bankName);
        }catch (BizException e){
            return new UploadBankCardPhotoResponse("0",e.getErrMsg(),"1",cardNumber, "目前只支持中农工建交五大银行", "");
        }
        sessionInfo.setBankChNameOne(chName);
        sessionInfo.setBankCardNumBindOne(bankNum);
        sessionInfo.setBankSnameOne(bankName);
        sessionService.setSession(sessionInfo);
        return new UploadBankCardPhotoResponse("0","OK","0",cardNumber, chName, bankNum);
    }
}
