package com.tenpay.wxwork.salary.provider.h5;


import com.tenpay.wxwork.salary.dto.h5.GestCheckReqParam;
import com.tenpay.wxwork.salary.dto.h5.GestCheckResponse;
import com.tenpay.wxwork.salary.service.h5.GestureCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/19
 */

@RestController
public class GestCheckController {

    @Autowired
    private GestureCheckService gestureCheckService;

    private static final Logger logger = LoggerFactory.getLogger(GestCheckController.class);

    @PostMapping(value = "/h5/update_gesture_password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object updategestrue(@CookieValue("ssid") String ssid,
                                      @RequestBody GestCheckReqParam request) {

        return gestureCheckService.updateFgesture(ssid,request);
    }


    @PostMapping(value = "/h5/set_gesture_password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object setfgesture(@CookieValue("ssid") String ssid,
            @RequestBody GestCheckReqParam request) {

        return gestureCheckService.setFgesturePassword(ssid,request);
    }

    @PostMapping(value = "/h5/check_gesture_password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object checkfgesture(@CookieValue("ssid") String ssid,
                                 @RequestBody GestCheckReqParam request) {

        return gestureCheckService.checkFgesture(ssid,request);
    }


}
