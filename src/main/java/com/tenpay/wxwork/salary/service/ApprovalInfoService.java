package com.tenpay.wxwork.salary.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.ExceptionUtils;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.dao.CorpApprovalInfoDAO;
import com.tenpay.wxwork.salary.dto.ApprovalResponse;
import com.tenpay.wxwork.salary.model.CorpApprovalInfo;
import com.tenpay.wxwork.salary.service.wxauth.WxHttpClient;
import com.tenpay.wxwork.salary.dto.Flow1002DetailReq;
import com.tenpay.wxwork.salary.info.ReceiveAccount;

@Service("approvalInfoService")
public class ApprovalInfoService {
    @Autowired
    private CorpApprovalInfoDAO corpApprovalInfoDAO;

    @Resource
    private Gson gson;

    @Autowired
    private WxHttpClient wxClient;

    private static final Logger logger = LoggerFactory.getLogger(ApprovalInfoService.class);

    public String getLastMotifyTime(String corpid)
    {
        CorpApprovalInfo approvalInfo = corpApprovalInfoDAO.queryLastCorpApprovalInfo(corpid);
        String modify_time="";
        if (approvalInfo != null)
        {
            modify_time = approvalInfo.getModify_time();
        }
        else
        {
            String approvalInfoStartTime = ConfigUtils.getApprovalInfoStartTime();
            modify_time = approvalInfoStartTime;
        }
        return modify_time;
    }

    public String getStartTime(String corpid)
    {
        String seconds= "";
        try
        {
            String startTime = getLastMotifyTime(corpid);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            seconds = String.valueOf(df.parse(startTime).getTime()/1000);
        }
        catch(Exception e)
        {
            throw new BizException(BizError.DATE_FORMAT_ERROR.errorCode(),"date format error");
        }
        return seconds;
    }

    public String getEndTime()
    {
        String seconds= "";
        try
        {
            Date date = new Date();
            seconds = String.valueOf(date.getTime()/1000);
        }
        catch(Exception e)
        {
            throw new BizException(BizError.DATE_FORMAT_ERROR.errorCode(),"date format error");
        }
        return seconds;
    }

    public ApprovalResponse queryApprovalData(String corpid, String accessToken)
    {
        String startTime = getStartTime(corpid);
        String endTime = getEndTime();
        JsonObject reqData = new JsonObject();
        reqData.addProperty("starttime", startTime);
        reqData.addProperty("endtime", endTime);
        logger.debug("start time:" + startTime + " end time:" + endTime);
        logger.debug("req data:" + gson.toJson(reqData) + " access_token:" + accessToken);
        ApprovalResponse approvalData  = dto(wxClient.getapprovaldata(accessToken, gson.toJson(reqData)));

        return approvalData;
    }

    public ApprovalResponse dto(String reqData)
    {
        JsonObject result =gson.fromJson(reqData, JsonObject.class) ;
        if(null == result){
            return null;
        }
        else if(result.has("errcode")) {
            ExceptionUtils.checkResult(result.get("errcode").getAsInt(), result.get("errmsg").getAsString());
        }

        if(result.get("total").getAsInt() == 0)
        {
            logger.error("return approval info not exist.");
            throw new BizException(BizError.APPROVAL_INFO_NOT_EXIST_ERROR.errorCode(),"return approval info not exist.");
        }

        ApprovalResponse res = new ApprovalResponse();
        JsonArray data = result.get("data").getAsJsonArray();
        res.setDataArr(data);
        res.setCount(result.get("count").getAsInt());
        res.setNext_spnum(result.get("next_spnum").getAsString());
        res.setTotal(result.get("total").getAsInt());
        return res;
    }

