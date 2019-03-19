package com.tenpay.wxwork.salary.common;

public enum BizError {

    // -------------系统错误码-----------------------
    // 银行渠道配置异常或调整
    INVALID_CONFIG(0, "invalid config."),
    NO_FIND_ERROR(1, "没有找到错误信息"),
    // 调用relay失败
    RELAY_CALL_ERROR(2, "调用relay失败"),
    // 调用middle失败
    MIDDLE_CALL_ERROR(3, "调用middle失败"),
    //bean转换为map失败
    TRANSFER_BEAN2MAP_ERROR(4, "Failed to transfer bean to map."),
    //http请求协议参数错误
    CLIENT_PROTOCOL_ERROR(5, "Http client protocol error."),
    //http请求网络错误
    HTTP_IO_ERROR(6, "Http io error."),
    // 发送http请求失败
    HTTP_CLIENT_ERROR(7, "发送http请求失败"),
    //请求转换json失败
    OBJECT2STR_CONVERT_ERROR(9, "Object converted to json error."),
    //http请求协议参数错误
    ILLEGAL_HTTARG_ERROR(10, "illegal http(s) argument."),
    //请求银行的url非法
    ILLEGAL_LOANURL_ERROR(11, "loan bank url illegal."),
    //银行返回失败
    GET_BANK_RESPONSE_FAILED(12, "Get Bank response failed."),
    // 银行信息不存在
    BANK_INFO_NOT_EXIST(13, "Bank info not exist."),

    RSA_DECRYPT_ERROR(14, "RSA解密失败"),
    RSA_ENCRYPT_ERROR(15, "RSA加密失败"),
    AES_ENCRYPT_ERROR(16, "failed to encrypt using AES."),
    AES_DECRYPT_ERROR(17, "failed to decrypt using AES."),
    GEN_RANDOM_ERROR(18, "failed to generate random number"),

    PARAM_TOO_LONG(19, "param too long: "),
    PARAM_TOO_SHORT(20, "param too short: "),
    PARAM_INVALID(21, "param invalid: "),
    ILLEGAL_ARGUMENT(22, "illegal argument exception: "),
    SET_CKV_FAIL(23, "failed to set ckv"),
    GET_CKV_FAIL(23, "failed to get ckv"),
    UNKOWN_ERROR(24, "系统繁忙，请稍后再试！"),
    XML_MSG_ERROR(25, "XML 报文格式不正确"),
    SIGN_NOT_MATCH(26, "签名不匹配"),
    JSON_MSG_ERROR(27, "json报文错误"),
    SIGN_DATA_ERROR(28, "签名失败"),
    CHECK_SIGN_ERROR(29, "验签异常"),
    COMPRESS_VIDEO_ERROR(30, "压缩视频出错"),
    // ---- 业务错误
    OCR_FAIL(1000, "failed to ocr: "),
    LIVENESS_DETECTION_NUMBER_FAIL(1001, "failed to get liveness detection number"),
    // LIVENESS_DETECTION_FAIL(1002, "failed to do liveness detection"),
    INVALID_LIVENESS_DETECTION_NUMBER(1003, "invalid liveness detection number"),
    BANK_NUMBER_NOT_FOUND(1004, "no bank number found for bank:"),
    BANK_NAME_NOT_FOUND(1004, "no bank name found for bank:"),
    CARDBIN_QUERY(1005, "failed to query cardbin"),
    NO_CORP_AUTHEN(1006, "no corp auth record"),
    NO_CORP_USER(1007, "no corp user"),
    NO_APP_INFO(1008, "no app info"),
    OCR_FRONT_FAIL(1009, "failed to ocr: front photo"),
    OCR_BACK_FAIL(1010, "failed to ocr: back photo"),
    SMS_CODE_SEND_FAIL(1011, "failed to sms code send"),
    SMS_CODE_VERIFY_FAIL(1012, "failed to sms code verfiy"),
    NO_REGISTER_CODE(1013, "no register code"),
    MCH_INFO_NOT_EXIST(1014, "金融机构不存在"),

