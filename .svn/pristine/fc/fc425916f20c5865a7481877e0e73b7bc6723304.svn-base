package com.tenpay.wxwork.salary.service;

import com.tenpay.wxwork.salary.client.DeepseaLabClient;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.dto.deepsea.OcrIdCardRequest;
import com.tenpay.wxwork.salary.dto.deepsea.OcrIdCardBackResponse;
import com.tenpay.wxwork.salary.dto.deepsea.OcrIdCardFrontResponse;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.UploadIdCardPhotosReqParam;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.model.SalaryAccount;
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


@Service
public class IdCardPhotosService {

    @Autowired
    private DeepseaLabClient deepseaLabClient;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BankProxyRequestService bankProxyRequestService;

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    @Autowired
    private KeyCacheService keyCacheService;

    private static final Logger logger = LoggerFactory.getLogger(IdCardPhotosService.class);

    public void verifyUpload(String ssid, String photo, boolean isFrontPhoto) {
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }

        byte[] photoBytes = Base64.getDecoder().decode(photo);
        logger.debug("photo size of {} in bytes: {}", session.getUserId(), photoBytes.length);
        if (photoBytes.length > Range.OCR_IMAGE_MAX_SIZE.toInt()) {
            throw new BizException(BizError.PARAM_TOO_LONG.errorCode(), BizError.PARAM_TOO_LONG.errorMsg() + "back photo");
        }

