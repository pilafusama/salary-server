package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.dto.h5.UploadBankCardPhotoRequest;
import com.tenpay.wxwork.salary.dto.h5.UploadBankCardPhotoResponse;
import com.tenpay.wxwork.salary.service.h5.UploadBankCardPhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UploadBankCardPhotoController {

    private static final Logger logger = LoggerFactory.getLogger(UploadBankCardPhotoController.class);

    @Autowired
    private UploadBankCardPhotoService uploadBankCardPhotoService;

    @RequestMapping(value = "/h5/upload_bank_card_photo",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object uploadBankCardPhoto(@CookieValue("ssid") String ssid,
                                      @RequestBody UploadBankCardPhotoRequest request)
    {

        logger.debug("upload bank card photo");
        UploadBankCardPhotoResponse uploadBankCardPhotoResponse = uploadBankCardPhotoService.uploadBankCardPhoto(ssid, request);
        return uploadBankCardPhotoResponse;
    }
}