    // --------前置机与bank_proxy及后台交互产生的错误及基本的业务错误码--------------
    //不支持的银行类型
    UNSUPPORTED_BANK(1000, "不支持的银行类型."),
    // 不支持的请求类型
    UNSUPPORT_REQUEST(1001, "不支持的请求类型"),
    // 参数未输入
    PARAM_NOT_EXIST(1002, "参数未输入"),
    // 银行类型不存在
    BANK_TYPE_NOT_EXIST(1003, "银行类型不存在"),
    // 非法域
    ILLEGAL_FIELDS(1004, "非法域"),
    //返回报文没有签名字段
    LACK_SIGN_ERROR(1005, "lack sign field."),
    //获取私钥失败
    GET_PRIKEY_ERROR(1006, "Failed to get private key."),
    //签名源串为空
    SIGNSRC_EMPTY_ERROR(1007, "Sign src is empty."),
    //返回信息关键信息不一致
    KEYMSG_CONSISTENCY_ERROR(1008, "请求报文与接收报文的关键信息不一致."),
    //不支持的签名算法
    SIGN_TYPE_ERROR(1011, "签名算法错误."),
    //不支持的padding方式
    PADDING_TYPE_ERROR(1012, "padding方式错误."),
    //key非法
    INVALID_KEY_ERROR(1013, "加密或解密的key非法"),
    //key非法
    ILLEGAL_BLOCK_ERROR(1014, "加密块非法"),
    //不支持的padding方式
    BAD_PADDING_ERROR(1015, "明文数据已破坏."),
    //验证签名失败
    VERIFY_SIGN_ERROR(1016, "验证银行签名失败"),
    //不支持的padding方式
    UNSUPPORTED_ENCODING_ERROR(1017, "不支持的编码方式."),
    //生成签名失败
    GEN_SIGN_ERROR(1018, "生成签名失败"),
    //生成签名失败
    ENCODE_SIGN_ERROR(1019, "签名base64编码失败"),
    //请求转换json失败
    JSON_CONVERT_ERROR(1020, "json转换失败"),
    //报文时间戳过期
    PACKAGE_TIMESTAMP_ERROR(1021, "报文时间戳过期."),
    //缺少bank_name
    LACK_BANKNAME_ERROR(1022, "lack of bank name."),
    //获取银行证书失败
    GET_BANK_CERT_ERROR(1023, "Failed to get bank cert."),
    //银行返回信息异常
    INVALID_BANK_RETURN(1024, "银行返回信息异常"),
    // 银行返回报文字段解析失败
    MISSING_REQUIRED_FIELD(1025, "银行返回报文字段缺失"),
    // 不支持的签名算法
    UNSUPPORT_SIGN_TYPE(1026, "不支持的签名算法"),

    // --------银行返回的业务错误码--------------
    //1)程序类错误
    // 报文格式错误
    PACKAGE_FORMAT_ERROR(3001, "银行报错报文格式错误"),
    // 必填域缺失
    LACK_REQURIED_FILED(3002, "银行报错报文格式错误"),
    // 验证码错误
    VERIFYCODE_ERROR(3003, "验证码错误"),
    //验证签名失败
    BANK_VERIFY_SIGN_ERROR(3004, "银行验证签名失败"),
    //通讯公钥异常
    CERT_ABNORMAL(3005, "通讯公钥异常"),
    //文件格式不正确
    FILE_FORMAT_ERROR(3006, "文件格式不正确"),
    //业务日期不正确
    BIZ_DATE_ERROR(3007, "业务日期不正确"),
    //文件已锁定
    FILE_LOCKED(3008, "文件已锁定"),
    //文件不存在
    FILE_NOT_EXIST(3009, "文件不存在"),
    //文件无法解密
    FILE_DECRYPT_ERROR(3010, "文件无法解密"),
    //文件已处理
    FILE_ALREADY_HANDLED(3011, "文件已处理"),
    //文件无法解压缩
    FILE_CANNT_DECOMPRESS(3012, "文件无法解压缩"),
    //文件无法删除
    FILE_CANNT_DELETE(3013, "文件无法删除"),
    //文件摘要不正确
    FILE_ABSTRACT_ERROR(3014, "文件摘要不正确"),
    //文件摘要不正确
    FILE_HANDLE_ERROR(3015, "文件读取处理失败"),
    //无接口码
    INVALID_INTERFACE_CODE(3016, "无接口码"),

    // -----------业务错误

    UNSUPPORTED_APPROVAL_TYPE_ERROR(3301, "Unsupported approval type."),

    UNSUPPORTED_RELATION_STATUS_ERROR(3302, "Unsupported corpAppRelation status."),

    PARAM_INCONSISTENCE(3303, "Params inconsistence."),

    CORP_INFO_NOT_EXIST(3304, "Corpinfo not exist."),

