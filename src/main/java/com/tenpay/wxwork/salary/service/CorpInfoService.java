package com.tenpay.wxwork.salary.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenpay.wxwork.salary.dao.CorpInfoDAO;
import com.tenpay.wxwork.salary.dto.CorpInfoDTO;
import com.tenpay.wxwork.salary.model.CorpInfo;

@Service
public class CorpInfoService {
	
	@Autowired
	private CorpInfoDAO corpInfoDAO;

	public CorpInfoDTO queryCorpByCorpid(String corpid) {
		CorpInfo corpInfo = corpInfoDAO.queryCorpByCorpid(corpid);
		if(null == corpInfo)
		{
			return null;
		}
		else
		{
			CorpInfoDTO dto = new CorpInfoDTO();
			dto.setCorpid(corpInfo.getCorpid());
			dto.setCorpName(corpInfo.getCorpName());
			dto.setCertId(corpInfo.getCertId());
			dto.setCorpType(corpInfo.getCorpType());
			dto.setCorpType(corpInfo.getCorpType());
			dto.setCorpSquareLogoUrl(corpInfo.getCorpSquareLogoUrl());
			dto.setCorpUserMax(corpInfo.getCorpUserMax());
			dto.setCorpFullName(corpInfo.getCorpFullName());
			dto.setVerifiedEndTime(corpInfo.getVerifiedEndTime());
			dto.setCorpWxqrcode(corpInfo.getCorpWxqrcode());
			dto.setSrcMchId(corpInfo.getSrcMchId());
			dto.setSrcAppId(corpInfo.getSrcAppId());
			dto.setCorpStatus(corpInfo.getCorpStatus());
			dto.setCreateTime(corpInfo.getCreateTime());
			dto.setModifyTime(corpInfo.getModifyTime());

			return dto;	
		}
	}
	
	public boolean isNeedUpdate(CorpInfoDTO corpInfoDTO,String corpName,String certId)
	{
		if(!StringUtils.isBlank(corpName) && !StringUtils.isBlank(corpInfoDTO.getCorpName()) && !corpInfoDTO.getCorpName().equals(corpName))
		{
			return true;
		}
		if(!StringUtils.isBlank(certId) && !StringUtils.isBlank(corpInfoDTO.getCertId()) && !corpInfoDTO.getCertId().equals(certId))
		{
			return true;
		}
		return false;
	}
	@Transactional
	public void updateCorpInfo(String corpid,String corpName,String certId)
	{
		corpInfoDAO.updateCorpInfo(corpid, corpName, certId);
	}
}
