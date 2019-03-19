package com.tenpay.wxwork.salary.presvr.sender.bean;

import org.apache.commons.lang3.StringUtils;

import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;

/**
 * server 过来的请求包
 *
 */
public class UploadIdCardPhotoReq extends BankProxyRelayRequestMsg {

    private static final long serialVersionUID = 6196176837783687096L;

    private static final String FILE_DATE = "file_date";
    private static final String FILE_NAME = "file_name";

    private String fileDate;

    private String fileName;

    public UploadIdCardPhotoReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
        // this.validate();
    }

    public void setRequest(String fileDate, String fileName) {
        setFileDate(fileDate);
        setFileName(fileName);
        validate();
    }

    private void validate() {
        if(StringUtils.isBlank(this.getFileDate())){
            throw new IllegalArgumentException("file date is empty.");
        }

        if(StringUtils.isBlank(this.getFileName())){
            throw new IllegalArgumentException("file name is empty.");
        }
    }

    public String getFileName()
    {
        return (this.fileName = this.get(FILE_NAME));
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
        this.put(FILE_NAME, fileName);
    }

    public String getFileDate()
    {
        return (this.fileDate = this.get(FILE_DATE));
    }

    public void setFileDate(String fileDate)
    {
        this.fileDate = fileDate;
        this.put(FILE_DATE, fileDate);
    }
}
