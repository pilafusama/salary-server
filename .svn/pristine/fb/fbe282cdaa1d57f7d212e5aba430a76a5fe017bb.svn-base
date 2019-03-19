/**
 * 深海 AI 实验室 client ，提供活体检测、身份证 OCR 等接口
 *
 */
package com.tenpay.wxwork.salary.client;

import com.tenpay.wxwork.salary.dto.deepsea.OcrIdCardBackResponse;
import com.tenpay.wxwork.salary.dto.deepsea.OcrIdCardFrontResponse;
import com.tenpay.wxwork.salary.dto.deepsea.OcrBankCardResponse;
import com.tenpay.wxwork.salary.dto.deepsea.LivenessDetectionNumberResponse;
import com.tenpay.wxwork.salary.dto.deepsea.LivenessDetectionResponse;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import feign.Param;
import feign.Logger;
import feign.form.FormEncoder;
import feign.jackson.JacksonEncoder;

// for okhttp
import feign.Feign;
import feign.Client;
import okhttp3.OkHttpClient;
import java.net.InetSocketAddress;
import java.net.Proxy;

@FeignClient(name = "DeepseaLabClient",
             url = "${deepsea.baseurl}"
             , configuration = DeepseaLabClient.MultipartEncoderConfig.class
             )
public interface DeepseaLabClient
{

    @RequestMapping(method = RequestMethod.POST,
                    value = "/face_recognition_mandarin_gateway/ocr/idcard",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public OcrIdCardFrontResponse ocrIdCardFrontPhoto(@RequestBody Map<String, ?> formParams);

    @RequestMapping(method = RequestMethod.POST,
                    value = "/face_recognition_mandarin_gateway/ocr/idcard",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public OcrIdCardBackResponse ocrIdCardBackPhoto(@RequestBody Map<String, ?> formParams);

    @RequestMapping(method = RequestMethod.POST,
                    value = "/face_recognition_silent_gateway/ocr/bankcard",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public OcrBankCardResponse ocrBankCardPhoto(@RequestBody Map<String, ?> formParams);

    @RequestMapping(method = RequestMethod.POST,
                    value = "/face_recognition_mandarin_gateway/random_number",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LivenessDetectionNumberResponse getLivenessDetectionNumber(@RequestBody Map<String, ?> formParams);

    @RequestMapping(method = RequestMethod.POST,
                    value = "/face_recognition_mandarin_gateway/face_verification",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LivenessDetectionResponse doLivenessDetection(@RequestBody Map<String, ?> formParams);

    public class MultipartEncoderConfig {

        // // 代理设置，方便本地开发
        // @Bean
        // public Client feignClient() {
        //     System.out.println("--- create feign client using proxy");
        //     Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("web-proxy.tencent.com", 8080));
        //     OkHttpClient okHttpClient = new OkHttpClient.Builder().proxy(proxy).build();
        //     return new feign.okhttp.OkHttpClient(okHttpClient);
        // }

        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }

        @Bean
        public Encoder feignEncoder() {
            return new FormEncoder();
        }
    }

}
