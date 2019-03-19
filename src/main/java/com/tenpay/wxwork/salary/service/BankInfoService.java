package com.tenpay.wxwork.salary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenpay.bap.relay.context.RelaySessionInfo;
import com.tenpay.bap.relay.protocol.RelayMessage;
import com.tenpay.wxwork.salary.dto.RelayDemoRequestDTO;

@Service("testBankInfoService")
public class BankInfoService {
	@Autowired
	private RelayCommonService relayDemoService;

	public String getBankInfoListByBankType(RelaySessionInfo sessionInfo, int bankType, int commQueReqType) {
		RelayDemoRequestDTO request = new RelayDemoRequestDTO(sessionInfo.getMsgNo(), bankType, commQueReqType,
				"version", "spid");
		request.setNeedEncrypting(false);

		RelayMessage relayRsp = relayDemoService.sendRequest(sessionInfo, request);

		return relayRsp.getRawStr();
	}
}
