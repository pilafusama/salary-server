package com.tenpay.wxwork.salary.service;

import com.tenpay.wxwork.salary.model.CorpInfo;
import com.tenpay.wxwork.salary.model.CorpAuthen;
import com.tenpay.wxwork.salary.model.UserInfo;
import com.tenpay.wxwork.salary.model.CorpDepartment;
import com.tenpay.wxwork.salary.dao.CorpInfoDAO;
import com.tenpay.wxwork.salary.dao.CorpAuthenDAO;
import com.tenpay.wxwork.salary.dao.CorpDepartmentDAO;
import com.tenpay.wxwork.salary.dao.UserInfoDAO;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.service.WxworkMessageService;

import com.tenpay.wxwork.wxworklib.client.WxworkHttpClient;
import com.tenpay.wxwork.wxworklib.exception.WxworkException;
import com.tenpay.wxwork.wxworklib.service.AuthService;
import com.tenpay.wxwork.wxworklib.service.WxAccessTokenService;
import com.tenpay.wxwork.wxworklib.service.AbstractContactBookService;
import com.tenpay.wxwork.wxworklib.utils.UrlGenerator;
import com.tenpay.wxwork.wxworklib.utils.XmlUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;

import org.w3c.dom.Document;

/**
 * 企业微信通讯录服务
 */
@Service
public class WxworkContactBookService extends AbstractContactBookService {

    private final static Logger LOGGER = LoggerFactory.getLogger(WxworkContactBookService.class);

    @Resource
    private WxAccessTokenService wxAccessTokenService;

    @Resource
    private WxworkHttpClient wxworkHttpClient;

    @Resource
    private WxworkMessageService wxworkMessageService;

    @Autowired
    private CorpInfoDAO corpInfoDAO;

    @Autowired
    private CorpAuthenDAO corpAuthenDAO;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    @Autowired
    private CorpDepartmentDAO corpDepartmentDAO;

    @Resource
    private Gson gson;

    /**
     * 同步企业的所有部门及通讯录成员
     *
     */
    public void syncContactBook(String suiteId, String corpid) {
        CorpAuthen corpAuthen = corpAuthenDAO.queryByCorpidAndSuiteId(corpid, suiteId);
        if (null == corpAuthen) {
            LOGGER.error("no corp auth record for corpid: {} and suite id: {}", corpid, suiteId);
            throw new BizException(BizError.NO_CORP_AUTHEN);
        }

        String accessToken = wxAccessTokenService.getCorpAccessToken(suiteId, corpid, corpAuthen.getPermanentCode());

        // 获取部门
        syncAllDepartments(corpid, accessToken);

        // 获取成员
        String[] departmentIds = gson.fromJson(corpAuthen.getAllowParty(), String[].class);
        for (String departmentId : departmentIds) {
            JsonObject jsonObject = wxworkHttpClient.getDepartmentUsers(accessToken, departmentId);
            JsonArray userList = jsonObject.get("userlist").getAsJsonArray();
            LOGGER.debug("get {} users from wxwork for corp id: {}, save them to db now.", userList.size(), corpid);

            for (JsonElement userElement : userList) {
                JsonObject user = userElement.getAsJsonObject();
                String userid = user.get("userid").getAsString();

                UserInfo userInfo = userInfoDAO.queryByCorpidUserid(corpid, userid);
                if (null == userInfo) {
                    userInfoDAO.insertBasicInfo(corpid, userid,
                                                user.get("name").getAsString(),
                                                user.get("status").getAsString(),
                                                user.get("department").getAsJsonArray().toString(),
                                                user.get("gender").getAsString(),
                                                user.get("avatar").getAsString(),
                                                suiteId);
                } else {
                    userInfoDAO.updateBasicInfo(corpid, userid,
                                                user.get("name").getAsString(),
                                                user.get("status").getAsString(),
                                                user.get("department").getAsJsonArray().toString(),
                                                user.get("gender").getAsString(),
                                                user.get("avatar").getAsString(),
                                                suiteId);
                }
            }
        }
    }

