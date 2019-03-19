package com.tenpay.fingate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.util.MapConvert;
// import com.tenpay.fingate.util.JsonUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tenpay.fingate.util.AesUtil;
import com.tenpay.fingate.util.MD5;
import com.tenpay.fingate.util.MapUtil;
// import com.tenpay.fingate.util.ResolveProperties;
// import com.tenpay.fingate.util.ResultMessageEnum;
import com.tenpay.fingate.util.Sha256;
import com.tenpay.fingate.util.Des3Util;
import com.tenpay.fingate.util.StrUtil;

import java.net.NetworkInterface;
import java.net.InetAddress;
import java.util.Enumeration;

/**
 * 
 * 资金流转service，主要实现： 
 * 1、获取支付订单号 
 * 2、c2b支付 
 * 3、c2b支付后的查单 
 * 4、c2b支付后进行退款 
 *
 * 待处理：
 * 1、查询商户余额(通过c2b转账看是否可以获取到)
 * 
 * @author robenzhang
 * @date 2017-03-24
 * 
 */
public class FundTransferService {
	private static final Log log = LogFactory.getLog(FundTransferService.class);

	// 申请的app_id
	private String appId;
	// 申请的app_key
	private String appKey;

    // 卡号加密密码
    private String cardNumberEncryptPasswd;

    private String cardbinQueryUrl;

    private String smsCodeSendUrl;

    private String smsCodeVerifyUrl;

    private String l5ModId;

    private String l5CmdId;

    private float l5Timeout;

    public String getCardbinQueryUrl()
    {
        return cardbinQueryUrl;
    }

    public void setCardbinQueryUrl(String cardbinQueryUrl)
    {
        this.cardbinQueryUrl = cardbinQueryUrl;
    }

    public String getSmsCodeSendUrl() {
        return smsCodeSendUrl;
    }

    public void setSmsCodeSendUrl(String smsCodeSendUrl) {
        this.smsCodeSendUrl = smsCodeSendUrl;
    }

    public String getSmsCodeVerifyUrl() {
        return smsCodeVerifyUrl;
    }

    public void setSmsCodeVerifyUrl(String smsCodeVerifyUrl) {
        this.smsCodeVerifyUrl = smsCodeVerifyUrl;
    }
    /*
	// 加密的key
	private static final String MD5_KEY = ResolveProperties.getValue("c2b.md5.key");
	// 获取支付订单号地址
	private static final String TRANSANCTION_URL = ResolveProperties.getValue("c2b.obtain.transactoinid.url");
	private static final String TRANSANCTION_L5_URL = ResolveProperties.getValue("c2b.obtain.transactoinid.l5.url");
	// 调用支付地址
	private static final String PAY_URL = ResolveProperties.getValue("c2b.pay.url");
	private static final String PAY_L5_URL = ResolveProperties.getValue("c2b.pay.l5.url");
	// 获取支付订单号地址
	private static final String QUERY_ORDER_URL = ResolveProperties.getValue("c2b.query.order.url");
	private static final String QUERY_ORDER_L5_URL = ResolveProperties.getValue("c2b.query.order.l5.url");
	// 获取支付订单号地址
	private static final String REFUND_URL = ResolveProperties.getValue("c2b.refund.url");
	private static final String REFUND_L5_URL = ResolveProperties.getValue("c2b.refund.l5.url");
    */

    public FundTransferService(String appId, String appKey, String cardNumberEncryptPasswd,
                               String l5ModId, String l5CmdId, float l5Timeout) {
        this.appId = appId;
        this.appKey = appKey;
        this.cardNumberEncryptPasswd = cardNumberEncryptPasswd;
        this.l5ModId = l5ModId;
        this.l5CmdId = l5CmdId;
        this.l5Timeout = l5Timeout;
    }