    CORP_APP_RELATION_NOT_EXIST(3305, "CorpAppRelation not exist."),

    OP_USER_ID_NOT_ADMIN(3306, "opUserId is not admin."),

    UNSUPPORTED_APPID_ERROR(3307, "Unsupported Appid."),

    CORP_APPRAVAL_INFO_NOT_EXIST(3308, "CorpAppraval Info not exist."),

    ERROR_APPRAVAL_STATUS(3309, "Approval status error."),

    UNSUPPORTED_PAY_STATUS(3310, "Unsupported pay status."),

    BAD_REQUEST(3311, "bad Request."),

    FAILED_TO_GET_ADMINLIST(3312, "Failed to get Adminlist from wxwork."),

    MYBATIS_CONFIG_ERROR(3313, "mybatis config error."),

    SQL_GRAMMAR_ERROR(3314, "MySQL grammar error."),


    UNCATEGORIZED_SQL_ERROR(3315,"Uncategorized SQL."),

    DATE_FORMAT_ERROR(3316, "Date Convert Format Error"),

    CORP_RELATION_AUTHEN_FAILED(3317, "CORP RELATION AUTHEN FAILED"),

    UN_SUPPORT_APPROVAL_INFO_TYPE(3318, "NOT SUPPORT APPROVAL INFO TYPE"),

    BUILD_CORPAPPROVALINFO_FAIL(3319, "BUILD CORPAPPROVALINFO FAIL"),

    LOGIN_FAILED(3320, "login failed."),

    UNSUPPORTED_RELATION_AUTHEN_STATUS_ERROR(3321, "Unsupported corpAppRelation authen status."),

    APPROVAL_INFO_NOT_EXIST_ERROR(3322, "Approval info not exist."),

    PARSE_APPROVAL_DATA_USER_ACOUNT_FAILED(3323, "parse approval data user account failed"),
	
	FGESTURE_PASSWORD_NOT_EXIST(3324, "parse approval data user account failed"),

    NOT_GET_ACCONT_INFO(3325, "未获取到用户信息！"),

    BALANCE_IS_EMPTY(3326,"未获取到余额"),

    //--------------返回给用用户的错误信息 以4开头
    //银行返回的错误用此错误吗，内容为银行返回的内容
    BANK_ERROR_MES(4001, "交易请求失败！"),

    GET_SESSION_FAILED(4002, "登录超时，请重新登录！"),

    FETCH_AMOUNT_BIG_THAN_BALANCE(4005,"转账金额不能大于余额"),

    FETCH_AMOUNT_TOO_LARGE(4006,"转账金额不能大于50000"),

    FETCH_AMOUNT_IS_EMPTY(4007,"转账金额不能为空"),

    SEND_SMS_SCENE_ERROR(4008,"发送验证码失败！"),

    FETCH_AMOUNT_ERROR(4009,"转账金额不能小于0"),

    PASSWORD_ERROR(4010,"手势密码错误！"),

    // -----------文件上传业务错误
    UPLOAD_FILE_EXCEL_ERR(4011,"文件格式错误！"),

    UPLOAD_FILE_EXCEL_CONTENT_NULL(4012,"文件内容不能为空！"),

    PLEASE_SELECT_MONTH(4013,"请选择月份！"),

    LIVENESS_DETECTION_FAIL(4014, "视频拍摄不通过，请重新拍摄！"),

    ERR_MOUTH_FAIL(4015, "请重新念出下方数字，嘴唇一定要动哦！"),

    BANK_CONF_ERROR(4016, "请联系系统管理员配置银行信息！"),

    CORP_CONF_ERROR(4017, "请联系企业管理员配置企业信息！"),

    NO_DEPARTMENT_INFO(4018, "未找到部门信息，请检查用户名是否正确"),

    BANK_CARD_ERROR(4019, "请输入银行卡号！"),

    //返回给用户的缺省错误，
    DEFAULT_ERROR_MSG(4999, "系统繁忙，请稍后再试！"),

    // 缺省错误码:系统繁忙
    DEFAULT_ERROR(9999, "system busy.");

    private int errorCode;

    private String errMsg;

    BizError(int errorCode, String errMsg) {
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

    public int errorCode() {
        return errorCode;
    }

    public String errorMsg() {
        return errMsg;
    }

    public String toString() {
        return String.valueOf(this.errorCode + ":" + this.errMsg);
    }
}
