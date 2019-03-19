package com.tenpay.wxwork.salary.service;

import com.tenpay.wxwork.salary.client.DeepseaLabClient;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.dto.deepsea.OcrRequest;
import com.tenpay.wxwork.salary.dto.deepsea.OcrIdCardBackResponse;
import com.tenpay.wxwork.salary.dto.deepsea.OcrIdCardFrontResponse;
import com.tenpay.wxwork.salary.dto.deepsea.OcrBankCardResponse;
import com.tenpay.wxwork.salary.presvr.sender.bean.UploadIdCardPhotoReq;
import com.tenpay.wxwork.salary.presvr.sender.bean.UploadIdCardPhotoRes;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.util.BinUtil;
import com.tenpay.wxwork.salary.util.MapConvert;
import com.tenpay.wxwork.salary.config.Range;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Base64;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class BankCardService {

    @Autowired
    private DeepseaLabClient deepseaLabClient;

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BankProxyRequestService bankProxyRequestService;

    @Autowired
    private KeyCacheService keyCacheService;

    private static final Logger logger = LoggerFactory.getLogger(BankCardService.class);

    public String processUpload(String ssid, String photo)
    {
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String userId = session.getUserId();
        String corpId = session.getCorpId();
        // OCR
        byte[] photoBytes = Base64.getDecoder().decode(photo);
        logger.debug("bank card photo size in bytes: " + photoBytes.length);
        if (photoBytes.length > Range.OCR_IMAGE_MAX_SIZE.toInt()) {
            throw new BizException(BizError.PARAM_TOO_LONG.errorCode(), BizError.PARAM_TOO_LONG.errorMsg() + "bank card photo");
        }

        OcrRequest ocrRequest = new OcrRequest(photoBytes);
        OcrBankCardResponse ocrResponse = deepseaLabClient.ocrBankCardPhoto(MapConvert.beanToMap(ocrRequest));
        logger.debug("ocr response: " + ocrResponse);
        if (0 != ocrResponse.getCode()) {
            logger.error("failed to ocr bank card, code: {}, message: {}",
                         ocrResponse.getCode(), ocrResponse.getMessage());
            throw new BizException(BizError.OCR_FAIL.errorCode(), BizError.OCR_FAIL.errorMsg() + "bank card photo");
        }
        logger.debug("ocr bank card number is: " + ocrResponse.getCardNumber());

        // update db
        salaryAccountDAO.updateBankCardPhoto(corpId,userId,SalaryAccount.State.BIND_CARD_RECOGNIZED.toInt(), AesUtil.encrypt(photo,keyCacheService.getAccountSecret()));

        return ocrResponse.getCardNumber().replace(" ", ""); // 防止有空格
    }
}
