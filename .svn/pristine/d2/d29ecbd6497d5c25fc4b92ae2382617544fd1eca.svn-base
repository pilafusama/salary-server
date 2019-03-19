package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.dto.h5.UserSalaryCardResponse;
import com.tenpay.wxwork.salary.service.h5.GetUserSalaryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserSalaryCardController {

    @Autowired
    private GetUserSalaryCardService getUserSalaryCardService;

    @PostMapping(value = "/h5/get_user_salary_card",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getUserSalaryCard(@CookieValue("ssid") String ssid){
        UserSalaryCardResponse userSalaryCard = getUserSalaryCardService.getUserSalaryCard(ssid);
        return userSalaryCard;
    }
}
