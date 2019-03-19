package com.tenpay.wxwork.salary.service.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tenpay.wxwork.salary.client.ApprovalClient;
import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.common.utils.CorpConfKey;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.dao.*;
import com.tenpay.wxwork.salary.dao.CorpAuthenDAO;
import com.tenpay.wxwork.salary.dao.MsgNotifyDAO;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dao.SalaryDetailDAO;
import com.tenpay.wxwork.salary.dao.SalaryOverViewDAO;
import com.tenpay.wxwork.salary.dto.Flow1003DetailReq;
import com.tenpay.wxwork.salary.dto.admin.*;
import com.tenpay.wxwork.salary.info.ApporvalType;
import com.tenpay.wxwork.salary.model.*;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.util.DateUtil;
import com.tenpay.wxwork.salary.util.NumUtil;
import com.tenpay.wxwork.salary.util.download.ExcelUtil;
import com.tenpay.wxwork.wxworklib.client.WxworkHttpClient;
import com.tenpay.wxwork.wxworklib.service.WxAccessTokenService;
import com.tenpay.wxwork.wxworklib.utils.UrlGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hxyh on 2018/8/22.
 */
@Service
public class DownLoadSalaryService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DownLoadSalaryService.class);

    @Autowired
    private SalaryDetailDAO salaryDetailDAO;

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    @Autowired
    private KeyCacheService keyCacheService;

    @Autowired
    private CorpAuthenDAO corpAuthenDAO;

    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    @Autowired
    private WxworkHttpClient wxworkHttpClient;

    @Autowired
    private MsgNotifyDAO msgNotifyDAO;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private SalaryOverViewDAO salaryOverViewDAO;

    @Autowired
    SalaryFieldsDAO salaryFieldsDAO;

    @Autowired
    private SalaryCorpConfDAO salaryCorpConfDAO;

    @Autowired
    private ApprovalClient approvalClient;

    public List<DownLoadSalaryReq> exportExcel07(DownLoadSalaryReq downLoadSalaryReq) {
        List<DownLoadSalaryReq> list = salaryAccountDAO.querySalary(downLoadSalaryReq);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getF_bankmark().equals(SalaryAccount.CardBanksRelation.SAME_BANK.toString())) {
                    list.get(i).setF_bankmark("0");
                } else {
                    list.get(i).setF_bankmark("1");
                }
                list.get(i).setFcre_name(AesUtil.decryptAccount(list.get(i).getFcre_name()));
            }
        }
        return list;
    }

    /**
     * 下载发薪明细
     *
     * @param response
     * @param downLoadSalaryReq
     */
    public void exportCsv(HttpServletResponse response, DownLoadSalaryReq downLoadSalaryReq) {
        String corpid = downLoadSalaryReq.getF_corpId();
        downLoadSalaryReq.setF_corpId(AesUtil.encryptSalary(corpid, corpid));
        List<DownLoadSalaryReq> list = salaryOverViewDAO.querySalaryOverViewByCorp(downLoadSalaryReq);
        //导出txt文件
        response.setContentType("text/plain");
        String fileName = "xxx";
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".txt");
        BufferedOutputStream buff = null;
        StringBuffer write = new StringBuffer();
        String enter = "\r\n";
        ServletOutputStream outSTr = null;


        try {
            outSTr = response.getOutputStream(); // 建立
            buff = new BufferedOutputStream(outSTr);
            //设置表头
            write.append(Constant.TITLE_F_NO);
            write.append(Constant.TITLE_F_SALARY_CARD_NUMBER);
            write.append(Constant.TITLE_F_CRE_NAME);
            write.append(Constant.TITLE_F_MOMENY);
            write.append(Constant.TITLE_F_BANKMARK);
            write.append(Constant.TITLE_F_BANK_TYPE);
            write.append(Constant.TITLE_F_NUM);
            write.append(Constant.TITLE_F_MARK);
            write.append(Constant.TITLE_F_REMARK);
            write.append(Constant.TITLE_F_ENTER);
            //把内容写入文件
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    write.append((i + 1) + "    |");

                    String salary_card_number = list.get(i).getFsalary_card_number();
                    if (StringUtils.isEmpty(salary_card_number) || salary_card_number == null) {
                        String userid = AesUtil.decryptSalary(list.get(i).getF_userid(), corpid);
                        SalaryAccount salaryAccount = salaryAccountDAO.queryByUserIdAndCorpid(corpid, userid);
                        write.append(AesUtil.decryptAccount(salaryAccount.getBind_card_number()) + " | ");
                    } else {
                        write.append(AesUtil.decryptSalary(list.get(i).getFsalary_card_number(), corpid) + " | ");
                    }
                    write.append(AesUtil.decryptSalary(list.get(i).getFcre_name(), corpid) + "   | ");
                    write.append(NumUtil.fenToyuan(list.get(i).getF_momeny()) + "   | ");
                    if (list.get(i).getF_bankmark().equals(SalaryAccount.CardBanksRelation.SAME_BANK.toString())) {
                        write.append("0        | ");
                    } else {
                        write.append("1        | ");
                    }
                    String bankno = list.get(i).getF_bankno();
                    if (StringUtils.isEmpty(bankno) || bankno == null) {
                        String userid = AesUtil.decryptSalary(list.get(i).getF_userid(), corpid);
                        SalaryAccount salaryAccount = salaryAccountDAO.queryByUserIdAndCorpid(corpid, userid);
                        write.append(salaryAccount.getBind_card_bank_number() + "  | ");
                    } else {
                        write.append(bankno + "  | ");
                    }
                    write.append("           | ");
                    write.append(list.get(i).getF_mark() + "   | ");
                    write.append(list.get(i).getF_remark());
                    write.append(enter);
                }
            }
            buff.write(write.toString().getBytes("UTF-8"));
            buff.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buff != null) buff.close();
                if (outSTr != null) outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载发薪明细
     *
     * @param request
     * @param response
     * @param downLoadSalaryReq
     * @throws ServletException
     * @throws IOException
     */
    public void exportExcel03(HttpServletRequest request, HttpServletResponse response, DownLoadSalaryReq downLoadSalaryReq) throws ServletException, IOException {
        HSSFRow row = null;
        HSSFCell cell = null;
        HSSFWorkbook wb = new HSSFWorkbook(); // 创建整体book
        HSSFSheet sheet = wb.createSheet("0"); // 创建sheet
        //设置列宽
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 5000);
        sheet.setColumnWidth(6, 5000);
        sheet.setColumnWidth(7, 5000);
        sheet.setColumnWidth(8, 5000);
        // 表头
        row = sheet.createRow(0);// 创建行
        cell = row.createCell(0); // 创建列
        cell.setCellValue(Constant.TITLE_EX_NO); // 设置列值
        cell = row.createCell(1);
        cell.setCellValue(Constant.TITLE_EX_SALARY_CARD_NUMBER);
        cell = row.createCell(2);
        cell.setCellValue(Constant.TITLE_EX_CRE_NAME);
        cell = row.createCell(3);
        cell.setCellValue(Constant.TITLE_EX_MOMENY);
        cell = row.createCell(4);
        cell.setCellValue(Constant.TITLE_EX_BANKMARK);
        cell = row.createCell(5);
        cell.setCellValue(Constant.TITLE_EX_BANK_NAME);
        cell = row.createCell(6);
        cell.setCellValue(Constant.TITLE_EX_BANK_TYPE);
        cell = row.createCell(7);
        cell.setCellValue(Constant.TITLE_EX_MARK);
        cell = row.createCell(8);
        cell.setCellValue(Constant.TITLE_EX_REMARK);
        // 内容
        String corpid = downLoadSalaryReq.getF_corpId();
        downLoadSalaryReq.setF_corpId(AesUtil.encryptSalary(corpid, corpid));
        List<DownLoadSalaryReq> salaryList = salaryOverViewDAO.querySalaryOverViewByCorp(downLoadSalaryReq);
        if (salaryList != null && salaryList.size() > 0) {
            for (int i = 0; i < salaryList.size(); i++) {
                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(i + 1);
                cell = row.createCell(1);
                String salary_card_number = salaryList.get(i).getFsalary_card_number();
                if (StringUtils.isEmpty(salary_card_number) || salary_card_number == null) {
                    String userid = AesUtil.decryptSalary(salaryList.get(i).getF_userid(), corpid);
                    SalaryAccount salaryAccount = salaryAccountDAO.queryByUserIdAndCorpid(corpid, userid);
                    cell.setCellValue(AesUtil.decryptAccount(salaryAccount.getBind_card_number()));
                } else {
                    cell.setCellValue(AesUtil.decryptSalary(salary_card_number, corpid));
                }
                cell = row.createCell(2);
                cell.setCellValue(AesUtil.decryptSalary(salaryList.get(i).getFcre_name(), corpid));
                cell = row.createCell(3);
                cell.setCellValue(NumUtil.fenToyuan(salaryList.get(i).getF_momeny()));
                cell = row.createCell(4);
                if (salaryList.get(i).getF_bankmark().equals(SalaryAccount.CardBanksRelation.SAME_BANK.toString())) {
                    cell.setCellValue("0");
                } else {
                    cell.setCellValue("1");
                }
                cell = row.createCell(5);
                cell.setCellValue(salaryList.get(i).getF_bankname());
                cell = row.createCell(6);
                String bankno = salaryList.get(i).getF_bankno();
                if (StringUtils.isEmpty(bankno) || bankno == null) {
                    String userid = AesUtil.decryptSalary(salaryList.get(i).getF_userid(), corpid);
                    SalaryAccount salaryAccount = salaryAccountDAO.queryByUserIdAndCorpid(corpid, userid);
                    cell.setCellValue(salaryAccount.getBind_card_bank_number());
                } else {
                    cell.setCellValue(bankno);
                }
                cell = row.createCell(7);
                cell.setCellValue(salaryList.get(i).getF_mark());
                cell = row.createCell(8);
                cell.setCellValue(salaryList.get(i).getF_remark());
            }
        }
        OutputStream os = null;
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        response.setHeader("Content-Disposition",
//                "attachment; filename=" + new String("zgx.xls".getBytes("UTF-8"), "ISO-8859-1"));
        os = response.getOutputStream();
        wb.write(os);
        os.close();
    }

    /**
     * 发送工资明细给用户
     *
     * @param suiteId
     * @param corpid
     * @param userid
     */
    public void sendSalaryDetailMsg(String suiteId, String corpid, String userid) {
        String url = UrlGenerator.genUserLoginUrl(suiteId, ConfigUtils.getBaseUrl() + "/salary/h5/user_login", "state0");
        String msg = String.format(ConfigUtils.getSalaryDetailMsgTemplate(), url);
        sendTextToUser(suiteId, corpid, userid, msg);
    }

    protected void sendTextToUser(String suiteId, String corpId, String userid, String textMsg) {
        CorpAuthen corpAuthen = corpAuthenDAO.queryByCorpidAndSuiteId(corpId, suiteId);
        if (null == corpAuthen) {
            LOGGER.error("no corp auth record for corpId: {} and suite id: {}", corpId, suiteId);
            throw new BizException(BizError.NO_CORP_AUTHEN);
        }
        String accessToken = wxAccessTokenService.getCorpAccessToken(suiteId, corpId, corpAuthen.getPermanentCode());
        wxworkHttpClient.sendTextToUser(accessToken, corpAuthen.getAgentId(), userid, textMsg);
    }

    /**
     * 业务报表
     * @param salaryDetailBackInfo
     * @return
     */
    public List<SalaryDetailBackInfo> querySalaryDetailBackInfo(SalaryDetailBackInfo salaryDetailBackInfo) {
        List<SalaryDetailBackInfo> list = salaryDetailDAO.querySalaryDetailBackInfo(salaryDetailBackInfo);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                SalaryDetailBackInfo sd = list.get(i);
                if (sd.getFsure_state().equals(SalaryDetailTranIntInfo.Fsure_state.YES.name())) {
                    sd.setFsure_state("是");
                } else {
                    sd.setFsure_state("否");
                }
                sd.setFuserid(AesUtil.decryptSalary(sd.getFuserid(), salaryDetailBackInfo.getUnfcorpid()));
                sd.setFuser_name(AesUtil.decryptSalary(sd.getFuser_name(), salaryDetailBackInfo.getUnfcorpid()));
            }
        }
        return list;
    }

    /**
     * 拼接工资明细表名
     *
     * @param corpId 企业id
     * @return
     */
    public static String getDetailTableName(String corpId, String month) {
        int length = corpId.length();
        if (length < 4) {
            throw new RuntimeException("企业id长度小于4位");
        }
        int hashValue = 0;
        for (int i = length - 4; i < length; i++) {
            char ch = corpId.charAt(i);
            hashValue += (int) ch;
        }
        int modulo = hashValue % 10;
        return month + modulo;
    }

    public static String getDetailTableName(String corpId) {
        int length = corpId.length();
        if (length < 4) {
            throw new RuntimeException("企业id长度小于4位");
        }
        int hashValue = 0;
        for (int i = length - 4; i < length; i++) {
            char ch = corpId.charAt(i);
            hashValue += (int) ch;
        }
        int modulo = hashValue % 10;
        return String.valueOf(modulo);
    }

    /**
     * 将数据从上传的工资表中导入到工资总览表和工资明细表
     *
     * @param salaryDetailInfoList
     * @param month
     * @param corpid
     * @param userid
     */
    public void insertDefinedSalaryDetailList(ArrayList<LinkedHashMap<String, String>> salaryDetailInfoList, String month, String corpid, String userid, int batch_no) {
        //同一个企业，同一个月份的发薪序号，从1开始,查询是否已经发过薪
        int  seqMaxNum = salaryOverViewDAO.queryMaxSequenceNumberForSalaryOverView(AesUtil.encryptSalary(corpid,corpid),month);
        String tableNum = getDetailTableName(corpid);
        List<SalaryAccount> salaryAccounts = salaryAccountDAO.querySalaryAccountByCorpid(corpid);
        SalaryOverViewInfo salaryOverViewInfo;
        if (salaryDetailInfoList != null && salaryAccounts != null && salaryDetailInfoList.size() > 0 && salaryAccounts.size() > 0) {
            for (int i = 0; i < salaryDetailInfoList.size(); i++) {
                for (int j = 0; j < salaryAccounts.size(); j++) {
                    salaryOverViewInfo = new SalaryOverViewInfo();
                    if (salaryDetailInfoList.get(i).get(Constant.DEFINED_CORP_WX_ID).equals(salaryAccounts.get(j).getUserid())) {
                        salaryOverViewInfo.setMonth(month);
                        salaryOverViewInfo.setCorpid(AesUtil.encryptSalary(corpid, corpid));
                        if (seqMaxNum != 0) {
                            seqMaxNum++;
                            salaryOverViewInfo.setSequence_number(seqMaxNum);
                        } else {
                            salaryOverViewInfo.setSequence_number(Integer.valueOf(salaryDetailInfoList.get(i).get(Constant.DEFINED_SEQUENCE_NUMBER)));
                        }
                        if (salaryAccounts.get(j).getDepartment() != null) {
                            salaryOverViewInfo.setDepartment_name(AesUtil.encryptSalary(salaryAccounts.get(j).getDepartment(), corpid));
                        }
                        String corpToUserId = salaryDetailInfoList.get(i).get(Constant.DEFINED_CORP_WX_ID);
                        salaryOverViewInfo.setUserid(AesUtil.encryptSalary(corpToUserId, corpid));
                        String userName = salaryDetailInfoList.get(i).get(Constant.DEFINED_NAME);
                        salaryOverViewInfo.setUser_name(AesUtil.encryptSalary(userName, corpid));
                        //先获取到工资卡进行解密，得到明文后再进行加密
                        if (salaryAccounts.get(j).getBind_card_number() != null) {
                            String bind_card_number = AesUtil.decryptAccount(salaryAccounts.get(j).getBind_card_number());
                            salaryOverViewInfo.setCard_number(AesUtil.encryptSalary(bind_card_number, corpid));
                        }
                        salaryOverViewInfo.setCard_bank_number(salaryAccounts.get(j).getBind_card_bank_number());
                        if (salaryAccounts.get(j).getCard_banks_relation() != null) {
                            salaryOverViewInfo.setBank_cards_relation(salaryAccounts.get(j).getCard_banks_relation().name());
                        }
                        salaryOverViewInfo.setOperator_userid(AesUtil.encryptSalary(userid, corpid));
                        List<BigInteger> listSalary = getSalaryAndDedution(salaryDetailInfoList.get(i));
                        //应发工资总额
                        salaryOverViewInfo.setSalary_sum(listSalary.get(0));
                        //扣款总额
                        salaryOverViewInfo.setDeduction_sum(listSalary.get(1));
                        //实发工资
                        salaryOverViewInfo.setActual_salary(listSalary.get(2));
                        salaryOverViewInfo.setRemark(salaryDetailInfoList.get(i).get(Constant.DEFINED_REMARK));
                        salaryOverViewInfo.setBatch_no(batch_no);
                        int salary_overview_id = 0;
                        //在将数据保存至总览表之前先查询下是否已经存在数据
                        SalaryOverViewInfo querySalaryOverView = salaryOverViewDAO.querySalaryOverView(salaryOverViewInfo);
                        if (querySalaryOverView == null) {
                            //将数据保存到工资总览表中，然后把userid在总览表中的Fsalary_overview_id字段进行返回
                            salaryOverViewDAO.insertSalaryOverView(salaryOverViewInfo);
                            salary_overview_id = salaryOverViewInfo.getSalary_overview_id();
                        } else {
                            salaryOverViewInfo.setIs_read(SalaryOverViewInfo.IsRead.NO.name());
                            salaryOverViewDAO.updateSalaryOverView(salaryOverViewInfo);
                            salary_overview_id = querySalaryOverView.getSalary_overview_id();
                        }
                        List<DefinedSalaryDetailInfo> definedSalaryDetailInfoList = new ArrayList<DefinedSalaryDetailInfo>();
                        for (Map.Entry<String, String> entry : salaryDetailInfoList.get(i).entrySet()) {
                            String[] key = entry.getKey().split("-");
                            String value = entry.getValue();
                            String category = key[0];
                            String detail_name = key[1];
                            String salary = DefinedSalaryDetailInfo.Category.SALARY.name();
                            String deduction = DefinedSalaryDetailInfo.Category.DEDUCTION.name();
                            DefinedSalaryDetailInfo detailInfo;
                            detailInfo = new DefinedSalaryDetailInfo();
                            if (category.equals(salary)) {
                                detailInfo.setCategory(salary);
                            } else if (category.equals(deduction)) {
                                detailInfo.setCategory(deduction);
                            } else {
                                continue;
                            }
                            detailInfo.setSalary_overview_id(salary_overview_id);
                            detailInfo.setDetail_name(detail_name);
                            detailInfo.setDetail_amount(NumUtil.stringToBigInteger(value));
                            definedSalaryDetailInfoList.add(detailInfo);
                        }
                        if (definedSalaryDetailInfoList != null && definedSalaryDetailInfoList.size() > 0){
                            //先查询是否已经有数据,如果存在则将salary_overview_id置为0，再将数据进行插入
                            List<DefinedSalaryDetailInfo> detailInfoList = salaryDetailDAO.querySalaryDetailList(salary_overview_id,month,tableNum);
                            if (detailInfoList != null && detailInfoList.size() > 0){
                                salaryDetailDAO.updateSalaryDetailList(salary_overview_id,month,tableNum);
                            }
                            salaryDetailDAO.insertDefinedSalaryDetailList(definedSalaryDetailInfoList,month,tableNum);
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取应发工资总额，扣款总额，实发总额
     * 应发工资总额  salary_sum
     * 扣款总额      deduction_sum
     * 实发总额      actual_salary
     *
     * @param map
     * @return
     */
    public static List<BigInteger> getSalaryAndDedution(Map<String, String> map) {
        List list = new ArrayList<String>();
        BigInteger salary_sum = new BigInteger("0");
        BigInteger deduction_sum = new BigInteger("0");
        BigInteger actual_salary ;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String[] key = entry.getKey().split("-");
            String value = entry.getValue();
            String category = key[0];
            String salary = DefinedSalaryDetailInfo.Category.SALARY.name();
            String deduction = DefinedSalaryDetailInfo.Category.DEDUCTION.name();
            if (category.equals(salary)) {
                salary_sum = salary_sum.add(NumUtil.stringToBigInteger(value));
            } else if (category.equals(deduction)) {
                deduction_sum = deduction_sum.add(NumUtil.stringToBigInteger(value));
            } else {
                continue;
            }
        }
        actual_salary = salary_sum.subtract(deduction_sum);
        list.add(salary_sum);
        list.add(deduction_sum);
        list.add(actual_salary);
        return list;
    }

    /**
     * 下载自定义工资明细模板
     *
     * @param request
     * @param response
     * @param salaryDetailInfo
     * @throws ServletException
     * @throws IOException
     */
    public void exportDefinedSalaryDetail(HttpServletRequest request, HttpServletResponse response, SalaryDetailInfo salaryDetailInfo) throws ServletException, IOException {
//        Map<String, String> map = new HashMap<String, String>();
        Row row = null;
        Cell cell = null;
        SXSSFWorkbook wb = new SXSSFWorkbook(); // 创建整体book
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        font.setFontHeight((short) 250);
        style.setFont(font);
        Sheet sheet = wb.createSheet("0"); // 创建sheet
        // 表头
        row = sheet.createRow(0);// 创建excel的sheet的一行
        cell = row.createCell(0);//创建excel的单元格
        cell.setCellValue(Constant.DEFINED_SEQUENCE_NUMBER.split("-")[1]);
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(Constant.DEFINED_NAME.split("-")[1]);
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue(Constant.DEFINED_CORP_WX_ID.split("-")[1]);
        cell.setCellStyle(style);
        sheet.setColumnWidth(2, 4000);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
        //从db中获取表头信息
        String corpid = salaryDetailInfo.getFcorpid();
        SalaryFields salaryFields = salaryFieldsDAO.querySalaryFields(corpid,SalaryFields.TemplateSelect.NO.toString());
        int salarySize = 0;
        int deductionSize = 0;
        if (salaryFields != null) {
            String jsonmessage_salary = salaryFields.getSalaryFields();
            String jsonmessage_deduction = salaryFields.getDeductionFields();
            // 首先把字符串转成 JSONArray 对象
            JSONArray json_salary = JSONArray.parseArray(jsonmessage_salary);
            JSONArray json_deduction = JSONArray.parseArray(jsonmessage_deduction);
            if (json_salary != null && json_salary.size() > 0) {
                salarySize = json_salary.size();
                //合并应发项
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 2 + salarySize));
                cell = row.createCell(3);
                cell.setCellValue(Constant.DEFINED_SALARY_PRO.split("-")[1]);
                cell.setCellStyle(style);
                //合并应发金额合计
                sheet.addMergedRegion(new CellRangeAddress(0, 1, 3 + salarySize, 3 + salarySize));
                cell = row.createCell(3 + salarySize);
                cell.setCellValue(Constant.DEFINED_SALARY_TALL.split("-")[1]);
                cell.setCellStyle(style);
                sheet.setColumnWidth(3 + salarySize, 4000);
            }
            if (json_deduction != null && json_deduction.size() > 0) {
                deductionSize = json_deduction.size();
                //合并应扣项
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 4 + salarySize, 3 + salarySize + deductionSize));
                cell = row.createCell(4 + salarySize);
                cell.setCellValue(Constant.DEFINED_DEDUCTION_PRO.split("-")[1]);
                cell.setCellStyle(style);
                //合并应扣金额合计
                sheet.addMergedRegion(new CellRangeAddress(0, 1, 4 + salarySize + deductionSize, 4 + salarySize + deductionSize));
                cell = row.createCell(4 + salarySize + deductionSize);
                cell.setCellValue(Constant.DEFINED_DEDUCTION_TALL.split("-")[1]);
                cell.setCellStyle(style);
                sheet.setColumnWidth(4 + salarySize + deductionSize, 4000);
                //合并实发金额
                sheet.addMergedRegion(new CellRangeAddress(0, 1, 5 + salarySize + deductionSize, 5 + salarySize + deductionSize));
                cell = row.createCell(5 + salarySize + deductionSize);
                cell.setCellValue(Constant.DEFINED_ACTION_SALARY.split("-")[1]);
                cell.setCellStyle(style);
                sheet.setColumnWidth(5 + salarySize + deductionSize, 3500);
            }
            //设置应发金额的表头
            if (json_salary != null && salarySize > 0) {
                row = sheet.createRow(1);
                for (int i = 0; i < salarySize; i++) {
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    JSONObject job = json_salary.getJSONObject(i);
                    // 得到 每个对象中的属性值
                    String salary_value = job.get("salary").toString();
                    cell = row.createCell(i + 3);
                    cell.setCellValue(salary_value);
                    cell.setCellStyle(style);
                }
            }
            //设置应扣金额的表头
            if (json_deduction != null && deductionSize > 0) {
                for (int i = 0; i < deductionSize; i++) {
                    JSONObject job = json_deduction.getJSONObject(i);
                    String deduction_value = job.get("deduction").toString();
                    cell = row.createCell(i + 4 + salarySize);
                    cell.setCellValue(deduction_value);
                    cell.setCellStyle(style);
                }
            }
            //设置备注项
            if ((json_salary != null && salarySize > 0) || (json_deduction != null && deductionSize > 0)) {
                cell = row.createCell(6 + salarySize + deductionSize);
                cell.setCellValue(Constant.DEFINED_REMARK.split("-")[1]);
                cell.setCellStyle(style);
            }
        }
        // 内容
        UserInfo userInfo = new UserInfo();
        userInfo.setCorpid(salaryDetailInfo.getFcorpid());
        List<UserInfo> userList = userInfoDAO.queryUserInfo(userInfo);
        int userListSize = 0;
        if (userList != null && userList.size() > 0) {
            userListSize = userList.size();
            String formula = "";
            for (int i = 0; i < userListSize; i++) {
                UserInfo sd = userList.get(i);
                row = sheet.createRow(i + 2);
                cell = row.createCell(0);
                cell.setCellValue(sd.getSequence_number());
                cell = row.createCell(1);
                cell.setCellValue(sd.getName());
                cell = row.createCell(2);
                cell.setCellValue(sd.getUserid());
                //给应发金额合计设置公式
                if (salarySize > 0) {
                    cell = row.createCell(3 + salarySize);
                    formula = ExcelUtil.sheetFormulaRowAdd(68, salarySize - 1, i + 3);
                    cell.setCellType(CellType.FORMULA);
                    cell.setCellFormula(formula);
                }
                //给应扣金额合计设置公式
                if (deductionSize > 0) {
                    cell = row.createCell(4 + salarySize + deductionSize);
                    formula = ExcelUtil.sheetFormulaRowAdd(69 + salarySize, deductionSize - 1, i + 3);
                    cell.setCellType(CellType.FORMULA);
                    cell.setCellFormula(formula);
                }
                //给实发金额设置公式
                if (salarySize > 0 && deductionSize > 0) {
                    cell = row.createCell(5 + salarySize + deductionSize);
                    formula = ExcelUtil.sheetFormulaRowSub(68 + salarySize, deductionSize + 1, i + 3);
                    cell.setCellType(CellType.FORMULA);
                    cell.setCellFormula(formula);
                }
            }
            row = sheet.createRow(2 + userListSize);
            cell = row.createCell(1);
            cell.setCellValue("合计");
            //给应发金额总合计设置公式
            if (salarySize > 0) {
                cell = row.createCell(3 + salarySize);
                formula = ExcelUtil.sheetFormulaCellAdd(68 + salarySize, userListSize - 1, 3);
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula(formula);
            }
            //给应扣金额总合计设置公式
            if (deductionSize > 0) {
                cell = row.createCell(4 + salarySize + deductionSize);
                formula = ExcelUtil.sheetFormulaCellAdd(69 + salarySize + deductionSize, userListSize - 1, 3);
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula(formula);
            }
            //给实发金额总合计设置公式
            if (salarySize > 0 && deductionSize > 0) {
                cell = row.createCell(5 + salarySize + deductionSize);
                formula = ExcelUtil.sheetFormulaCellAdd(70 + salarySize + deductionSize, userListSize - 1, 3);
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula(formula);
            }
        }
        OutputStream os = null;
        os = response.getOutputStream();
        wb.write(os);
        os.close();
    }

    /**
     * 获取上传工资明细后摘要信息
     *
     * @param month
     * @param corpid
     * @return
     */
    public ViewSalaryAllDetail viewSalaryAllDetail(String month, String corpid, int batch_no) {
        corpid = AesUtil.encryptSalary(corpid, corpid);
        ViewSalaryAllDetail viewSalaryAllDetail = salaryOverViewDAO.viewSalaryAllDetail(corpid, month, batch_no);
        String format_month = "";
        if (month.length() == 6) {
            format_month = month.substring(0, 4) + "年" + month.substring(4, month.length()) + "月";
        }
        viewSalaryAllDetail.setMonth(format_month);
        return viewSalaryAllDetail;
    }

    /**
     * 发送查询工资明细通知给用户
     *
     * @param corpid
     * @param month
     * @return
     */
    public String sendMsgToNoticeUser(String corpid, String month, int batch_no) {
        String corpidSecret = AesUtil.encryptSalary(corpid, corpid);
        List<SalaryOverViewInfo> infoList = salaryOverViewDAO.querySalaryOverviewForMonthAndCorp(corpidSecret, month, batch_no);
        String retCode = "0";
        if (infoList != null && infoList.size() > 0) {
            for (int i = 0; i < infoList.size(); i++) {
                String useriddec = AesUtil.decryptSalary(infoList.get(i).getUserid(), corpid);
                String suiteId = ConfigUtils.getSuiteId();
                try {
                    sendSalaryDetailMsg(suiteId, corpid, useriddec);
                } catch (Exception e) {
                    msgNotifyDAO.insertMsgNotify(MsgNotify.BUSI_TYPE_SALARY, corpid + ":" + useriddec, MsgNotify.State.FAIL.name(), "1");
                    continue;
                }
                //消息通知后存入消息通知表
                msgNotifyDAO.insertMsgNotify(MsgNotify.BUSI_TYPE_SALARY, corpid + ":" + useriddec, MsgNotify.State.SUCCESS.name(), "0");
            }
        } else {
            retCode = "1";
        }
        return retCode;
    }

    /**
     * 发送工资明细通知银行付款
     *
     * @param corpid
     * @param month
     * @return
     */
    public SalaryApprovalResponse sendSalaryToFlow(String corpid, String month, int batch_no, SessionInfo session) {
        String corpidSecret = AesUtil.encryptSalary(corpid, corpid);
        //获取申请人信息
        UserInfo applyUser = userInfoDAO.queryByCorpidUserid(session.getCorpId(), session.getUserId());

        SalarySequences seqNum = salaryOverViewDAO.querySequenceNumberForSalaryApprove(AesUtil.encryptSalary(corpid,corpid),month,batch_no);
        int seqMinNum = seqNum.getSeqMinNum();
        int seqMaxNum = seqNum.getSeqMaxNum();
        String retCode = "0";
        
        //批次号
        int btNum = seqMaxNum/1000 - seqMinNum/1000 + 1;
        int start = 0;
        int end = 0;
        for(int n = 1; n <= btNum; n ++){
            if(n == 1){
                //第一批
                start = seqMinNum;
                end = (seqMinNum/1000 + 1) * 1000 - 1;
            }else if(n == btNum){
                //最后一批
                start = end + 1;
                end = seqMaxNum;
            }else{
                start = end + 1;
                end = start + 1000 - 1;
            }

            List<SalaryFlowDetail> salaryDetails = salaryOverViewDAO.querySalaryForApprove(corpidSecret, month, batch_no,
                    applyUser.getName(), start, end);
            //解密操作
            salaryDetails.forEach(info -> {
                info.setReceiveName(AesUtil.decryptSalary(info.getReceiveName(),corpid));
                info.setReceiveAccount(AesUtil.decryptSalary(info.getReceiveAccount(),corpid));
            });
            //审批流接口数据
            SalaryFlowSubmitReqParam reqParam = new SalaryFlowSubmitReqParam();
            reqParam.setCorpId(session.getCorpId());
            int threeNum = end / 1000;
            String flowId = "salary" + session.getCorpId() + DateUtil.getSubMonth() + batch_no + NumUtil.frontCompWithZero(threeNum, 3);
            reqParam.setFlowId(flowId);
            reqParam.setApprovalType(Integer.toString(ApporvalType.Salary));
            reqParam.setName("发薪");
            ViewSalaryAllDetail salarySum = salaryOverViewDAO.forApproveSalarySum(corpidSecret, month
                    , batch_no, start, end);
            reqParam.setCount(salarySum.getCounter());
            reqParam.setSumAmount(salarySum.getSalaryAll());
            reqParam.setApprovalName("");
            reqParam.setStatus(2);
            
            // detail
            JSONObject detail = new JSONObject();
            detail.put("type", Integer.toString(ApporvalType.Salary));
            detail.put("reason", "发薪");
            detail.put("common", "");
            detail.put("pay_time", Long.toString(new Date().getTime()));

            reqParam.setDetails(JSONObject.toJSONString(detail));
            reqParam.setSalaryDetailList(salaryDetails);

            LOGGER.info("=============="+reqParam.toString());
            SalaryApprovalResponse resp = approvalClient.salaryFlowSubmit(reqParam);
            
            if (!resp.getRetCode().equals("0")) {
                return resp;
            }
        }

        return new SalaryApprovalResponse("0", "OK");
    }

    /**
     * 招行绑卡模式  获取未绑卡的用户
     *
     * @param salaryAccountForCmbInfo
     * @return
     */
    public List<SalaryAccountForCmbInfo> querySalaryAccountForCmb(SalaryAccountForCmbInfo salaryAccountForCmbInfo) {
        String corpid = salaryAccountForCmbInfo.getFcorpid();
        int fstate = SalaryAccount.State.ACCOUNT_OPENED.toInt();
        String fkey = CorpConfKey.CORP_TO_BANK;
        String fvalue = "";
        String au_state = SalaryCorpConfInfo.State.AUDITED.name();
        SalaryCorpConfInfo salaryCorpConfInfo = salaryCorpConfDAO.querySalaryCorpConfInfo(corpid, fkey, au_state);
        if (salaryCorpConfInfo != null) {
            fvalue = salaryCorpConfInfo.getValue();
        }
        List<SalaryAccountForCmbInfo> list = salaryAccountDAO.querySalaryAccountForCmb(corpid, fkey, fvalue, fstate);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setFsequence_number(i + 1);
            }
        }
        return list;
    }


    /**
     * 招行绑卡模式 批量导入用户信息,实现绑卡操作
     *
     * @param salaryAccountForCmbInfo
     * @param corpid
     */

    public void updateSalaryAccountForCmb(List<SalaryAccountForCmbInfo> salaryAccountForCmbInfo, String corpid) {
        //先获取企业所有的员工
        List<SalaryAccount> salaryAccounts = salaryAccountDAO.querySalaryAccountByCorpid(corpid);
        List<String> userId_list = salaryAccountDAO.selectSalaryAccountUserId(corpid);
        if (salaryAccounts != null && salaryAccountForCmbInfo != null && salaryAccounts.size() > 0 && salaryAccountForCmbInfo.size() > 0) {
            List<String> salaryAccounts_userIdList = new ArrayList<>();
            for (int j = 0; j < salaryAccounts.size(); j++) {
                salaryAccounts_userIdList.add(salaryAccounts.get(j).getUserid());
            }

            for (int i = 0; i < salaryAccountForCmbInfo.size(); i++) {
                SalaryAccountForCmbInfo info = salaryAccountForCmbInfo.get(i);
                if (salaryAccounts_userIdList.indexOf(salaryAccountForCmbInfo.get(i).getFuserid()) != -1) {
                    String fcre_name = AesUtil.encryptAccount(info.getFuser_name());
                    int fstate = SalaryAccount.State.INIT.toInt();
                    String info_fbind_card_number = info.getFbind_card_number();
                    if (StringUtils.isNotBlank(info_fbind_card_number)){
                        String fbind_card_number = AesUtil.encryptAccount(info_fbind_card_number);
                        String userid = info.getFuserid();
                        if (StringUtils.isBlank(userid)){
                            continue;
                        }
                        FinGateClient finGateClient = new FinGateClient();
                        NationalBankNumber nationalBankNumber = new NationalBankNumber();
                        CardBin cardBin = finGateClient.queryCardBin(info_fbind_card_number);
                        String bankName = cardBin.getBankSname();
                        String fbind_card_bank_number = nationalBankNumber.query(bankName);

                        String fremark = StringUtils.isNotBlank(info.getFremark())?info.getFremark():"";
                        if (fremark.length() > 255){
                            fremark = fremark.substring(0,250);
                        }
                        if (userId_list.indexOf(salaryAccountForCmbInfo.get(i).getFuserid()) != -1) {
                            salaryAccountDAO.updateSalaryAccountForCmb(userid, corpid, fstate, fcre_name, bankName, fbind_card_number, fbind_card_bank_number,fremark);
                        } else {
                            salaryAccountDAO.insertSalaryAccountForCmb(userid, corpid, fstate, fcre_name, bankName, fbind_card_number, fbind_card_bank_number,fremark);
                        }
                    }

                }
            }

        }
    }

    /**
     * 获取企业配置表中企业所对应的银行
     *
     * @param corpid
     * @return
     */
    public SalaryCorpConfInfo getSalaryCorpConfByBankname(String corpid) {
        String fkey = CorpConfKey.CORP_TO_BANK;
        String au_state = SalaryCorpConfInfo.State.AUDITED.name();
        SalaryCorpConfInfo salaryCorpConfInfo = salaryCorpConfDAO.querySalaryCorpConfInfo(corpid, fkey, au_state);
        return salaryCorpConfInfo;
    }

    /**
     * 获取企业某月入职离职员工人数
     *
     * @param corpid
     * @return
     */
    public UserChangeResponse getUserChange(String corpid, String month){
        UserChangeResponse userChangeResponse = salaryAccountDAO.queryUserComeinAndLeave(corpid, SalaryAccount.State.ACCOUNT_DELETED.toInt(),month);
        if(userChangeResponse == null){
            userChangeResponse = new UserChangeResponse();
        }else {
            if(userChangeResponse.getUserComein() == 0 && userChangeResponse.getUserLeave() == 0){
                userChangeResponse.setChange(false);
            }else {
                userChangeResponse.setChange(true);
            }
        }
        return userChangeResponse;
    }

}