    @Override
    public void createUser(Document document) {
        String corpid = XmlUtils.findNodeContent(document, "/xml/AuthCorpId");
        String userid = XmlUtils.findNodeContent(document, "/xml/UserID");
        LOGGER.info("create user with corpid: {}, userid: {}", corpid, userid);

        String suiteId = ConfigUtils.getSuiteId();
        String gender = XmlUtils.findNodeContent(document, "/xml/Gender");
        // 目前来看，推送过来的信息中可能不包含 Gender 字段
        if (gender.isEmpty()) {
            gender = "0"; // 性别0：未定义 1：男性 2：女性
        }
        UserInfo userInfo = userInfoDAO.queryByCorpidUserid(corpid, userid);
        if (null == userInfo) {
            userInfoDAO.insertBasicInfo(corpid, userid,
                                        XmlUtils.findNodeContent(document, "/xml/Name"),
                                        XmlUtils.findNodeContent(document, "/xml/Status"),
                                        convert2JsonArray(XmlUtils.findNodeContent(document, "/xml/Department")),
                                        gender,
                                        XmlUtils.findNodeContent(document, "/xml/Avatar"),
                                        suiteId);
        } else {
            userInfoDAO.updateBasicInfo(corpid, userid,
                                        XmlUtils.findNodeContent(document, "/xml/Name"),
                                        XmlUtils.findNodeContent(document, "/xml/Status"),
                                        convert2JsonArray(XmlUtils.findNodeContent(document, "/xml/Department")),
                                        gender,
                                        XmlUtils.findNodeContent(document, "/xml/Avatar"),
                                        suiteId);
        }

        LOGGER.info("send open account msg to new user: {}", userid);
        // 发送开户信息
        wxworkMessageService.sendOpenAccountMsg(ConfigUtils.getSuiteId(), corpid, userid);
    }

    @Override
    public void updateUser(Document document) {
        String corpid = XmlUtils.findNodeContent(document, "/xml/AuthCorpId");
        String userid = XmlUtils.findNodeContent(document, "/xml/UserID");
        String newUserid = XmlUtils.findNodeContent(document, "/xml/NewUserID");
        LOGGER.info("update user with corpid: {}, userid: {}, new userid: {}", corpid, userid, newUserid);

        String suiteId = ConfigUtils.getSuiteId();
        UserInfo userInfo = userInfoDAO.queryByCorpidUserid(corpid, userid);
        if (null == userInfo) {
            throw new BizException(BizError.NO_CORP_USER);
        } else {
            userInfoDAO.updateBasicInfoSelectively(corpid, userid, newUserid,
                                                   XmlUtils.findNodeContent(document, "/xml/Name"),
                                                   XmlUtils.findNodeContent(document, "/xml/Status"),
                                                   convert2JsonArray(XmlUtils.findNodeContent(document, "/xml/Department")),
                                                   XmlUtils.findNodeContent(document, "/xml/Gender"),
                                                   XmlUtils.findNodeContent(document, "/xml/Avatar"),
                                                   suiteId);
        }
    }

    @Override
    public void deleteUser(String corpid, String userid) {
        LOGGER.info("mark salary account deleted, corpid: {} and userid: {}", corpid, userid);
        salaryAccountDAO.deleteUser(corpid, userid);
    }

    private String convert2JsonArray(String origin) {
        return "[" + origin + "]";
    }

    private void syncAllDepartments(String corpid, String accessToken) {
        JsonObject departmentsObject = wxworkHttpClient.getAllDepartments(accessToken);
        JsonArray departmentArray = departmentsObject.get("department").getAsJsonArray();
        LOGGER.debug("get {} departments from wxwork for corp id: {}, save them to db now.",
                     departmentArray.size(), corpid);
        for (JsonElement departmentElement : departmentArray) {
            JsonObject departmentObject = departmentElement.getAsJsonObject();
            int departmentId = departmentObject.get("id").getAsInt();
            CorpDepartment corpDepartment = corpDepartmentDAO.queryByCorpidDepartmentId(corpid, departmentId);

            if (null == corpDepartment) {
                corpDepartmentDAO.insert(corpid, departmentId,
                                     departmentObject.get("name").getAsString(),
                                     departmentObject.get("parentid").getAsInt(),
                                     departmentObject.get("order").getAsInt());
            } else {
                corpDepartmentDAO.update(corpid, departmentId,
                                     departmentObject.get("name").getAsString(),
                                     departmentObject.get("parentid").getAsInt(),
                                     departmentObject.get("order").getAsInt());
            }
        }
    }
}
