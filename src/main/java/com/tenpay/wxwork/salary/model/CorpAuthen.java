package com.tenpay.wxwork.salary.model;

import java.io.Serializable;

public class CorpAuthen implements Serializable {
	private static final long serialVersionUID = 4666191834606998871L;

    // 企业授权状态1：未授权2：已授权3：解除授权
    public static int AUTH_STATUS_UNKNOWN = 1;
    public static int AUTH_STATUS_AUTHED = 2;
    public static int AUTH_STATUS_REVOKED = 3;
	
    private String corpid;

    /**
     * 就是企业微信中的第三方应用 suite id
     *
     */
    private String platAppId;

    /**
     * suite id 授权给企业之后，会产生一个 agent id ，每个企业不同。
     *
     */
    private int agentId;

    private String authStatus;

    private String permanentCode;

    private String authOkTime;

    private String appLevel;

    private String allowParty;

    private String allowTag;

    private String allowUser;

    private String extraParty;

    private String extraTag;

    private String extraUser;

    private String createTime;

    private String modifyTime;

    public String getModifyTime()
    {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime)
    {
        this.modifyTime = modifyTime;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getExtraUser()
    {
        return extraUser;
    }

    public void setExtraUser(String extraUser)
    {
        this.extraUser = extraUser;
    }

    public String getExtraTag()
    {
        return extraTag;
    }

    public void setExtraTag(String extraTag)
    {
        this.extraTag = extraTag;
    }

    public String getExtraParty()
    {
        return extraParty;
    }

    public void setExtraParty(String extraParty)
    {
        this.extraParty = extraParty;
    }

    public String getAllowUser()
    {
        return allowUser;
    }

    public void setAllowUser(String allowUser)
    {
        this.allowUser = allowUser;
    }

    public String getAllowTag()
    {
        return allowTag;
    }

    public void setAllowTag(String allowTag)
    {
        this.allowTag = allowTag;
    }

    public String getAllowParty()
    {
        return allowParty;
    }

    public void setAllowParty(String allowParty)
    {
        this.allowParty = allowParty;
    }

    public String getAppLevel()
    {
        return appLevel;
    }

    public void setAppLevel(String appLevel)
    {
        this.appLevel = appLevel;
    }

    public String getAuthOkTime()
    {
        return authOkTime;
    }

    public void setAuthOkTime(String authOkTime)
    {
        this.authOkTime = authOkTime;
    }

    public String getPermanentCode()
    {
        return permanentCode;
    }

    public void setPermanentCode(String permanentCode)
    {
        this.permanentCode = permanentCode;
    }

    public String getAuthStatus()
    {
        return authStatus;
    }

    public void setAuthStatus(String authStatus)
    {
        this.authStatus = authStatus;
    }

    public String getPlatAppId()
    {
        return platAppId;
    }

    public void setPlatAppId(String platAppId)
    {
        this.platAppId = platAppId;
    }

    public int getAgentId()
    {
        return agentId;
    }

    public void setAgentId(int agentId)
    {
        this.agentId = agentId;
    }

    public String getCorpid()
    {
        return corpid;
    }

    public void setCorpid(String corpid)
    {
        this.corpid = corpid;
    }
}
