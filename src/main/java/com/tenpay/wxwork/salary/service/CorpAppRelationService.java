package com.tenpay.wxwork.salary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenpay.wxwork.salary.dao.CorpAppRelationDAO;
import com.tenpay.wxwork.salary.dto.AdminInfo;
import com.tenpay.wxwork.salary.dto.CorpAppRelationDTO;
import com.tenpay.wxwork.salary.info.CorpAppRelationAuthenStatus;
import com.tenpay.wxwork.salary.info.CorpAppRelationStatus;
import com.tenpay.wxwork.salary.model.CorpAppRelation;
import com.tenpay.wxwork.salary.util.JsonUtils;
import com.tenpay.wxwork.salary.util.TypeTokenWrapper;

@Service
public class CorpAppRelationService {
	
	@Autowired
	private CorpAppRelationDAO corpAppRelationDAO;
	
	private static Map<String, CorpAppRelationDTO> corpAppRelationMap = new HashMap<String, CorpAppRelationDTO>();
	
	public CorpAppRelationDTO queryCorpByCorpid(String corpid,String appId)
	{	
		CorpAppRelation corpAppRelation = corpAppRelationDAO.queryCorpAppRelation(corpid, appId);
		if(null == corpAppRelation)
		{
			return null;
		}
		else
		{
			CorpAppRelationDTO corpAppRelationDTO = new CorpAppRelationDTO();
			corpAppRelationDTO.setCorpid(corpAppRelation.getCorpid());
			corpAppRelationDTO.setAppId(corpAppRelation.getAppId());
			corpAppRelationDTO.setBankType(corpAppRelation.getBankType());
			corpAppRelationDTO.setOpUserid(corpAppRelation.getOpUserid());
			corpAppRelationDTO.setBankUin(corpAppRelation.getBankUin());
			corpAppRelationDTO.setOpPhone(corpAppRelation.getOpPhone());
			corpAppRelationDTO.setStatus(corpAppRelation.getStatus());
			corpAppRelationDTO.setAuthenStatus(corpAppRelation.getAuthenStatus());
			corpAppRelationDTO.setAuthenId(corpAppRelation.getAuthenId());
			corpAppRelationDTO.setAuthenTime(corpAppRelation.getAuthenTime());
			corpAppRelationDTO.setCreateTime(corpAppRelation.getCreateTime());
			corpAppRelationDTO.setModifyTime(corpAppRelation.getModifyTime());
			corpAppRelationMap.put(corpid, corpAppRelationDTO);
			
			return corpAppRelationDTO;
		}
	}

	public boolean isNeedUpdate(CorpAppRelationDTO corpAppRelationDTO, String bankType, String bankUin, String opUserPhone,String opList)
	{
		if(!StringUtils.isBlank(bankType) && !StringUtils.isBlank(corpAppRelationDTO.getBankType()) && !corpAppRelationDTO.getBankType().equals(bankType))
		{
			return true;
		}
		if(!StringUtils.isBlank(bankUin) && !StringUtils.isBlank(corpAppRelationDTO.getBankUin()) && !corpAppRelationDTO.getBankUin().equals(bankUin))
		{
			return true;
		}
		if(!StringUtils.isBlank(opUserPhone) && !StringUtils.isBlank(corpAppRelationDTO.getOpPhone()) && !corpAppRelationDTO.getOpPhone().equals(opUserPhone))
		{
			return true;
		}
		if(!StringUtils.isBlank(opList) && !StringUtils.isBlank(JsonUtils.toJson(corpAppRelationDTO.getOpUserid())) && !corpAppRelationDTO.getOpUserid().equals(opList))
		{
			return true;
		}
		return false;
	}
	@Transactional
	public void insertCorpAppRelation(String corpid,String appId,String bankType, String bankUin, String opUserId, String opPhone,String status)
	{
		corpAppRelationDAO.insertCorpAppRelation(corpid, appId, bankType, bankUin, opUserId, opPhone, status);
	}
	
	@Transactional
	public void updateCorpAppRelationFromClose(String corpid, String appId, String bankType, String bankUin, String opUserId, String opUserPhone)
	{
		corpAppRelationDAO.updateCorpAppRelationFromClose(corpid, appId, bankType, bankUin, opUserId, opUserPhone);
	}
	@Transactional
	public void updateCorpAppRelationFromOpen(String corpid, String appId, String bankType, String bankUin, String opUserId, String opUserPhone)
	{
		corpAppRelationDAO.updateCorpAppRelationFromOpen(corpid, appId, bankType, bankUin, opUserId, opUserPhone);
	}
	
	public void updateAuthenStatusCloseFromNormal(@Param("corpid")String corpid,@Param("appId")String appId)
	{
		corpAppRelationDAO.updateAuthenStatusCloseFromNormal(corpid,appId);
	}
	
	public void updateAuthenStatusNormalFromInitial(@Param("corpid")String corpid,@Param("appId")String appId,@Param("corpAuthId")String corpAuthId)
	{
		corpAppRelationDAO.updateAuthenStatusNormalFromInitial(corpid,appId,corpAuthId);
	}
	
	public void updateAuthenStatusNormalFromClose(@Param("corpid")String corpid,@Param("appId")String appId,@Param("corpAuthId")String corpAuthId)
	{
		corpAppRelationDAO.updateAuthenStatusNormalFromClose(corpid,appId,corpAuthId);
	}
	
	@Transactional
	public void updateStatusOpenFromClose(@Param("corpid")String corpid, @Param("appId")String appId)
	{
		corpAppRelationDAO.updateStatusOpenFromClose(corpid,appId);
	}
	@Transactional
	public void updateStatusOpenFromStop(@Param("corpid")String corpid, @Param("appId")String appId)
	{
		corpAppRelationDAO.updateStatusOpenFromStop(corpid,appId);
	}
	@Transactional
	public void updateStatusStopFromOpen(@Param("corpid")String corpid, @Param("appId")String appId)
	{
		corpAppRelationDAO.updateStatusStopFromOpen(corpid,appId);
	}
	@Transactional
	public void updateStatusCloseFromOpen(@Param("corpid")String corpid, @Param("appId")String appId)
	{
		corpAppRelationDAO.updateStatusCloseFromOpen(corpid,appId);
	}
	@Transactional
	public void updateStatusCloseFromStop(@Param("corpid")String corpid, @Param("appId")String appId)
	{
		corpAppRelationDAO.updateStatusCloseFromStop(corpid,appId);
	}

	public boolean checkCorpRelationStatus(String corpid, String appid)
	{
		CorpAppRelationDTO corpAppRelationDTO = queryCorpByCorpid(corpid, appid);
		if(corpAppRelationDTO != null && ! corpAppRelationDTO.getStatus().equals(CorpAppRelationStatus.Open))
		{
			return false;
		}
		if(corpAppRelationDTO != null && corpAppRelationDTO.getAuthenStatus() != CorpAppRelationAuthenStatus.Normal)
		{
			return false;
		}
		
		return true;
	}
	
	public CorpAppRelationDTO getCacheCorpRelationDTO(String corpid)
	{
		return corpAppRelationMap.get(corpid);
	}
}