    public String ArrayToString(String[] arr)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        for (int i= 0; i < arr.length; i++)
        {
            stringBuffer.append(arr[i]);
            if (i != (arr.length-1))
            {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public String getCurTime()
    {
        Date date = new Date();
        SimpleDateFormat df = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    // 解析apply_data, 获取收款相关信息
    public void getUserAccoutInfo(String apply_data, ReceiveAccount receiveAccount, long[] outArr, Flow1002DetailReq flow1002DetailReq)
    {

        JSONObject jsonObject = new JSONObject(apply_data);
        long bit = 0; //用于判断数据的完整性
        long amount = 0;//收款金额
        long isCrossFlag = 1;  //是否跨行标记，默认跨行
        for (String key : jsonObject.keySet())
        {
            JSONObject item = jsonObject.getJSONObject(key);
            String id = (String)item.get("id");
            if (id.equals("item-1529054668172"))
            {
                //收款人户名
                receiveAccount.setReceive_name((String)item.get("value"));
                bit = (bit | (1 << 0));
            }
            else if (id.equals("item-1529054746166"))
            {
                //收款账号
                receiveAccount.setReceive_account((String)item.get("value"));
                bit = (bit | (1 << 1));
            }
            else if (id.equals("item-1529054695571"))
            {
                //收款人开户银行
                receiveAccount.setAccount_bank((String)item.get("value"));
                bit = (bit | (1 << 2));
                if ((String)item.get("value") == "招商银行")
                {
                    isCrossFlag = 0;
                }
                else
                {
                    isCrossFlag = 1;
                }
            }
            else if (id.equals("item-1529054710803"))
            {
                //收款人开户行所在城市
                receiveAccount.setAccount_area((String)item.get("value"));
                bit = (bit | (1 << 3));
            }
            else if (id.equals("item-1494249104239"))
            {
                //金额是元，要乘以100
                String amountStr = (String)item.get("value");
                amount = (long)(Double.parseDouble(amountStr) * 100.0);
                bit = (bit | (1 << 4));
            }
            else if(id.equals("item-1494248811896"))
            {
                //费用类型
                flow1002DetailReq.setExpenseType((String)item.get("value"));
                bit = (bit | (1 << 5));
            }
            else if(id.equals("item-1494249039034"))
            {
                //事由
                flow1002DetailReq.setReason((String)item.get("value"));
                bit = (bit | (1 << 6));
            }
            else if(id.equals("item-1494249126248"))
            {
                //备注
                flow1002DetailReq.setCommon((String)item.get("value"));
                bit = (bit | (1 << 7));
            }
            else if(id.equals("item-1494249113679"))
            {
                //发生时间
                long timeStamp = (long)item.get("value");
                flow1002DetailReq.setTime(String.valueOf(timeStamp));
                bit = (bit | (1 << 8));
            }
        }

        //检查数据是否完整
        if ((bit &0x1ff) != 0x1ff)
        {
            throw new BizException(BizError.PARSE_APPROVAL_DATA_USER_ACOUNT_FAILED.errorCode(), "parse approval data user account failed");
        }

        outArr[0] = amount;
        outArr[1] = isCrossFlag;
    }

    public List<CorpApprovalInfo> buildCorpApprovalInfo(String corpid, String bankid, ApprovalResponse response) throws JsonProcessingException
    {
        JsonArray dataArr = response.getDataArr();
        List<CorpApprovalInfo> approvalInfoList =  new ArrayList<>();
        for (int i = 0; i < dataArr.size(); i++)
        {
            JsonObject data = dataArr.get(i).getAsJsonObject();
            if (data.get("sp_status").getAsInt() != 2)  //过滤非审批通过的状态
            {
                logger.error("approval state must be approvaled.");
                continue;
            }
            CorpApprovalInfo corpApprovalInfo = new CorpApprovalInfo();
            corpApprovalInfo.setApproval_id(data.get("sp_num").getAsString());
            corpApprovalInfo.setCorp_id(corpid);
            corpApprovalInfo.setOp_userid(data.get("apply_user_id").getAsString());
            corpApprovalInfo.setBank_id(bankid);
            corpApprovalInfo.setName(data.get("spname").getAsString());
            String type;

            //一期只处理费用类型的审批
            if (corpApprovalInfo.getName().equals("报销"))
            {
                logger.debug("type of reimburse not supported.");
                continue;
            }
            else if (corpApprovalInfo.getName().equals("费用"))
            {
                type = "1002";
            }
            else if (corpApprovalInfo.getName().equals("付款"))
            {
                logger.debug("type of payment not supported.");
                continue;
            }
            else
            {
                throw new BizException(BizError.UN_SUPPORT_APPROVAL_INFO_TYPE.errorCode(), "NOT SUPPORT APPROVAL INFO TYPE");
            }
            corpApprovalInfo.setType(type);
            corpApprovalInfo.setApply_name(data.get("apply_name").getAsString());

            long applyTime = Long.parseLong(data.get("apply_time").getAsString()) * 1000;
            String applyTimeDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(applyTime));
            corpApprovalInfo.setApply_time(applyTimeDatetime);
            logger.debug("applyTime datetime:" + applyTimeDatetime);

            corpApprovalInfo.setApproval_name(data.get("approval_name").getAsJsonArray().toString());
            corpApprovalInfo.setStatus(data.get("sp_status").getAsInt());

            String apply_data = data.get("comm").getAsJsonObject().get("apply_data").getAsString();

            long[] outArr = new long[2];
            Flow1002DetailReq flow1002DetailReq = new Flow1002DetailReq();
            flow1002DetailReq.setType(type);
            ReceiveAccount receiveAccount = new ReceiveAccount();
            //解析收款人信息
            getUserAccoutInfo(apply_data, receiveAccount, outArr, flow1002DetailReq);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String detail = mapper.writeValueAsString(flow1002DetailReq);

            corpApprovalInfo.setReceive_name(receiveAccount.getReceive_name());
            corpApprovalInfo.setReceive_account(receiveAccount.getReceive_account());
            corpApprovalInfo.setAccount_bank(receiveAccount.getAccount_bank());
            corpApprovalInfo.setAccount_area(receiveAccount.getAccount_area());
            corpApprovalInfo.setAmount(outArr[0]);
            corpApprovalInfo.setCross_flag((int)outArr[1]);


            corpApprovalInfo.setDetail(detail);
            corpApprovalInfo.setBank_list("");
            corpApprovalInfo.setPayment_state("INIT");
            corpApprovalInfo.setPay_memo("");

            String curTime = getCurTime();

            //pay_time默认值
            corpApprovalInfo.setPay_time("1970-01-01 00:00:00");
            corpApprovalInfo.setCreate_time(curTime);
            corpApprovalInfo.setModify_time(curTime);

            approvalInfoList.add(corpApprovalInfo);
        }
        return approvalInfoList;
    }

    /*
    public List<CorpApprovalInfo> buildCorpApprovalInfo2(String corpid, String bankid, ApprovalResponse response) throws JsonProcessingException
    {
        ApprovalResponseData[] dataArr = null;//response.getDataArr();
        List<CorpApprovalInfo> approvalInfoList =  new ArrayList<>();
        for (int i = 0; i < dataArr.length; i++)
        {
            ApprovalResponseData data = dataArr[i];
            if (data.getSp_status() != 2)  //过滤非审批通过的状态
            {
                logger.error("approval state must be approvaled.");
                continue;
            }
            CorpApprovalInfo corpApprovalInfo = new CorpApprovalInfo();
            corpApprovalInfo.setApproval_id(data.getSp_num());
            corpApprovalInfo.setCorp_id(corpid);
            corpApprovalInfo.setOp_userid(data.getApply_user_id());
            corpApprovalInfo.setBank_id(bankid);
            corpApprovalInfo.setName(data.getSpname());
            String type;

            //一期只处理费用类型的审批
            if (data.getSpname().equals("报销"))
            {
                logger.debug("type of reimburse not supported.");
                continue;
            }
            else if (data.getSpname().equals("费用"))
            {
                type = "1002";
            }
            else if (data.getSpname().equals("付款"))
            {
                logger.debug("type of payment not supported.");
                continue;
            }
            else
            {
                throw new BizException(BizError.UN_SUPPORT_APPROVAL_INFO_TYPE.errorCode(), "NOT SUPPORT APPROVAL INFO TYPE");
            }
            corpApprovalInfo.setType(type);
            corpApprovalInfo.setApply_name(data.getApply_name());
            corpApprovalInfo.setApply_time(data.getApply_time());
            corpApprovalInfo.setApproval_name(ArrayToString(data.getApproval_name()));
            corpApprovalInfo.setStatus(data.getSp_status());

            String apply_data = data.getComm().getApply_data();
            String receive_name = "",receive_account = "", account_bank="", account_area="";
            long[] outArr = new long[2];
            Flow1002DetailReq flow1002DetailReq = new Flow1002DetailReq();
            flow1002DetailReq.setType(type);
            //解析收款人信息
            getUserAccoutInfo(apply_data, receive_name, receive_account, account_bank, account_area, outArr, flow1002DetailReq);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String detail = mapper.writeValueAsString(flow1002DetailReq);

            corpApprovalInfo.setReceive_name(receive_name);
            corpApprovalInfo.setReceive_account(receive_account);
            corpApprovalInfo.setAccount_bank(account_bank);
            corpApprovalInfo.setAccount_area(account_area);
            corpApprovalInfo.setAmount(outArr[0]);
            corpApprovalInfo.setCross_flag((int)outArr[1]);


            corpApprovalInfo.setDetail(detail);
            corpApprovalInfo.setBank_list("");
            corpApprovalInfo.setPayment_state("INIT");
            corpApprovalInfo.setPay_memo("");

            String curTime = getCurTime();

            corpApprovalInfo.setPay_time("1970-01-01 00:00:00");
            corpApprovalInfo.setCreate_time(curTime);
            corpApprovalInfo.setModify_time(curTime);

            approvalInfoList.add(corpApprovalInfo);
        }
        return approvalInfoList;
    }
    */

    public void insertCorpApprovalInfo(CorpApprovalInfo corpApprovalInfo)
    {
        corpApprovalInfoDAO.insertApprovalInfo(corpApprovalInfo.getApproval_id(), corpApprovalInfo.getCorp_id(), corpApprovalInfo.getOp_userid(),
                corpApprovalInfo.getBank_id(), corpApprovalInfo.getName(), corpApprovalInfo.getType(), corpApprovalInfo.getApply_name(), corpApprovalInfo.getApply_time(),
                corpApprovalInfo.getApproval_name(), corpApprovalInfo.getStatus(), corpApprovalInfo.getReceive_name(), corpApprovalInfo.getReceive_account(),
                corpApprovalInfo.getAccount_bank(), corpApprovalInfo.getAccount_area(), corpApprovalInfo.getCross_flag(), corpApprovalInfo.getAmount(),
                corpApprovalInfo.getDetail(), corpApprovalInfo.getPayment_state(), corpApprovalInfo.getPay_time());
    }

    public void updateBankApprovalInfo(int status, String bankList, String approvalId)
    {
        corpApprovalInfoDAO.updateBankApprovalInfo(status, bankList, approvalId);
    }
}
