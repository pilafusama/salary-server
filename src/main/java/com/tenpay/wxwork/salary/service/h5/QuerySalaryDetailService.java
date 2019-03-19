package com.tenpay.wxwork.salary.service.h5;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.dao.SalaryDetailDAO;
import com.tenpay.wxwork.salary.dto.admin.*;
import com.tenpay.wxwork.salary.dto.h5.SalaryCountResponse;
import com.tenpay.wxwork.salary.model.SalaryCount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.admin.DownLoadSalaryService;
import com.tenpay.wxwork.salary.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuerySalaryDetailService {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SalaryDetailDAO salaryDetailDAO;
    @Autowired
    private KeyCacheService keyCacheService;
    @Autowired
    private DownLoadSalaryService downLoadSalaryService;

    public SalaryCountResponse querySalaryCount(String ssid){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = sessionInfo.getCorpId();
        String userId = sessionInfo.getUserId();
        List<SalaryCount> list = salaryDetailDAO.querySalaryCount(
                AesUtil.encryptSalary(corpId,corpId),
                AesUtil.encryptSalary(userId,corpId));
        SalaryCountResponse res = new SalaryCountResponse(list);
        return res;
    }

    public UploadPayrollTransInt querySalaryDetail(String ssid, String mouth){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = sessionInfo.getCorpId();
        String userId = sessionInfo.getUserId();
        return salaryDetailDAO.querySalaryDetail(
                mouth,
                AesUtil.encryptSalary(corpId,corpId),
                AesUtil.encryptSalary(userId,corpId));
    }

    //TODO UPDATE 以下为适应企业过渡版本
    public SalaryCountResponse querySalaryCountNo(String ssid){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = sessionInfo.getCorpId();
        String userId = sessionInfo.getUserId();
        List<SalaryCount> list = salaryDetailDAO.querySalaryDetailCount(
                AesUtil.encryptSalary(corpId,corpId),
                AesUtil.encryptSalary(userId,corpId));
        SalaryCountResponse res = new SalaryCountResponse(list);
        return res;
    }

    //TODO UPDATE 以下为适应企业过渡版本
    public SalaryDetailTranIntInfo querySalaryDetailNo(String ssid, String mouth){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = sessionInfo.getCorpId();
        String userId = sessionInfo.getUserId();
        return salaryDetailDAO.querySalaryDetailInfo(
                mouth,
                AesUtil.encryptSalary(corpId,corpId),
                AesUtil.encryptSalary(userId,corpId));
    }

    //TODO UPDATE 以下为适应企业过渡版本
    public void updateSalaryDetailState(String ssid, String mouth){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = sessionInfo.getCorpId();
        String userId = sessionInfo.getUserId();
        salaryDetailDAO.updateSalaryDetailState(SalaryDetailTranIntInfo.Fsure_state.YES.name(), mouth ,
                AesUtil.encryptSalary(corpId,corpId),
                AesUtil.encryptSalary(userId,corpId));
    }

    //TODO UPDATE 以下为适应企业过渡版本
    public SalaryConfirmResponse querySalaryConfirmState(String ssid, String mouth){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = sessionInfo.getCorpId();
        List<SalaryConfirm> list = salaryDetailDAO.querySalaryConfirm(AesUtil.encryptSalary(corpId,corpId), mouth);
        for (SalaryConfirm salary: list) {
            salary.setUserid(AesUtil.decryptSalary(salary.getUserid(),corpId));
            salary.setUsername(AesUtil.decryptSalary(salary.getUsername(),corpId));
        }
        SalaryConfirmResponse res = new SalaryConfirmResponse(list);
        return res;
    }

    //适配版 查询工资详情
    public UserSalaryDetailResponse queryUserSalaryDetail(String ssid, String mouth,int batch_no){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = sessionInfo.getCorpId();
        String userId = sessionInfo.getUserId();
        String tablename = downLoadSalaryService.getDetailTableName(sessionInfo.getCorpId());
        UserSalaryDetail userSalary = salaryDetailDAO.queryUserSalary(mouth ,AesUtil.encryptSalary(corpId,corpId),
                AesUtil.encryptSalary(userId,corpId),batch_no );

        List<UserSalaryDetail> list  = salaryDetailDAO.queryUserSalaryDetail( mouth,tablename,userSalary.getSalary_overview_id());
        List<UserSalaryDetail> salarylist = new ArrayList<>();
        List<UserSalaryDetail> deductionlist = new ArrayList<>();
        for (UserSalaryDetail userSalaryDetail: list) {
            if(DefinedSalaryDetailInfo.Category.SALARY.name().equals(userSalaryDetail.getCategory())){
                salarylist.add(userSalaryDetail);
            }else if(DefinedSalaryDetailInfo.Category.DEDUCTION.name().equals(userSalaryDetail.getCategory())){
                deductionlist.add(userSalaryDetail);
            }else {

            }
        }
        return new UserSalaryDetailResponse(salarylist,deductionlist,userSalary.getIs_read());
    }

    //适配版 查询近三个月工资
    public SalaryCountResponse queryUserSalaryCount(String ssid){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = sessionInfo.getCorpId();
        String userId = sessionInfo.getUserId();
        String tablename = Constant.TABLE_SALARY_OVERVIEW;
        String data1 = DateUtil.getMonth(0);
        String data2 = DateUtil.getMonth(-1);
        String data3 = DateUtil.getMonth(-2);
        if(salaryDetailDAO.queryExistTable(tablename + data1) < 1){
            data1 = "";
        }
        if(salaryDetailDAO.queryExistTable(tablename + data2) < 1){
            data2 = "";
        }
        if(salaryDetailDAO.queryExistTable(tablename + data3) < 1){
            data3 = "";
        }

        List<SalaryCount> list = salaryDetailDAO.queryUserSalaryCount(
                AesUtil.encryptSalary(corpId,corpId),
                AesUtil.encryptSalary(userId,corpId),
                data1, data2, data3);
        SalaryCountResponse res = new SalaryCountResponse(list);
        return res;
    }

    //T适配版  薪资确认
    public void updateUserSalaryDetailState(String ssid, String mouth,int batch_no){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = sessionInfo.getCorpId();
        String userId = sessionInfo.getUserId();
        salaryDetailDAO.updateUserSalaryDetailState(SalaryDetailTranIntInfo.Fsure_state.YES.name(), mouth ,batch_no,
                AesUtil.encryptSalary(corpId,corpId),
                AesUtil.encryptSalary(userId,corpId));
    }

    //适配版  查询薪资确认状态
    public SalaryConfirmResponse queryUserSalaryConfirmState(String ssid, String mouth,int pageNum,int pageSize){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String corpId = sessionInfo.getCorpId();
        PageHelper.startPage(pageNum,pageSize);
        List<SalaryConfirm> list = salaryDetailDAO.queryUserSalaryConfirm(AesUtil.encryptSalary(corpId,corpId), mouth);
        for (SalaryConfirm salary: list) {
            salary.setUserid(AesUtil.decryptSalary(salary.getUserid(),corpId));
            salary.setUsername(AesUtil.decryptSalary(salary.getUsername(),corpId));
        }
        PageInfo<SalaryConfirm> pageInfo = new PageInfo<>(list);
        SalaryConfirmResponse res = new SalaryConfirmResponse(pageInfo);
        return res;
    }

}
