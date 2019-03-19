package com.tenpay.wxwork.wxworklib.exception;

public class WxworkException extends RuntimeException {

    // 错误码
    private int errorCode;

    private String errorMsg;

    // public WxworkException(WxworkErr error) {
    //     super(error.getMsg());
    //     this.errorCode = error.getCode();
    //     this.errorMsg = error.getMsg();
    // }

    // /**
    //  * @param error    错误类型
    //  * @param extraMsg 额外补充错误信息
    //  */
    // public WxworkException(WxworkErr error, String extraMsg) {
    //     super(error.getMsg() + ":" + extraMsg);
    //     this.errorCode = error.getCode();
    //     this.errorMsg = error.getMsg() + ":" + extraMsg;
    // }

    public WxworkException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 获取错误码
     *
     * @return
     */
    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
