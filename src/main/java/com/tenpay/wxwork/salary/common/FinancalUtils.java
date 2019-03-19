package com.tenpay.wxwork.salary.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tenpay.wxwork.salary.dto.AdminInfo;
import com.tenpay.wxwork.salary.dto.GetAdminListRes;
import com.tenpay.wxwork.salary.info.AuthenType;
import com.tenpay.wxwork.salary.info.CorpAppRelationStatus;

@Component
public class FinancalUtils {
	
//    private static String workFlowSupportedBankTypes;
    
    /**
     * 校验请求参数合法性
     */
    public static void checkCorpBindRequestParam(String status,String bankType,String appid){
    	/*if(! AppId.contains(appid))
    	{
    		throw new BizException(BizError.UNSUPPORTED_APPID_ERROR.errorCode(),"Unsupported Appid:"+appid);
    	}
    	else
		{
    		if( AppId.ApprovalStream.equals(appid) && !workFlowSupportedBankTypes.contains(bankType))
        	{
        		throw new BizException(BizError.UNSUPPORTED_BANK.errorCode(),"不支持的银行类型:"+bankType);
        	}
		}*/
    	/*if(!workFlowSupportedBankTypes.contains(bankType))
    	{
    		throw new BizException(BizError.UNSUPPORTED_BANK.errorCode(),"不支持的银行类型:"+bankType);
    	}*/
    	
    	
    	if(!CorpAppRelationStatus.contains(status))
    	{
    		throw new BizException(BizError.UNSUPPORTED_RELATION_STATUS_ERROR.errorCode(),"Unsupported corpAppRelation status:"+status);
    	}
    }
    

    public static boolean isOpUser(GetAdminListRes getAdminListRes,String opUserId){
        for(AdminInfo element:getAdminListRes.getAdmin())
        {
        	if(AuthenType.isAdmin(element.getAuthType()))
        	{
        		if(!opUserId.isEmpty() && opUserId.equals(element.getUserid()))
        		{
        			return true;
        		}
        	}
        }
        return false;
    }
}
