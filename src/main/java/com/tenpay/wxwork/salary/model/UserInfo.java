package com.tenpay.wxwork.salary.model;

/**
 * t_user_info
 *
 */
public class UserInfo {
    private String corpid;

    private String userid;

    private String name;

    private String status;

    private String department;

    private String position;

    private String mobile;

    private String gender;

    private String email;

    private String avatar;

    private String lastLogintime;

    private String lastPlatAppid;

    private String createTime;

    private String modifyTime;

    private String sequence_number;

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

    public String getLastPlatAppid()
    {
        return lastPlatAppid;
    }

    public void setLastPlatAppid(String lastPlatAppid)
    {
        this.lastPlatAppid = lastPlatAppid;
    }

    public String getLastLogintime()
    {
        return lastLogintime;
    }

    public void setLastLogintime(String lastLogintime)
    {
        this.lastLogintime = lastLogintime;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUserid()
    {
        return userid;
    }

    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getCorpid()
    {
        return corpid;
    }

    public void setCorpid(String corpid)
    {
        this.corpid = corpid;
    }

    public String getSequence_number() {
        return sequence_number;
    }

    public void setSequence_number(String sequence_number) {
        this.sequence_number = sequence_number;
    }
}