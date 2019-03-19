package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.model.CardBin;
import com.tenpay.wxwork.salary.model.Ea02Group;
import com.tenpay.wxwork.salary.presvr.sender.bean.QueryExistCategory2AccountsRes;
import com.tenpay.wxwork.salary.dto.h5.QueryExistCategory2AccountsResponse;
import com.tenpay.wxwork.salary.service.h5.QueryExistCategory2AccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QueryExistCategory2AccountsController {
    private static final Logger logger = LoggerFactory.getLogger(QueryExistCategory2AccountsController.class);

    @Autowired
    QueryExistCategory2AccountsService service;

    @RequestMapping(value = "/h5/query_exist_category2_accounts",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object queryExistCategory2Accounts(@CookieValue("ssid") String ssid)
    {
        logger.debug("query exist category2 accounts");
        /*
        1、参数检查
        2、获取登录态并检查，读取用户信息。
        3、去银行查询已有二类户
        */
        QueryExistCategory2AccountsRes res = service.queryExistCategory2Accounts(ssid);

        //判断是否有账户信息
        if (res != null){
            List<Ea02Group> ea02GroupsList = res.getEa02_group();
            FinGateClient finGateClient = new FinGateClient();
            NationalBankNumber nb = null; // TODO 改为使用注入
            NationalBankNumber nb2 = null;
            if(null != ea02GroupsList && ea02GroupsList.size() !=0 ){
                for (int i = 0; i < ea02GroupsList.size(); i++){
                    //获取一类户,查询银行名称
                    String Rltv_AccNo = ea02GroupsList.get(i).getRltv_AccNo();
                    Rltv_AccNo = Rltv_AccNo.replace(" ", ""); // 银行返回的可能带空格
                    if (Rltv_AccNo != null && !Rltv_AccNo.equals("")){
                        CardBin cardBin = finGateClient.queryCardBin(Rltv_AccNo);
                        String bankName = cardBin.getBankSname();
                        nb = new NationalBankNumber(bankName);
                        String chName = nb.queryChName(bankName);
                        ea02GroupsList.get(i).setBank_name(chName);
                    }else{
                        ea02GroupsList.get(i).setBank_name("");
                    }
                    
                    //获取二类户,查询银行名称
                    String DbCrd_CardNo = ea02GroupsList.get(i).getDbCrd_CardNo();
                    DbCrd_CardNo = DbCrd_CardNo.replace(" ", ""); // 银行返回的可能带空格
                    if (DbCrd_CardNo != null && !DbCrd_CardNo.equals("")){
                        CardBin cardBin2 = finGateClient.queryCardBin(DbCrd_CardNo);
                        String bankName2 = cardBin2.getBankSname();
                        nb2 = new NationalBankNumber(bankName2);
                        String chName2 = nb2.queryChName(bankName2);
                        ea02GroupsList.get(i).setBank_name_two(chName2);
                    }else{
                        ea02GroupsList.get(i).setBank_name_two("");
                    }
                }
            }
        }

        return new QueryExistCategory2AccountsResponse(res.getVld_Rcrd_Cnt(),res.getEbnk_Cst_Pltfrm_ID(),res.getEa02_group());
    }
}
