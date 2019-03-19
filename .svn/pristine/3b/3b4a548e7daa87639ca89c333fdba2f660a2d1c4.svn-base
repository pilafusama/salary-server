package com.tenpay.wxwork.salary.model;

import com.tenpay.wxwork.salary.model.SalaryAccount.CreGender;
import com.tenpay.wxwork.salary.model.SalaryAccount.CreType;
import com.tenpay.wxwork.salary.model.SalaryAccount.OpeningType;
import com.tenpay.wxwork.salary.model.SalaryAccount.State;

/**
 * @author : CZK
 * @description : 数据库表t_salary_account的实体类
 * @date : 2018/8/22
 */
public class AccountInfo
{
	private int fsalaryAccountId;
	private String fcorpid;//企业id
	private String fuserid;//用户id
	private State fstate;//开户状态
	private OpeningType fopeningType;//开户类型
	private String fsalaryCardNumber;//工资卡号（二类户）
	private String fsalaryCardBankType;//工资卡发卡行 bank_type
	private String fsalaryCardBankSname;//工资卡发卡银行sname
	private String fsalaryCardOpenedTime;//银行的工资卡开户时间戳
	private String fgesturePassword;//手势密码
	private String fmobileNumber;//手机号码
	private CreType fcreType;//证件类型
	private String fcreId;//证件号码
	private String fcreName;//证件姓名
	private CreGender fcreGender;//证件性别
	private String fcreNationality;//证件民族
	private String fcreBirthdate;//证件生日
	private String fcreAddress;//证件住址
	private String fcreIssueAuthority;//证件发证机关
	private String fcreValidDate;//证件有效期起始
	private String fcreExpireDate;//证件有效期截止
	private byte[] fcreFrontPhoto;//证件正面照
	private byte[] fcreBackPhoto;//证件背面照
	private String flivenessDetectionNumber;//活体检测数字
	private byte[] flivenessDetectionVideo;//活体检测视频
	private byte[] ffaceRecognitionPhoto;//人脸识别照片
	private String fbindCardNumber;//绑定的银行卡号（一类户）
	private String fbindCardBankSname;//绑定银行卡的银行sname
	private String fbindCardBankNumber;//绑定银行卡的开户行联行号
	private byte[] fbindCardPhoto;//银行卡照片
	private String fcreateTime;//创建时间
	private String fmodifyTime;//最后更新时间
	/*
	 * fstate字段因需要枚举类型State的 name()方法和toInt()方法，返回State类型
	 */
	public State getFstate()
	{
		return fstate;
	}
	/*
	 * 数据库查询Fstate字段,如select Fstate as Fstate from dual;
	 * myBatis通过as Fstate寻找返回类的字段Fstate的set方法赋值,查询结果作参数
	 * 因此,枚举类型State的set方法改造如下
	 */
	public void setFstate(int fstate)
	{
		this.fstate = State.fromInt(fstate);
	}
	public int getFsalaryAccountId()
	{
		return fsalaryAccountId;
	}
	public void setFsalaryAccountId(int fsalaryAccountId)
	{
		this.fsalaryAccountId = fsalaryAccountId;
	}
	public String getFcorpid()
	{
		return fcorpid;
	}
	public void setFcorpid(String fcorpid)
	{
		this.fcorpid = fcorpid;
	}
	public String getFuserid()
	{
		return fuserid;
	}
	public void setFuserid(String fuserid)
	{
		this.fuserid = fuserid;
	}
	public OpeningType getFopeningType()
	{
		return fopeningType;
	}
	public void setFopeningType(OpeningType fopeningType)
	{
		this.fopeningType = fopeningType;
	}
	public String getFsalaryCardNumber()
	{
		return fsalaryCardNumber;
	}
	public void setFsalaryCardNumber(String fsalaryCardNumber)
	{
		this.fsalaryCardNumber = fsalaryCardNumber;
	}
	public String getFsalaryCardBankType()
	{
		return fsalaryCardBankType;
	}
	public void setFsalaryCardBankType(String fsalaryCardBankType)
	{
		this.fsalaryCardBankType = fsalaryCardBankType;
	}
	public String getFsalaryCardBankSname()
	{
		return fsalaryCardBankSname;
	}
	public void setFsalaryCardBankSname(String fsalaryCardBankSname)
	{
		this.fsalaryCardBankSname = fsalaryCardBankSname;
	}
	public String getFsalaryCardOpenedTime()
	{
		return fsalaryCardOpenedTime;
	}
	public void setFsalaryCardOpenedTime(String fsalaryCardOpenedTime)
	{
		this.fsalaryCardOpenedTime = fsalaryCardOpenedTime;
	}
	public String getFgesturePassword()
	{
		return fgesturePassword;
	}
	public void setFgesturePassword(String fgesturePassword)
	{
		this.fgesturePassword = fgesturePassword;
	}
	public String getFmobileNumber()
	{
		return fmobileNumber;
	}
	public void setFmobileNumber(String fmobileNumber)
	{
		this.fmobileNumber = fmobileNumber;
	}
	public CreType getFcreType()
	{
		return fcreType;
	}
	public void setFcreType(CreType fcreType)
	{
		this.fcreType = fcreType;
	}
	public String getFcreId()
	{
		return fcreId;
	}
	public void setFcreId(String fcreId)
	{
		this.fcreId = fcreId;
	}
	public String getFcreName()
	{
		return fcreName;
	}
	public void setFcreName(String fcreName)
	{
		this.fcreName = fcreName;
	}
	public CreGender getFcreGender()
	{
		return fcreGender;
	}
	public void setFcreGender(CreGender fcreGender)
	{
		this.fcreGender = fcreGender;
	}
	public String getFcreNationality()
	{
		return fcreNationality;
	}
	public void setFcreNationality(String fcreNationality)
	{
		this.fcreNationality = fcreNationality;
	}
	public String getFcreBirthdate()
	{
		return fcreBirthdate;
	}
	public void setFcreBirthdate(String fcreBirthdate)
	{
		this.fcreBirthdate = fcreBirthdate;
	}
	public String getFcreAddress()
	{
		return fcreAddress;
	}
	public void setFcreAddress(String fcreAddress)
	{
		this.fcreAddress = fcreAddress;
	}
	public String getFcreIssueAuthority()
	{
		return fcreIssueAuthority;
	}
	public void setFcreIssueAuthority(String fcreIssueAuthority)
	{
		this.fcreIssueAuthority = fcreIssueAuthority;
	}
	public String getFcreValidDate()
	{
		return fcreValidDate;
	}
	public void setFcreValidDate(String fcreValidDate)
	{
		this.fcreValidDate = fcreValidDate;
	}
	public String getFcreExpireDate()
	{
		return fcreExpireDate;
	}
	public void setFcreExpireDate(String fcreExpireDate)
	{
		this.fcreExpireDate = fcreExpireDate;
	}
	public byte[] getFcreFrontPhoto()
	{
		return fcreFrontPhoto;
	}
	public void setFcreFrontPhoto(byte[] fcreFrontPhoto)
	{
		this.fcreFrontPhoto = fcreFrontPhoto;
	}
	public byte[] getFcreBackPhoto()
	{
		return fcreBackPhoto;
	}
	public void setFcreBackPhoto(byte[] fcreBackPhoto)
	{
		this.fcreBackPhoto = fcreBackPhoto;
	}
	public String getFlivenessDetectionNumber()
	{
		return flivenessDetectionNumber;
	}
	public void setFlivenessDetectionNumber(String flivenessDetectionNumber)
	{
		this.flivenessDetectionNumber = flivenessDetectionNumber;
	}
	public byte[] getFlivenessDetectionVideo()
	{
		return flivenessDetectionVideo;
	}
	public void setFlivenessDetectionVideo(byte[] flivenessDetectionVideo)
	{
		this.flivenessDetectionVideo = flivenessDetectionVideo;
	}
	public byte[] getFfaceRecognitionPhoto()
	{
		return ffaceRecognitionPhoto;
	}
	public void setFfaceRecognitionPhoto(byte[] ffaceRecognitionPhoto)
	{
		this.ffaceRecognitionPhoto = ffaceRecognitionPhoto;
	}
	public String getFbindCardNumber()
	{
		return fbindCardNumber;
	}
	public void setFbindCardNumber(String fbindCardNumber)
	{
		this.fbindCardNumber = fbindCardNumber;
	}
	public String getFbindCardBankSname()
	{
		return fbindCardBankSname;
	}
	public void setFbindCardBankSname(String fbindCardBankSname)
	{
		this.fbindCardBankSname = fbindCardBankSname;
	}
	public String getFbindCardBankNumber()
	{
		return fbindCardBankNumber;
	}
	public void setFbindCardBankNumber(String fbindCardBankNumber)
	{
		this.fbindCardBankNumber = fbindCardBankNumber;
	}
	public byte[] getFbindCardPhoto()
	{
		return fbindCardPhoto;
	}
	public void setFbindCardPhoto(byte[] fbindCardPhoto)
	{
		this.fbindCardPhoto = fbindCardPhoto;
	}
	public String getFcreateTime()
	{
		return fcreateTime;
	}
	public void setFcreateTime(String fcreateTime)
	{
		this.fcreateTime = fcreateTime;
	}
	public String getFmodifyTime()
	{
		return fmodifyTime;
	}
	public void setFmodifyTime(String fmodifyTime)
	{
		this.fmodifyTime = fmodifyTime;
	}
}