    private String getLocalIp() {
        String ip;
        try {
            NetworkInterface nif = NetworkInterface.getByName("eth1");
            Enumeration<InetAddress> addressEnum = nif.getInetAddresses();
            InetAddress address = addressEnum.nextElement();
 
            ip = address.getHostAddress();
        } catch (Exception e) {
            log.error("failed to get local ip: " + e);
            ip = "127.0.0.1";
        }        

        return ip;
    }

	/**
	 * 组装传递公共参数 做了特殊处理： 
	 * 1、采取JSON格式返回数据 
	 * 2、采取appid调用的协议
	 */
	private String inputParam(Map<String, String> inputMap) {
		inputMap.put("Ver", "1.1");
		inputMap.put("SeqNo", UUID.randomUUID().toString().replace("-", "").substring(0, 30));
		inputMap.put("OutPutType", "2");
		inputMap.put("ProtocolType", "2");
		inputMap.put("AppId", appId);
		Date now = new Date();
		inputMap.put("PlatTimeStamp", now.getTime() / 1000 + "");// 获取当前的秒数
		inputMap.put("From", "2");
		inputMap.put("GateType", "2");
		inputMap.put("SignType", "SHA256");
		inputMap.put("Sign", Sha256.sha256(MapUtil.mapToUrlString(inputMap, null) + "&key=" + appKey));
		return MapUtil.mapToUrlString(inputMap);
	}

