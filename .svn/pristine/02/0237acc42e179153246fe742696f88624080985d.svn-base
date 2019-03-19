package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.dto.h5.*;
import com.tenpay.wxwork.salary.util.VideoCompressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenpay.wxwork.salary.service.IdCardPhotosService;
import com.tenpay.wxwork.salary.service.LivenessDetectionService;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

@RestController
@RequestMapping("/h5")
public class UploadIdCardPhotosController
{
    @Autowired
    private IdCardPhotosService idCardPhotosService;

    @Autowired
    private LivenessDetectionService livenessDetectionService;

    private static final Logger logger = LoggerFactory.getLogger(UploadIdCardPhotosController.class);

    @RequestMapping(value = "/verify_id_card_photo",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object verifyIdCardPhoto(@CookieValue("ssid") String ssid,
                                     @RequestBody VerifyIdCardRequest request)
    {
        logger.debug("verify id card photo");
        try{
            idCardPhotosService.verifyUpload(ssid, request.getIdCardPhoto(),request.isFrontPhoto());
        }catch (BizException e){
            return new GestCheckResponse("0" , e.getErrMsg(), "1");
        }

        return new GestCheckResponse("0" , "OK", "0");
    }

    @RequestMapping(value = "/upload_id_card_photos",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object uploadIdCardPhotos(@CookieValue("ssid") String ssid,
                                     @RequestBody UploadIdCardPhotosRequest request)
    {
        logger.debug("upload id card photos");
        String[] nameAndNumber = null;

        try{
             nameAndNumber = idCardPhotosService.processUpload(ssid, request.getIdCardFrontPhoto(),
                    request.getIdCardBackPhoto());
        }catch(BizException e){
            //配合前端控制跳转
            if(e.getRetCode()== BizError.OCR_FRONT_FAIL.errorCode()){
                return new GestCheckResponse("0" , e.getErrMsg(), "1");
            }
            if(e.getRetCode()== BizError.OCR_BACK_FAIL.errorCode()){
                return new GestCheckResponse("0" , e.getErrMsg(), "2");
            }
            throw e;
        }


        return new UploadIdCardPhotosResponse(nameAndNumber[0], nameAndNumber[1]);
    }

    @RequestMapping(value = "/get_liveness_detection_number",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getLivenessDetectionNumber(@CookieValue("ssid") String ssid)
    {
        logger.debug("get liveness detection number");

        String number = livenessDetectionService.getLivenessDetectionNumber(ssid);
        return new GetLivenessDetectionNumberResponse(number);
    }

    @RequestMapping(value = "/upload_liveness_detection_video",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object uploadLivenessDetectionVideo(@CookieValue("ssid") String ssid,
                                                         @RequestBody UploadLivenessDetectionVideoRequest request)
    {
        logger.debug("upload liveness detection video");
        String faceRecognitionPhotoStr = "";
        try{
            String base64String = request.getVideo();
            if(request.getCompressFlag()){
                base64String = VideoCompressUtil.getCompressedBase64String(request.getVideo());
            }

            faceRecognitionPhotoStr = livenessDetectionService.doLivenessDetection(ssid, request.getNumber(), base64String, request.getType());
        }catch(BizException e){
            //配合前端控制跳转
            return new LivenessPhotoResponse("0" , e.getErrMsg(), "1","");
        }
        return new LivenessPhotoResponse("0" , "OK", "0", faceRecognitionPhotoStr);

    }


}