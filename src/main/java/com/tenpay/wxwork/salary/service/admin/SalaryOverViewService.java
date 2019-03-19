package com.tenpay.wxwork.salary.service.admin;

import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.dao.SalaryOverViewDAO;
import com.tenpay.wxwork.salary.dto.admin.ViewSalaryAllDetail;
import com.tenpay.wxwork.salary.util.NumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryOverViewService {

    @Autowired
    private SalaryOverViewDAO salaryOverViewDAO;

    /**
     * 查询企业发薪工资总览的信息
     * @param corpId
     * @param month
     * @return
     */
    public List<ViewSalaryAllDetail> viewSalaryAllDetailForList(String corpId,String month){
        List<ViewSalaryAllDetail> viewSalaryAllDetails = salaryOverViewDAO.viewSalaryAllDetailForList(AesUtil.encryptSalary(corpId,corpId), month);
        if (viewSalaryAllDetails != null && viewSalaryAllDetails.size() > 0){
            for (int i = 0;i < viewSalaryAllDetails.size(); i++){
                viewSalaryAllDetails.get(i).setSalaryAll(NumUtil.fenToyuan(viewSalaryAllDetails.get(i).getSalaryAll()));
                viewSalaryAllDetails.get(i).setSalarySum(NumUtil.fenToyuan(viewSalaryAllDetails.get(i).getSalarySum()));
            }
        }
        return viewSalaryAllDetails;
    }

    /**
     * 删除企业发薪总览信息
     * @param corpId
     * @param month
     * @param batch_no
     * @return
     */
    public String updateSalaryOverViewForBatchno(String corpId,String month,String batch_no){
        int num = salaryOverViewDAO.updateSalaryOverViewForBatchno(AesUtil.encryptSalary(corpId,corpId), month, batch_no);
        if (num == 0){
            return "1";
        }else {
            return "0";
        }
    }

    /**
     * 获取企业本月份最后一次发薪的batch_no
     * @param corpId
     * @param month
     * @return
     */
    public int queryCorpMaxBatchno(String corpId,String month){
        corpId = AesUtil.encryptSalary(corpId, corpId);
        int batchno = salaryOverViewDAO.queryCorpMaxBatchno(corpId, month);
        return batchno;
    }
}
