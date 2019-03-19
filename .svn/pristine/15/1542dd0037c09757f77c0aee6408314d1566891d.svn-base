package com.tenpay.wxwork.salary.service;

import com.tenpay.wxwork.salary.client.DeepseaLabClient;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.dto.deepsea.LivenessDetectionNumberRequest;
import com.tenpay.wxwork.salary.dto.deepsea.LivenessDetectionNumberResponse;
import com.tenpay.wxwork.salary.dto.deepsea.LivenessDetectionRequest;
import com.tenpay.wxwork.salary.dto.deepsea.LivenessDetectionResponse;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.h5.VerifyUserService;
import com.tenpay.wxwork.salary.util.BinUtil;
import com.tenpay.wxwork.salary.util.MapConvert;
import com.tenpay.wxwork.salary.config.Range;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.AesUtil;

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

@Service()
public class LivenessDetectionService {

    @Autowired
    private DeepseaLabClient deepseaLabClient;

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private KeyCacheService keyCacheService;

    @Autowired
    private VerifyUserService verifyUserService;

    private static final Logger logger = LoggerFactory.getLogger(LivenessDetectionService.class);

    /**
     * 获取活体检测随机数
     *
     */
    public String getLivenessDetectionNumber(String ssid)
    {
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        LivenessDetectionNumberResponse response = deepseaLabClient.getLivenessDetectionNumber(
            MapConvert.beanToMapViaJackson(new LivenessDetectionNumberRequest()));
        logger.info("response of liveness detection number: " + response);
        if (0 != response.getCode()) {
            logger.error("failed to get liveness detection number, code: {}, message: {}",
                         response.getCode(), response.getMessage());
            throw new BizException(BizError.LIVENESS_DETECTION_NUMBER_FAIL.errorCode(),
                                   BizError.LIVENESS_DETECTION_NUMBER_FAIL.errorMsg());
        }

        session.setLivenessDetectionNumber(response.getNumber());
        session.setLivenessDetectionToken(response.getToken());
        sessionService.setSession(session);

        return response.getNumber();
    }

    /**
     * 活体检测
     *
     */
    public String doLivenessDetection(String ssid, String number, String video,Boolean type)
    {
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        if (!number.equals(session.getLivenessDetectionNumber())) {
            logger.error("invalid liveness detection number: {}, expected: {}", number, session.getLivenessDetectionNumber());
            throw new BizException(BizError.INVALID_LIVENESS_DETECTION_NUMBER.errorCode(),
                                   BizError.INVALID_LIVENESS_DETECTION_NUMBER.errorMsg());
        }

        byte[] videoBytes = Base64.getDecoder().decode(video);
        logger.debug("video size in bytes: " + videoBytes.length);
        if (videoBytes.length > Range.VIDEO_MAX_SIZE.toInt()) {
            throw new BizException(BizError.PARAM_TOO_LONG.errorCode(), BizError.PARAM_TOO_LONG.errorMsg() + "video");
        }

        SalaryAccount salaryAccount = salaryAccountDAO.querySalaryAccountByUserId(session.getCorpId(), session.getUserId());
        LivenessDetectionRequest request = new LivenessDetectionRequest(session.getLivenessDetectionToken(),
                                                                        AesUtil.decrypt(salaryAccount.getCre_name(), keyCacheService.getAccountSecret()),
                                                                        AesUtil.decrypt(salaryAccount.getCre_id(), keyCacheService.getAccountSecret()),
                                                                        videoBytes);

        LivenessDetectionResponse response = deepseaLabClient.doLivenessDetection(MapConvert.beanToMapViaJackson(request));
        logger.debug("liveness detection response: " + response);

        //返回的错误码为{"code":12003,"message":"ERR_LIVE_MOUTH_FAILED"}
        if(response.getCode() == Constant.ERR_MOUTH_CODE){
            throw new BizException(BizError.ERR_MOUTH_FAIL.errorCode(), BizError.ERR_MOUTH_FAIL.errorMsg());
        }

        if (0 != response.getCode() ||
            !response.getStatus().equals("1") ||
                response.getReturn_photo() == null ||
                response.getReturn_photo().equals("0")) {
            logger.error("failed to do liveness detection, code: {}, status: {}, message: {}",
                         response.getCode(), response.getStatus(), response.getMessage());
            throw new BizException(BizError.LIVENESS_DETECTION_FAIL.errorCode(), BizError.LIVENESS_DETECTION_FAIL.errorMsg());
        }

        byte[] faceRecognitionPhoto = response.getDecryptedReturnPhoto(request.getNonceBytes());
        logger.debug("face recognition photo size of {} in bytes: {}", session.getUserId(), faceRecognitionPhoto.length);
        String faceRecognitionPhotoStr = Base64.getEncoder().encodeToString(faceRecognitionPhoto);
        if(!type){
            salaryAccountDAO.updateLivenessDetection(session.getCorpId(), session.getUserId(),
                    SalaryAccount.State.CRE_RECOGNIZED.toInt(),
                    SalaryAccount.State.LIVENESS_DETECTED.toInt(),
                    number,
                    AesUtil.encrypt(video, keyCacheService.getAccountSecret()),
                    AesUtil.encrypt(faceRecognitionPhotoStr, keyCacheService.getAccountSecret()));

        }

        return faceRecognitionPhotoStr;
    }

}