        // OCR
        OcrIdCardRequest request = new OcrIdCardRequest(isFrontPhoto, photoBytes);
        OcrIdCardFrontResponse response = deepseaLabClient.ocrIdCardFrontPhoto(MapConvert.beanToMap(request));
        logger.debug("ocr photo response: " + response);
        if (0 != response.getCode()) {
            logger.error("failed to ocr photo, code: {}, message: {}",
                    response.getCode(), response.getMessage());
            throw new BizException(BizError.OCR_FAIL.errorCode(), BizError.OCR_FAIL.errorMsg());
        }

    }

    public String[] processUpload(String ssid, String frontPhoto, String backPhoto)
    {
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        SalaryAccount salaryAccount = salaryAccountDAO.queryByUserId(session.getCorpId(), session.getUserId());
        
        byte[] frontPhotoBytes = Base64.getDecoder().decode(frontPhoto);
        logger.debug("front photo size of {} in bytes: {}", session.getUserId(), frontPhotoBytes.length);
        if (frontPhotoBytes.length > Range.OCR_IMAGE_MAX_SIZE.toInt()) {
            throw new BizException(BizError.PARAM_TOO_LONG.errorCode(), BizError.PARAM_TOO_LONG.errorMsg() + "front photo");
        }

        byte[] backPhotoBytes = Base64.getDecoder().decode(backPhoto);
        logger.debug("back photo size of {} in bytes: {}", session.getUserId(), backPhotoBytes.length);
        if (backPhotoBytes.length > Range.OCR_IMAGE_MAX_SIZE.toInt()) {
            throw new BizException(BizError.PARAM_TOO_LONG.errorCode(), BizError.PARAM_TOO_LONG.errorMsg() + "back photo");
        }

        // OCR 正面
        OcrIdCardRequest frontRequest = new OcrIdCardRequest(true, frontPhotoBytes);
        OcrIdCardFrontResponse frontResponse = deepseaLabClient.ocrIdCardFrontPhoto(MapConvert.beanToMap(frontRequest));
        logger.debug("ocr front photo response: " + frontResponse);
        if (0 != frontResponse.getCode()) {
            logger.error("failed to ocr front photo, code: {}, message: {}",
                         frontResponse.getCode(), frontResponse.getMessage());
            throw new BizException(BizError.OCR_FRONT_FAIL.errorCode(), BizError.OCR_FRONT_FAIL.errorMsg());
        }

        // OCR 反面
        OcrIdCardRequest backRequest = new OcrIdCardRequest(false, backPhotoBytes);
        OcrIdCardBackResponse backResponse = deepseaLabClient.ocrIdCardBackPhoto(MapConvert.beanToMap(backRequest));
        logger.info("ocr back photo response: " + backResponse);
        if (0 != backResponse.getCode()) {
            logger.error("failed to ocr back photo, code: {}, message: {}",
                         backResponse.getCode(), backResponse.getMessage());
            throw new BizException(BizError.OCR_BACK_FAIL.errorCode(), BizError.OCR_BACK_FAIL.errorMsg());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(frontResponse.getData().getBirthAsDate() == null){
            throw new BizException(BizError.DATE_FORMAT_ERROR.errorCode(),BizError.DATE_FORMAT_ERROR.errorMsg());
        }
        if(salaryAccount.getState() == SalaryAccount.State.INIT.toInt()
                || salaryAccount.getState() == SalaryAccount.State.CRE_RECOGNIZED.toInt()
                || salaryAccount.getState() == SalaryAccount.State.GESTURE_PASSWORD_SET.toInt()){//数据库中还有状态为GESTURE_PASSWORD_SET的数据
            salaryAccountDAO.updateCreInfo(session.getCorpId(), session.getUserId(),
                                           salaryAccount.getState(),
                                           SalaryAccount.State.CRE_RECOGNIZED.toInt(),
                                           SalaryAccount.CreType.ID_CARD.name(),
                                           AesUtil.encrypt(frontResponse.getData().getId(),
                                                           keyCacheService.getAccountSecret()),
                                           AesUtil.encrypt(frontResponse.getData().getName(),keyCacheService.getAccountSecret()),
                                           frontResponse.getData().getSex().equals("女") ? SalaryAccount.CreGender.FEMALE.name() : SalaryAccount.CreGender.MALE.name(),
                                           frontResponse.getData().getNation(),
                                           dateFormat.format(frontResponse.getData().getBirthAsDate()),
                                           AesUtil.encrypt(frontResponse.getData().getAddress(),keyCacheService.getAccountSecret()),
                                           AesUtil.encrypt(backResponse.getData().getAuthority(),keyCacheService.getAccountSecret()),
                                           dateFormat.format(backResponse.getData().getValid_date_start()),
                                           dateFormat.format(backResponse.getData().getValid_date_end()),
                                           AesUtil.encrypt(frontPhoto,keyCacheService.getAccountSecret()),
                                           AesUtil.encrypt(backPhoto,keyCacheService.getAccountSecret()));

        }
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = new Date();
        String frontName = dateFormat2.format(date)+"_"+frontResponse.getData().getId()+"_ZM.jpg";
        String backName = dateFormat2.format(date)+"_"+frontResponse.getData().getId()+"_FM.jpg";
        //绑卡开户模式
        if(Constant.BIND_AND_OPEN_MODE.equals(session.getOpenAccountMode())){
            //上传正面
            UploadIdCardPhotosReqParam reqParam1 = new UploadIdCardPhotosReqParam();
            reqParam1.setFile_date(dateFormat1.format(date));
            reqParam1.setFile_nm(frontName);
            //reqParam1.setSend_file(frontPhoto);
            reqParam1.setCorpid(session.getCorpId());
            reqParam1.setUserid(session.getUserId());
//            reqParam1.setBankType(salaryAccount.getSalary_card_bank_type());
            reqParam1.setBankType(session.getBankType());
            String[] allkeys = AesUtil.getAesKey(keyCacheService.getAccountSecret());
            reqParam1.setPassword(allkeys[0]);
            reqParam1.setVectorKey(allkeys[1]);
            bankProxyRequestService.uploadIdCardPhotos(reqParam1);
            //上传反面
            UploadIdCardPhotosReqParam reqParam2 = new UploadIdCardPhotosReqParam();
            reqParam2.setFile_date(dateFormat1.format(date));
            reqParam2.setCorpid(session.getCorpId());
            reqParam2.setUserid(session.getUserId());
            reqParam2.setFile_nm(backName);
//            reqParam2.setBankType(salaryAccount.getSalary_card_bank_type());
            reqParam2.setBankType(session.getBankType());

            reqParam2.setPassword(allkeys[0]);
            reqParam2.setVectorKey(allkeys[1]);
            bankProxyRequestService.uploadIdCardPhotos(reqParam2);
        }


        //将身份证正反面名称存入session
        session.setIdCardFrontFileName(frontName);
        session.setIdCardBackFileName(backName);
        sessionService.setSession(session);

        String[] nameAndNumber = new String[2];
        nameAndNumber[0] = frontResponse.getData().getName();
        nameAndNumber[1] = frontResponse.getData().getId();
        return nameAndNumber;
    }

}
