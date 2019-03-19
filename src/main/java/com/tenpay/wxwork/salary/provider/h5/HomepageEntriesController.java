package com.tenpay.wxwork.salary.provider.h5;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.HomepageEntriesResponse;
import com.tenpay.wxwork.salary.model.GridInfo;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.h5.HomepageEntriesService;

/**
 * 主页入口
 * @author v_dongzhao
 *
 */
@RestController
@RequestMapping("/h5")
public class HomepageEntriesController {
	
	@Autowired
    private SessionService sessionService;
	
	@Autowired
	private HomepageEntriesService homepageEntriesService;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HomepageEntriesController.class);	

	@PostMapping(value = "/get_homepage_entries", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HomepageEntriesResponse getHomepageEntries(@CookieValue("ssid") String ssid) {
		
		// 验证登陆态
		SessionInfo sessionInfo = sessionService.getSession(ssid);
		if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
		
		// 获取宫格配置
		String bankSname = sessionInfo.getBankSname();
		List<GridInfo> gridList = homepageEntriesService.getGridsInfo(bankSname);
		String grid_topic = homepageEntriesService.getGridTopic(bankSname);
		return new HomepageEntriesResponse(grid_topic, gridList);
		
	}
	
}
