package com.tenpay.wxwork.salary.service.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dao.SalaryCardInfoDAO;
import com.tenpay.wxwork.salary.dto.admin.PageHelperParam;
import com.tenpay.wxwork.salary.dto.admin.SalaryCardInfoResponse;
import com.tenpay.wxwork.salary.dto.admin.SalaryFieldsReq;
import com.tenpay.wxwork.salary.model.CardBin;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SalaryCardInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wg on 2018/11/15
 */
@Service
public class SalaryCardInfoService {

    @Autowired
    private SalaryCardInfoDAO salaryCardInfoDAO;
    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    //查询方法
    public SalaryCardInfoResponse selectSalaryCardInfo(String corpId,PageHelperParam pageHelperParam){

        //分页
        PageHelper.startPage(pageHelperParam.getPageNum(), pageHelperParam.getPageSize());

        NationalBankNumber nb = new NationalBankNumber("");

        List<SalaryCardInfo> salaryCardList = salaryCardInfoDAO.querySalaryCardInfo(corpId,pageHelperParam.getName(),pageHelperParam.getDepartment_id(),pageHelperParam.getBank_sname(),pageHelperParam.getState_ch());
        if (salaryCardList != null && salaryCardList.size() > 0){
            for (int i=0;i<salaryCardList.size();i++){
                String bind_card_number = salaryCardList.get(i).getBindCardNumber();
                if (StringUtils.isNotBlank(bind_card_number)){
                    salaryCardList.get(i).setBindCardNumber(AesUtil.decryptAccount(bind_card_number));
                }
                if (StringUtils.isNotBlank(salaryCardList.get(i).getBindCardBankSname())){
                    salaryCardList.get(i).setBindCardBankSname_ch(nb.queryChName(salaryCardList.get(i).getBindCardBankSname()));
                }
            }
        }
        PageInfo<SalaryCardInfo> salaryCardInfoList = new PageInfo<SalaryCardInfo>(salaryCardList);
        return new SalaryCardInfoResponse(salaryCardInfoList);
    }

    /**
     * 根据企业ID查询员工部门
     * @param corpId
     * @return
     */
    public SalaryCardInfoResponse selectDepartmentName(String corpId){
        List<SalaryFieldsReq> departmentNameList = salaryCardInfoDAO.selectDepartmentNameByCorpId(corpId);
        return new SalaryCardInfoResponse(departmentNameList);
    }

    /**
     * 根据企业ID查询员工发卡银行
     * @param corpId
     * @return
     */
    public SalaryCardInfoResponse selectBankSname(String corpId){
        List<SalaryFieldsReq> bankSnameList = salaryCardInfoDAO.selectBankSnameByCorpId(corpId);
        NationalBankNumber nb = new NationalBankNumber("");
        for (int i = 0;i<bankSnameList.size();i++){
            String bankName = bankSnameList.get(i).getSalary();
            if (bankName != null && !"".equals(bankName)){
                bankSnameList.get(i).setDeduction(nb.queryChName(bankName));
            }
        }
        return new SalaryCardInfoResponse(bankSnameList);
    }


    /**
     * 单个工资卡信息维护修改方法
     * @param salaryCardInfo
     */
    public Boolean updateSalaryCardInfo(SalaryCardInfo salaryCardInfo){
        String bind_card_number = salaryCardInfo.getBindCardNumber();
        if (StringUtils.isNotBlank(bind_card_number)){
            FinGateClient finGateClient = new FinGateClient();
            NationalBankNumber nationalBankNumber = new NationalBankNumber();
            CardBin cardBin = finGateClient.queryCardBin(bind_card_number);
            String bankName = cardBin.getBankSname();
            String fbind_card_bank_number = nationalBankNumber.query(bankName);
            String fbind_card_number = AesUtil.encryptAccount(bind_card_number);
            String fremark = StringUtils.isNotBlank(salaryCardInfo.getRemark())?salaryCardInfo.getRemark():"";
            if (fremark.length() > 255){
                fremark = fremark.substring(0,250);
            }
            List<String> userId_list = salaryAccountDAO.selectSalaryAccountUserId(salaryCardInfo.getCorpid());
            if (userId_list.indexOf(salaryCardInfo.getUserid()) != -1) {
                salaryCardInfoDAO.updateSalaryCardInfo(salaryCardInfo.getCorpid(), salaryCardInfo.getUserid(), fbind_card_number, bankName, fbind_card_bank_number, fremark);
            }else {
                int fstate = SalaryAccount.State.INIT.toInt();
                salaryCardInfoDAO.insertSalaryCardInfo(salaryCardInfo.getCorpid(), salaryCardInfo.getUserid(),fstate, fbind_card_number, bankName, fbind_card_bank_number, fremark);
            }
            return true;
        }else {
            return false;
        }
    }
}
