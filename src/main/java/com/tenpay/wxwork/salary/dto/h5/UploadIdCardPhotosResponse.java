package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;

public class UploadIdCardPhotosResponse extends FrontEndResponse
{
    public UploadIdCardPhotosResponse(String idCardName, String idCardNumber)
    {
        super();

        this.idCardName = idCardName;
        this.idCardNumber = idCardNumber;
    }

    @JsonProperty(value="id_card_number")
    private String idCardNumber;

    @JsonProperty(value="id_card_name")
    private String idCardName;

    public String getIdCardName()
    {
        return idCardName;
    }

    public void setIdCardName(String idCardName)
    {
        this.idCardName = idCardName;
    }

    public String getIdCardNumber()
    {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber)
    {
        this.idCardNumber = idCardNumber;
    }
}
