package com.tenpay.wxwork.salary.common;

/**
 * Created by Sean Lei on 30/11/2016.
 */

/**
 * 业务异常类
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 5930074539261049999L;
	private int retCode;
    private String errMsg;

    public BizException(int retCode, String errMsg) {
        super(errMsg);
        this.retCode = retCode;
        this.errMsg = errMsg;
    }

    public BizException(BizError error) {
        super(error.errorMsg());
        this.retCode = error.errorCode();
        this.errMsg = error.errorMsg();
    }

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