	/**
	 * 返回结果处理
	 * 
	 * @param outputMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> outputParam(Map<String, String> outputMap) {
		Map<String, String> returnMap = new HashMap<String, String>();
		// 1、判断platCode是否为0
		if ("0".equals(outputMap.get("PlatCode"))) {
			// 2、签名验证
			Map<String, String> paramMap = MapUtil.getTreeMap();
			paramMap.put("SeqNo", outputMap.get("SeqNo"));
			paramMap.put("PlatCode", outputMap.get("PlatCode"));
			paramMap.put("PlatMsg", outputMap.get("PlatMsg"));
			paramMap.put("RetText", outputMap.get("RetText"));
			paramMap.put("SignType", outputMap.get("SignType"));
			paramMap.put("Ver", outputMap.get("Ver"));
			paramMap.put("ProtocolType", outputMap.get("ProtocolType"));
			if (outputMap.get("Sign").equals(Sha256.sha256(MapUtil.mapToUrlString(paramMap, null) + "&key=" + appKey))) {
				// 3、返回业务结果
				returnMap.put("FinalResult", "0");

                // 合并字段
				String[] aesKey = getAesKey();
				returnMap.putAll(MapConvert.jsonToStrMap(AesUtil.decrypt(outputMap.get("RetText"), aesKey[0], aesKey[1])));// aes解密
                if (!returnMap.get("result").equals("0")) {
                    returnMap.put("FinalResult", "1");
                    returnMap.put("FinalError", "result code: " + returnMap.get("result"));
                }

			} else {// 签名失败
				returnMap.put("FinalResult", "1");
				returnMap.put("FinalError", "sign mismatch");
			}
		} else {// 平台请求异常
            returnMap.put("FinalResult", "1");
            returnMap.put("FinalError", "PlatCode not 0");
		}

		return returnMap;
	}

	// 获取加密key
	private String[] getAesKey() {
		String allKey = MD5.parseStrToMd5L32(appKey + "_" + appId);
		return new String[] { allKey.substring(0, 16), allKey.substring(16, 32) };
	}

    // 查询卡 bin
    public Map<String, String> queryCardBin(String cardNumber) {
        log.info("query card bin: " + cardNumber);

        // 1、组装调用参数
        Map<String, String> inputMap = MapUtil.getTreeMap();
        // 1.1、组装业务参数(主要传递clientIp和ReqText信息、userInfo信息)
        inputMap.put("ClientIp", getLocalIp());

        // 对卡号打码，从第10位开始用0替换
        StringBuilder strBuilder = new StringBuilder(cardNumber.length());
        strBuilder.append(cardNumber.substring(0, 9));
        for (int i = 9; i < cardNumber.length(); i++) {
            strBuilder.append("0");
        }
        String cardNumberMasked = strBuilder.toString();
        
        // 1.1.1、获取ReqText信息
        Map<String, String> reqTextMap = MapUtil.getTreeMap();
        reqTextMap.put("bankacc_no", Des3Util.desEncode(cardNumberMasked, MD5.parseStrToMd5U32(cardNumberEncryptPasswd)));
        reqTextMap.put("client_ip", getLocalIp());

        String[] aesKey = getAesKey();
        inputMap.put("ReqText", AesUtil.encrypt(MapUtil.mapToUrlString(reqTextMap), aesKey[0], aesKey[1]));

        // 1.2、组装公共参数 and convert it to string
        String paramStr = inputParam(inputMap);

        // 2、调用接口
        Map<String, String> outputMap = FundTransferHttp.fundTransfer(cardbinQueryUrl, paramStr,
                                                                      Integer.parseInt(l5ModId),
                                                                      Integer.parseInt(l5CmdId), l5Timeout);

        // 3、返回结果处理
        Map<String, String> returnMap = new HashMap<String, String>();
        returnMap.putAll(outputParam(outputMap));

        log.info("returnMap: " + returnMap);
        return returnMap;
    }

    //发送短信验证码
    public Map<String, String> smsCodeSend(String mobileNo, String uin) {
        log.info("sms code send: " + mobileNo);
        // 1、组装调用参数
        Map<String, String> inputMap = MapUtil.getTreeMap();
        // 1.1、组装业务参数(主要传递clientIp和ReqText信息、userInfo信息)
        inputMap.put("ClientIp", getLocalIp());
        String[] aesKey = getAesKey();
        // 1.1.1、获取用户信息
        Map<String, String> userInfoMap = new HashMap<String, String>();
        userInfoMap.put("uin", ConfigUtils.getSmsUin());
        inputMap.put("UserInfo", AesUtil.encrypt(MapUtil.mapToUrlString(userInfoMap), aesKey[0], aesKey[1]));// aes加密传递的参数
        // 1.1.2、获取ReqText信息
        Map<String, String> reqTextMap = MapUtil.getTreeMap();
        reqTextMap.put("transaction_id", ConfigUtils.getSmsTransactionId());
        reqTextMap.put("mobile_no_type", "1");
        reqTextMap.put("mobile_no", mobileNo);
        reqTextMap.put("appid", ConfigUtils.getSmsAppid());//等待确认，需填数字

        reqTextMap.put("relation_key", ConfigUtils.getSmsRelationKey());//上面appid值为386时必填

        inputMap.put("ReqText", AesUtil.encrypt(MapUtil.mapToUrlString(reqTextMap), aesKey[0], aesKey[1]));

        // 1.2、组装公共参数 and convert it to string
        String paramStr = inputParam(inputMap);

        // 2、调用接口
        Map<String, String> outputMap = FundTransferHttp.fundTransfer(smsCodeSendUrl, paramStr,
                Integer.parseInt(l5ModId),
                Integer.parseInt(l5CmdId), l5Timeout);

        // 3、返回结果处理
        Map<String, String> returnMap = new HashMap<String, String>();
        returnMap.putAll(outputParam(outputMap));

        log.info("returnMap: " + returnMap);
        return returnMap;
    }

    //验证短信验证码
    public Map<String, String> smsCodeVerify(String mobileNo,String verifyCode, String fpgSmsGid,  String uin) {
        log.info("sms code verify: " + mobileNo+":"+verifyCode);
        // 1、组装调用参数
        Map<String, String> inputMap = MapUtil.getTreeMap();
        // 1.1、组装业务参数(主要传递clientIp和ReqText信息、userInfo信息)
        inputMap.put("ClientIp", getLocalIp());
        String[] aesKey = getAesKey();
        // 1.1.1、获取用户信息
        Map<String, String> userInfoMap = new HashMap<String, String>();
        userInfoMap.put("uin", ConfigUtils.getSmsUin());
        inputMap.put("UserInfo", AesUtil.encrypt(MapUtil.mapToUrlString(userInfoMap), aesKey[0], aesKey[1]));// aes加密传递的参数

        // 1.1.2、获取ReqText信息
        Map<String, String> reqTextMap = MapUtil.getTreeMap();
        reqTextMap.put("transaction_id", ConfigUtils.getSmsTransactionId());
        reqTextMap.put("mobile_no_type", "1");
        reqTextMap.put("mobile_no", mobileNo);
        reqTextMap.put("appid", ConfigUtils.getSmsAppid());
        reqTextMap.put("relation_key", ConfigUtils.getSmsRelationKey());//上面appid值为386时必填
        reqTextMap.put("client_ip", getLocalIp());
        reqTextMap.put("verify_code", verifyCode);
        reqTextMap.put("fpg_sms_gid", fpgSmsGid);

        inputMap.put("ReqText", AesUtil.encrypt(MapUtil.mapToUrlString(reqTextMap), aesKey[0], aesKey[1]));

        // 1.2、组装公共参数 and convert it to string
        String paramStr = inputParam(inputMap);

        // 2、调用接口
        Map<String, String> outputMap = FundTransferHttp.fundTransfer(smsCodeVerifyUrl, paramStr,
                Integer.parseInt(l5ModId),
                Integer.parseInt(l5CmdId), l5Timeout);

        // 3、返回结果处理
        Map<String, String> returnMap = new HashMap<String, String>();
        returnMap.putAll(outputParam(outputMap));

        log.info("returnMap: " + returnMap);
        return returnMap;
    }

    /*

	// 获取支付订单号(返回结果用utf-8处理)
	public Map<String, String> obtainTransactoinId(Map<String, String> paramMap) {
		log.info("获取支付订单号接口FundTransferService.obtainTransactoinId，传递参数：" + paramMap);
		// 返回处理结果
		Map<String, String> returnMap = new HashMap<String, String>();
		// 1、组装调用参数
		Map<String, String> inputMap = MapUtil.getTreeMap();
		// 1.1、组装业务参数(主要传递clientIp和ReqText信息、userInfo信息)
		// 1.1.1、获取ReqText信息
		inputMap.put("ClientIp", paramMap.get("userIp"));
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("partner", paramMap.get("sellSpId"));// 收款商户号
		reqMap.put("pay_uin", paramMap.get("buyUin"));// 付款uin帐号
		reqMap.put("sp_billno", paramMap.get("spBillNo"));// 商户订单号
		String[] aesKey = getAesKey();
		inputMap.put("ReqText", AesUtil.encrypt(MapUtil.mapToUrlString(reqMap), aesKey[0], aesKey[1]));// aes加密传递的参数
		// 1.1.2、获取用户信息
		Map<String, String> userInfoMap = new HashMap<String, String>();
		userInfoMap.put("uin", paramMap.get("buyUin"));
		inputMap.put("UserInfo", AesUtil.encrypt(MapUtil.mapToUrlString(userInfoMap), aesKey[0], aesKey[1]));// aes加密传递的参数
		// 1.2、组装公共参数
		String paramStr = inputParam(inputMap);
		// 2、调用接口
		Map<String, String> outputMap = FundTransferHttp.fundTransfer(TRANSANCTION_URL, TRANSANCTION_L5_URL, paramStr);
		// 3、返回结果处理
		returnMap.putAll(outputParam(outputMap));
		if (ResultMessageEnum.RESULT_CODE_SUC.getKey().equals(returnMap.get(ResultMessageEnum.RESULT_CODE.getKey()))) {
			if (returnMap.containsKey("result") && "0".equals(returnMap.get("result")) 
					&& returnMap.containsKey("transaction_id") && StrUtil.isNotEmpty(returnMap.get("transaction_id"))) {
				// 成功不做处理
			} else {
				returnMap.put(ResultMessageEnum.RESULT_CODE.getKey(), ResultMessageEnum.RESULT_CODE_FAIL.getKey());
				returnMap.put(ResultMessageEnum.RESULT_MESSAGE.getKey(), "平台请求异常");
			}
		}
		log.info("获取支付订单号接口FundTransferService.obtainTransactoinId，调用结果：" + returnMap);
		return returnMap;
	}

	// 实现c2b支付接口(返回结果用gbk处理)
	public Map<String, String> dealC2B(Map<String, String> paramMap) {
		log.info("调用c2b支付接口FundTransferService.dealC2B，传递参数：" + paramMap);
		// 返回处理结果
		Map<String, String> returnMap = new HashMap<String, String>();
		// 1、组装调用参数
		Map<String, String> inputMap = MapUtil.getTreeMap();
		// 1.1、组装业务参数(主要传递clientIp和ReqText信息、userInfo信息)
		// 1.1.1、获取ReqText信息
		inputMap.put("ClientIp", paramMap.get("userIp"));
		Map<String, String> reqMap = MapUtil.getTreeMap();
		reqMap.put("transaction_id", paramMap.get("transactionId"));// 财付通订单号
		reqMap.put("sp_billno", paramMap.get("spBillNo"));// 商户订单号
		if ("1".equals(paramMap.get("payType"))) {// 1:运营C,2:现金C
			reqMap.put("passwd_type", "1");
			reqMap.put("op_id", paramMap.get("buyOpId"));
		} else if ("1".equals(paramMap.get("payType"))) {
			reqMap.put("passwd_type", "2");
			reqMap.put("op_id", paramMap.get("buyOpId"));// 商户号登录帐号
		}
		reqMap.put("pay_uin", paramMap.get("buyUin"));// 买家帐号
		reqMap.put("purchaser_spid", paramMap.get("buySpId"));// 买家商户号
		reqMap.put("partner", paramMap.get("sellSpId"));// 卖家商户号
		reqMap.put("total_fee", paramMap.get("rechargeAmount"));
		reqMap.put("fee_type", "1");// 人民币
		reqMap.put("pay_passwd", Des3Util.desEncode(MD5.parseStrToMd5L32(paramMap.get("buyPassword")).trim(), MD5.parseStrToMd5U32(MD5_KEY)));// 运营C:支付密码,现金C:操作员密码
		reqMap.put("accept_true_name", paramMap.get("sellName"));// 卖方用户名称
		reqMap.put("desc", paramMap.get("desc"));// 交易描述
		Date now = new Date();
		reqMap.put("sp_time_stamp", now.getTime() / 1000 + "");
		reqMap.put("channel_id", "129");// 第三方平余额支付
		reqMap.put("sign", MD5.parseStrToMd5L32(MapUtil.mapToUrlString(reqMap, null) + "&key=" + paramMap.get("sellPassword")));
		String[] aesKey = getAesKey();
		inputMap.put("ReqText", AesUtil.encrypt(MapUtil.mapToUrlString(reqMap), aesKey[0], aesKey[1]));
		// 1.1.2、获取用户信息
		Map<String, String> userInfoMap = new HashMap<String, String>();
		userInfoMap.put("uin", paramMap.get("buyUin"));
		inputMap.put("UserInfo", AesUtil.encrypt(MapUtil.mapToUrlString(userInfoMap), aesKey[0], aesKey[1]));// aes加密传递的参数
		// 1.2、组装公共参数
		String paramStr = inputParam(inputMap);
		// 2、调用接口
		Map<String, String> outputMap = FundTransferHttp.fundTransfer(PAY_URL ,PAY_L5_URL , paramStr);
		// 3、返回结果处理
		returnMap.putAll(outputParam(outputMap));
		if (ResultMessageEnum.RESULT_CODE_SUC.getKey().equals(returnMap.get(ResultMessageEnum.RESULT_CODE.getKey()))) {
			// 判断调用状态、买家帐号、卖家帐号、财付通订单号、商户订单号、支付状态、交易金额是否和传递的一致
			if (returnMap.containsKey("result") && "0".equals(returnMap.get("result")) 
					&& returnMap.containsKey("status") && FundTradeStatusEnum.PAY_SUC.getKey().equals(returnMap.get("status")) 
					&& paramMap.get("buyUin").equals(returnMap.get("buy_uin")) 
					&& paramMap.get("sellSpId").equals(returnMap.get("sale_uin")) 
					&& paramMap.get("transactionId").equals(returnMap.get("transaction_id"))
					&& paramMap.get("rechargeAmount").equals(returnMap.get("fee")) 
					&& paramMap.get("spBillNo").equals(returnMap.get("sp_billno"))) {
				// 成功不做处理
				// balance_uid余额信息,能否查看买方的C帐号余额
			} else {
				returnMap.put(ResultMessageEnum.RESULT_CODE.getKey(), ResultMessageEnum.RESULT_CODE_FAIL.getKey());
				if (returnMap.containsKey("status")) {
					returnMap.put(ResultMessageEnum.RESULT_MESSAGE.getKey(), FundTradeStatusEnum.getValue(returnMap.get("status")));
				} else {
					returnMap.put(ResultMessageEnum.RESULT_MESSAGE.getKey(), "平台请求异常");
				}
			}
		}
		log.info("调用c2b支付接口FundTransferService.dealC2B，调用结果：" + returnMap);
		return returnMap;
	}

	// 实现c2b查单接口
	public Map<String, String> queryOrder(Map<String, String> paramMap) {
		log.info("调用c2b查单接口FundTransferService.queryOrder，传递参数：" + paramMap);
		// 返回处理结果
		Map<String, String> returnMap = new HashMap<String, String>();
		// 1、组装调用参数
		Map<String, String> inputMap = MapUtil.getTreeMap();
		// 1.1、组装业务参数(主要传递clientIp和ReqText信息、userInfo信息)
		// 1.1.1、获取ReqText信息
		inputMap.put("ClientIp", paramMap.get("userIp"));
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("transaction_id", paramMap.get("transactionId"));// 财付通订单号
		String[] aesKey = getAesKey();
		inputMap.put("ReqText", AesUtil.encrypt(MapUtil.mapToUrlString(reqMap), aesKey[0], aesKey[1]));
		// 1.1.2、获取用户信息
		Map<String, String> userInfoMap = new HashMap<String, String>();
		userInfoMap.put("uin", paramMap.get("buyUin"));
		inputMap.put("UserInfo", AesUtil.encrypt(MapUtil.mapToUrlString(userInfoMap), aesKey[0], aesKey[1]));// aes加密传递的参数
		// 1.2、组装公共参数
		String paramStr = inputParam(inputMap);
		// 2、调用接口
		Map<String, String> outputMap = FundTransferHttp.fundTransfer(QUERY_ORDER_URL, QUERY_ORDER_L5_URL, paramStr);
		// 3、返回结果处理
		returnMap.putAll(outputParam(outputMap));
		if (ResultMessageEnum.RESULT_CODE_SUC.getKey().equals(returnMap.get(ResultMessageEnum.RESULT_CODE.getKey()))) {
			// 判断调用状态、买家帐号、卖家帐号、财付通订单号、商户订单号、支付状态(包含支付成功和退款成功)、交易金额是否和传递的一致
			if (returnMap.containsKey("result") && "0".equals(returnMap.get("result"))
					&& returnMap.containsKey("trade_status")
					&& (FundTradeStatusEnum.PAY_SUC.getKey().equals(returnMap.get("trade_status")) 
							|| FundTradeStatusEnum.RET.getKey().equals(returnMap.get("trade_status"))) 
					&& paramMap.get("buyUin").equals(returnMap.get("purchaser_id"))
					&& paramMap.get("sellSpId").equals(returnMap.get("bargainor_id")) 
					&& paramMap.get("transactionId").equals(returnMap.get("transaction_id"))
					&& paramMap.get("rechargeAmount").equals(returnMap.get("total_fee")) 
					&& paramMap.get("spBillNo").equals(returnMap.get("sp_billno"))) {
				// 成功不做处理
			} else {
				returnMap.put(ResultMessageEnum.RESULT_CODE.getKey(), ResultMessageEnum.RESULT_CODE_FAIL.getKey());
				if (returnMap.containsKey("trade_status")) {
					returnMap.put(ResultMessageEnum.RESULT_MESSAGE.getKey(), FundTradeStatusEnum.getValue(returnMap.get("trade_status")));
				} else {
					returnMap.put(ResultMessageEnum.RESULT_MESSAGE.getKey(), "平台请求异常");
				}
			}
		}
		log.info("调用c2b查单接口FundTransferService.queryOrder，传递参数：" + paramMap);
		return returnMap;
	}

	// 实现c2b退款接口
	public Map<String, String> refund(Map<String, String> paramMap) {
		log.info("调用c2b退款接口FundTransferService.refund，传递参数：" + paramMap);
		// 返回处理结果
		Map<String, String> returnMap = new HashMap<String, String>();
		// 1、组装调用参数
		Map<String, String> inputMap = MapUtil.getTreeMap();
		// 1.1、组装业务参数(主要传递clientIp和ReqText信息、userInfo信息)
		// 1.1.1、获取ReqText信息
		inputMap.put("ClientIp", paramMap.get("userIp"));
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("merchant_spid", paramMap.get("sellSpId"));// 卖家商户号
		reqMap.put("transaction_id", paramMap.get("transactionId"));// 财付通订单号
		reqMap.put("out_refund_id", paramMap.get("transactionId"));
		reqMap.put("sp_refund_id", paramMap.get("spBillNo"));
		reqMap.put("fee_type", "1");// 人名币类型
		reqMap.put("refund_fee", paramMap.get("rechargeAmount"));// 退款金额
		reqMap.put("total_fee", paramMap.get("rechargeAmount"));// 总金额
		reqMap.put("client_ip", paramMap.get("userIp"));// 发起退款方ip
		String[] aesKey = getAesKey();
		inputMap.put("ReqText", AesUtil.encrypt(MapUtil.mapToUrlString(reqMap), aesKey[0], aesKey[1]));
		// 1.1.2、获取用户信息
		Map<String, String> userInfoMap = new HashMap<String, String>();
		userInfoMap.put("uin", paramMap.get("buyUin"));
		inputMap.put("UserInfo", AesUtil.encrypt(MapUtil.mapToUrlString(userInfoMap), aesKey[0], aesKey[1]));// aes加密传递的参数
		// 1.2、组装公共参数
		String paramStr = inputParam(inputMap);
		// 2、调用接口
		Map<String, String> outputMap = FundTransferHttp.fundTransfer(REFUND_URL ,REFUND_L5_URL, paramStr);
		// 3、返回结果处理
		returnMap.putAll(outputParam(outputMap));
		if (ResultMessageEnum.RESULT_CODE_SUC.getKey().equals(returnMap.get(ResultMessageEnum.RESULT_CODE.getKey()))) {
			// 判断调用状态和退款状态
			if (returnMap.containsKey("result") && "0".equals(returnMap.get("result")) 
					&& returnMap.containsKey("refund_status") && FundTradeStatusEnum.RET.getKey().equals(returnMap.get("refund_status"))) {
				// 成功不做处理
			} else {
				returnMap.put(ResultMessageEnum.RESULT_CODE.getKey(), ResultMessageEnum.RESULT_CODE_FAIL.getKey());
				if (returnMap.containsKey("refund_status")) {
					returnMap.put(ResultMessageEnum.RESULT_MESSAGE.getKey(), FundTradeStatusEnum.getValue(returnMap.get("refund_status")));
				} else {
					returnMap.put(ResultMessageEnum.RESULT_MESSAGE.getKey(), "平台请求异常");
				}
			}
		}
		log.info("调用c2b退款接口FundTransferService.refund，传递参数：" + paramMap);
		return returnMap;
	}
    */
}
