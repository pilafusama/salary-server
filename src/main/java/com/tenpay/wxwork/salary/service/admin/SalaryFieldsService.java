package com.tenpay.wxwork.salary.service.admin;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tenpay.wxwork.salary.dao.SalaryFieldsDAO;
import com.tenpay.wxwork.salary.dto.admin.SalaryFieldsParam;
import com.tenpay.wxwork.salary.dto.admin.SalaryFieldsReq;
import com.tenpay.wxwork.salary.dto.admin.SalaryFieldsResponse;
import com.tenpay.wxwork.salary.model.SalaryFields;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryFieldsService {

    @Autowired
    private SalaryFieldsDAO salaryFieldsDAO;

    /**
      这是第一次配置发薪表模板or修改模板调用操作
     */
    public void InsertSalaryFields(String corpid, SalaryFieldsParam salaryFieldsParam){

        List<SalaryFieldsReq> config_Shouldlist = new ArrayList<>();
        List<SalaryFieldsReq> template_Shouldlist = new ArrayList<>();

        for (int i = 0;i < salaryFieldsParam.getShouldList().size();i++){
            SalaryFieldsReq salaryFieldsReq =  salaryFieldsParam.getShouldList().get(i);
            if (SalaryFields.TemplateSelect.YES.toString().equals(salaryFieldsReq.getIsSelect())){
                salaryFieldsReq.setIsSelect(null);
                config_Shouldlist.add(salaryFieldsReq);
                template_Shouldlist.add(salaryFieldsReq);
            }else {
                salaryFieldsReq.setIsSelect(null);
                template_Shouldlist.add(salaryFieldsReq);
            }
        }

        List<SalaryFieldsReq> config_Deductlist = new ArrayList<>();
        List<SalaryFieldsReq> template_Deductlist = new ArrayList<>();

        for (int i = 0;i < salaryFieldsParam.getDeductList().size();i++){
            SalaryFieldsReq salaryFieldsReq =  salaryFieldsParam.getDeductList().get(i);
            if (SalaryFields.TemplateSelect.YES.toString().equals(salaryFieldsReq.getIsSelect())){
                salaryFieldsReq.setIsSelect(null);
                config_Deductlist.add(salaryFieldsReq);
                template_Deductlist.add(salaryFieldsReq);
            }else {
                salaryFieldsReq.setIsSelect(null);
                template_Deductlist.add(salaryFieldsReq);
            }
        }

        String salary_template_fields = JSONArray.toJSONString(template_Shouldlist);
        String deduction_template_fields = JSONArray.toJSONString(template_Deductlist);

        String salary_fields = JSONArray.toJSONString(config_Shouldlist);
        String deduction_fields = JSONArray.toJSONString(config_Deductlist);

        String remake = "";
        if (StringUtils.isNotBlank(salaryFieldsParam.getRemark()) && SalaryFields.TemplateSelect.YES.toString().equals(salaryFieldsParam.getRemark_valid())){
            remake = salaryFieldsParam.getRemark();
        }

        SalaryFields template_salaryFields = salaryFieldsDAO.querySalaryFields(corpid,SalaryFields.TemplateSelect.YES.toString());
        SalaryFields config_salaryFields = salaryFieldsDAO.querySalaryFields(corpid,SalaryFields.TemplateSelect.NO.toString());

        if (template_salaryFields == null )
        {
            salaryFieldsDAO.insertSalaryFields(corpid,salary_fields, deduction_fields,remake,SalaryFields.TemplateSelect.NO.toString());
            salaryFieldsDAO.insertSalaryFields(corpid,salary_template_fields, deduction_template_fields,salaryFieldsParam.getRemark(),SalaryFields.TemplateSelect.YES.toString());
        }
        else{
            if (config_salaryFields == null){
                salaryFieldsDAO.insertSalaryFields(corpid,salary_fields, deduction_fields,remake,SalaryFields.TemplateSelect.NO.toString());
                //salaryFieldsDAO.updateByCorpid(corpid,salary_template_fields, deduction_template_fields,salaryFieldsParam.getRemark(),SalaryFields.TemplateSelect.YES.toString());
            }else {
                salaryFieldsDAO.updateByCorpid(corpid,salary_fields, deduction_fields,remake,SalaryFields.TemplateSelect.NO.toString());
            }
            salaryFieldsDAO.updateByCorpid(corpid,salary_template_fields, deduction_template_fields,salaryFieldsParam.getRemark(),SalaryFields.TemplateSelect.YES.toString());

        }

    }

    /**
     这是查询发薪表模板
     */
    public SalaryFieldsResponse SelectSalaryFields(String corpid){

        SalaryFields template_salaryFields = salaryFieldsDAO.querySalaryFields(corpid,SalaryFields.TemplateSelect.YES.toString());
        SalaryFields config_salaryFields = salaryFieldsDAO.querySalaryFields(corpid,SalaryFields.TemplateSelect.NO.toString());

        if (template_salaryFields == null)
        {
            return new SalaryFieldsResponse(new ArrayList<>(),new ArrayList<>(),"",SalaryFields.TemplateSelect.NO.toString(),SalaryFields.TemplateSelect.NO.toString());
        }
        else
        {
            List<SalaryFieldsReq> template_shouldList = JSONObject.parseArray(template_salaryFields.getSalaryFields(),SalaryFieldsReq.class);
            List<SalaryFieldsReq> template_deductList = JSONObject.parseArray(template_salaryFields.getDeductionFields(),SalaryFieldsReq.class);
            String remark_valid = SalaryFields.TemplateSelect.NO.toString();

            if (config_salaryFields == null){
                setValue(template_shouldList,SalaryFields.TemplateSelect.NO.toString());
                setValue(template_deductList,SalaryFields.TemplateSelect.NO.toString());

            }else {
                List<SalaryFieldsReq> config_shouldList = JSONObject.parseArray(config_salaryFields.getSalaryFields(),SalaryFieldsReq.class);
                List<SalaryFieldsReq> config_deductList = JSONObject.parseArray(config_salaryFields.getDeductionFields(),SalaryFieldsReq.class);

                List<String> config_slist = new ArrayList<>();
                List<String> config_dlist = new ArrayList<>();

                for (SalaryFieldsReq s : config_shouldList){
                    config_slist.add(s.getSalary());
                }

                for (SalaryFieldsReq s : config_deductList){
                    config_dlist.add(s.getDeduction());
                }

                for (int i =0;i<template_shouldList.size();i++){
                    if (config_slist.contains(template_shouldList.get(i).getSalary())){
                        template_shouldList.get(i).setIsSelect(SalaryFields.TemplateSelect.YES.toString());
                    }else {
                        template_shouldList.get(i).setIsSelect(SalaryFields.TemplateSelect.NO.toString());
                    }
                }

                for (int i =0;i<template_deductList.size();i++){
                    if (config_dlist.contains(template_deductList.get(i).getDeduction())){
                        template_deductList.get(i).setIsSelect(SalaryFields.TemplateSelect.YES.toString());
                    }else {
                        template_deductList.get(i).setIsSelect(SalaryFields.TemplateSelect.NO.toString());
                    }
                }

                if (StringUtils.isNotBlank(config_salaryFields.getRemark())){
                    remark_valid = SalaryFields.TemplateSelect.YES.toString();
                }

            }
            return new SalaryFieldsResponse(template_shouldList,template_deductList,template_salaryFields.getRemark(),SalaryFields.TemplateSelect.YES.toString(),remark_valid);

        }

    }

    public void setValue(List<SalaryFieldsReq> list,String val){
        for (SalaryFieldsReq s : list){
            s.setIsSelect(val);
        }
    }


    /**
     * 查询企业是否配置发薪模板
     * @param corpid
     * @return
     */
    public SalaryFieldsResponse SelectIsCOnfigTemplate(String corpid){
        SalaryFields config_salaryFields = salaryFieldsDAO.querySalaryFields(corpid,SalaryFields.TemplateSelect.NO.toString());
        if (config_salaryFields != null){
            return new SalaryFieldsResponse("YES");
        }else {
            return new SalaryFieldsResponse("NO");
        }
    }

    /**
     * 根据templateSelect查询模板
     * @param corpid
     * @param templateSelect
     * @return
     */
    public SalaryFields SelectSalaryFieldsTemplate(String corpid,String templateSelect) {
        SalaryFields salaryFields = salaryFieldsDAO.querySalaryFields(corpid, templateSelect);
        return salaryFields;
    }
}
