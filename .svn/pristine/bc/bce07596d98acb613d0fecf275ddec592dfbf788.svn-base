package com.tenpay.wxwork.salary.service.admin;

import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.dao.*;
import com.tenpay.wxwork.salary.dto.admin.*;
import com.tenpay.wxwork.salary.model.CardBin;
import com.tenpay.wxwork.salary.model.CorpDepartment;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.UserInfo;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 单笔银行卡信息添加
 */
@Service
public class SingleCardInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SingleCardInfoService.class);

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    @Autowired
    private UserInfoDAO  userInfoDAO ;

    @Autowired
    private KeyCacheService keyCacheService;

    public UserDepartmentResponse findDepartment(String name, String corpId){
        //用户名对应的部门信息
        CorpDepartment deptInfo = userInfoDAO.findDepartmentInfo(corpId, name);
        if(deptInfo== null){
            throw new BizException(BizError.NO_DEPARTMENT_INFO.errorCode(),BizError.NO_DEPARTMENT_INFO.errorMsg());
        }

        return new UserDepartmentResponse(name, deptInfo.getDepartmentId(), deptInfo.getDepartmentName());
    }

    public void saveSingleCard(SingleCardInfoReq req, String corpId){
        //获取用户ID
        UserInfo userInfo = userInfoDAO.queryUserId(corpId, req.getName());
        String userId = userInfo.getUserid();
        //获取银行的联行号
        String bankNum = "";
        FinGateClient finGateClient = new FinGateClient();
        NationalBankNumber nationalBankNumber = new NationalBankNumber();
        CardBin cardBin = finGateClient.queryCardBin(req.getBindCardNumber());
        String bankName = cardBin.getBankSname();
        bankNum = nationalBankNumber.query(bankName);
        //查询该用户是否存在工资条账户
        SalaryAccount act = salaryAccountDAO.getFsalaryCardInfo(corpId, userId);
        String encryCardNo = AesUtil.encryptAccount(req.getBindCardNumber());
        if(act == null){
            //新增工资账户，并录入绑卡信息
            salaryAccountDAO.insertBindCardInfo(corpId,userId,SalaryAccount.State.INIT.toInt(),
                    encryCardNo,bankName, bankNum);
        }else{
            //更新工资账户的绑卡信息
            salaryAccountDAO.updateBindCardInfo(corpId,userId,
                    encryCardNo,bankName, bankNum);
        }

    }



}
