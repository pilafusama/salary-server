package com.tenpay.wxwork.salary.provider.h5;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpay.wxwork.salary.dto.h5.ExampleReqParam;
import com.tenpay.wxwork.salary.dto.h5.ExampleResponse;
import com.tenpay.wxwork.salary.service.h5.ExampleService;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/19
 */
//@CrossOrigin
@RestController
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @PostMapping(value = "/testMethod", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExampleResponse testMethod(@CookieValue("ssid") String ssid,
                             @RequestBody ExampleReqParam request) {
        String myMoney = exampleService.testMethod(ssid, request);

        return new ExampleResponse(myMoney);
    }

    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExampleResponse test(@CookieValue("ssid") String ssid,
                                      @RequestBody ExampleReqParam request) {
//        String myMoney = exampleService.testMethod(ssid, request);
        
        return new ExampleResponse("111");
    }

    @PostMapping(value = "/testDownload", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void testDownload(HttpServletResponse response) throws Exception {

        //new ExcelDownloader(this.exampleService, "getTestData", Example.class).setArgs(new Example()).downloadByExplorer();
    }
}
