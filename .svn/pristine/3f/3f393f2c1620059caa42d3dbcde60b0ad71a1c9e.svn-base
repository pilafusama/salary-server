package com.tenpay.wxwork.salary.config;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;

/**
 * 银行联行号
 *
 */
@Component
public class NationalBankNumber {
    private Map<String, String> map;
    private Map<String, String> mapName;

    public NationalBankNumber() {
        map = new HashMap<String, String>();

        map.put("ICBC", "102100099996"); // 中国工商银行
        map.put("ABC", "103100000026"); // 中国农业银行
        map.put("BOC", "104100000004"); // 中国银行
        map.put("COMM", "301290000007"); // 交通银行
        map.put("CCB", "105100000017"); // 建设银行
        map.put("CMB", "308584000013"); // 招商银行
        map.put("BSB", "313192000013"); // 包商银行
    }

    public NationalBankNumber(String str) {
        mapName = new HashMap<String, String>();

        mapName.put("ICBC","工商银行");
        mapName.put("ABC","农业银行");
        mapName.put("PSBC","邮政储蓄银行");
        mapName.put("CCB","建设银行");
        mapName.put("CMB","招商银行");
        mapName.put("BOC","中国银行");
        mapName.put("COMM","交通银行");
        mapName.put("SPDB","浦发银行");
        mapName.put("GDB","广发银行");
        mapName.put("CMBC","民生银行");
        mapName.put("PAB","平安银行");
        mapName.put("CEB","光大银行");
        mapName.put("CIB","兴业银行");
        mapName.put("CITIC","中信银行");
        mapName.put("BOSH","上海银行");
        mapName.put("CRB","华润银行");
        mapName.put("HZB","杭州银行");
        mapName.put("BSB","包商银行");
        mapName.put("CQB","重庆银行");
        mapName.put("SDEB","顺德农商行");
        mapName.put("SZRCB","深圳农商银行");
        mapName.put("HRBB","哈尔滨银行");
        mapName.put("BOCD","成都银行");
        mapName.put("GDNYB","南粤银行");
        mapName.put("GZCB","广州银行");
        mapName.put("JSB","江苏银行");
        mapName.put("NBCB","宁波银行");
        mapName.put("NJCB","南京银行");
        mapName.put("QHNX","青海农信");
        mapName.put("ORDOSB","鄂尔多斯银行");
        mapName.put("BJRCB","北京农商");
        mapName.put("BHB","河北银行");
        mapName.put("BGZB","贵州银行");
        mapName.put("BEEB","鄞州银行");
        mapName.put("PZHCCB","攀枝花银行");
        mapName.put("QDCCB","青岛银行");
        mapName.put("SHINHAN","新韩银行");
        mapName.put("QLB","齐鲁银行");
        mapName.put("QSB","齐商银行");
        mapName.put("ZZB","郑州银行");
        mapName.put("CCAB","长安银行");
        mapName.put("RZB","日照银行");
        mapName.put("SCNX","四川农信");
        mapName.put("BEEB","鄞州银行");
        mapName.put("SDRCU","山东农信");
        mapName.put("BCZ","沧州银行");
        mapName.put("SJB","盛京银行");
        mapName.put("LNNX","辽宁农信");
        mapName.put("JUFENGB","临朐聚丰村镇银行");
        mapName.put("ZZB","郑州银行");
        mapName.put("JXNXB","江西农信");
        mapName.put("JZB","晋中银行");
        mapName.put("JZCB","锦州银行");
        mapName.put("JZCB","锦州银行");
        mapName.put("KLB","昆仑银行");
        mapName.put("KRCB","昆山农商");
        mapName.put("KUERLECB","	库尔勒市商业银行");
        mapName.put("LJB","龙江银行");
        mapName.put("NYCCB","南阳村镇银行");
        mapName.put("LSCCB","乐山市商业银行");
        mapName.put("LUZB","柳州银行");
        mapName.put("LWB","莱商银行");
        mapName.put("LYYHB","辽阳银行");
        mapName.put("LZB","兰州银行");
        mapName.put("MINTAIB","民泰银行");
        mapName.put("MINTAIB","民泰银行");
        mapName.put("NCB","宁波通商银行");
        mapName.put("NMGNX","内蒙古农信");
        mapName.put("XAB","西安银行");
        mapName.put("WFB","潍坊银行");
        mapName.put("WHB","威海商业银行");
        mapName.put("WHRC","武汉农商");
        mapName.put("WJRCB","吴江农商行");
        mapName.put("WLMQB","乌鲁木齐银行");
        mapName.put("WRCB","无锡农商");
        mapName.put("WZB","温州银行");
        mapName.put("XAB","西安银行");
        mapName.put("WEB","微众银行");
        mapName.put("XIB","厦门国际银行");
        mapName.put("XJRCCB","新疆农信银行");
        mapName.put("XMCCB","厦门银行");
        mapName.put("YNRCCB","云南农信");
        mapName.put("YRRCB","黄河农商银行");
        mapName.put("YRRCB","黄河农商银行");
        mapName.put("YTB","烟台银行");
        mapName.put("ZJB","紫金农商银行");
        mapName.put("ZJLXRB","兰溪越商银行");
        mapName.put("ZJRCUB","浙江农信");
        mapName.put("AHRCUB","安徽省农村信用社联合社");
        mapName.put("BCZ","沧州银行");
        mapName.put("SRB","上饶银行");
        mapName.put("ZYB","中原银行");
        mapName.put("ZRCB","张家港农商行");
        mapName.put("SRCB","上海农商银行");
        mapName.put("ZJTLCB","浙江泰隆银行");
        mapName.put("SUZB","苏州银行");
        mapName.put("SXNX","山西农信");
        mapName.put("SXXH","陕西信合");
        mapName.put("ZJRCUB","浙江农信");
        mapName.put("AE","AE");
        mapName.put("TACCB","泰安银行");
        mapName.put("TCRCB","太仓农商行");
        mapName.put("TJBHB","天津滨海农商行");
        mapName.put("TJB","天津银行");
        mapName.put("TRCB","天津农商");
        mapName.put("TZB","台州银行");
        mapName.put("URB","联合村镇银行");
        mapName.put("DYB","东营银行");
        mapName.put("CSRCB","常熟农商银行");
        mapName.put("CZB","浙商银行");
        mapName.put("CZCB","稠州银行");
        mapName.put("DANDONGB","丹东银行");
        mapName.put("DLB","大连银行");
        mapName.put("DRCB","东莞农商银行");
        mapName.put("CSRCB","常熟农商银行");
        mapName.put("DYB","东营银行");
        mapName.put("DYCCB","德阳银行");
        mapName.put("FBB","富邦华一银行");
        mapName.put("FDB","富滇银行");
        mapName.put("FJHXB","福建海峡银行");
        mapName.put("FJNX","福建农信银行");
        mapName.put("FUXINB","阜新银行");
        mapName.put("BOCDB","承德银行");
        mapName.put("JSNX","江苏农商行");
        mapName.put("BOLFB","廊坊银行");
        mapName.put("CCAB","长安银行");
        mapName.put("CBHB","渤海银行");
        mapName.put("CDRCB","成都农商银行");
        mapName.put("BYK","营口银行");
        mapName.put("BOZ","张家口市商业银行");
        mapName.put("CFT","零钱");
        mapName.put("BOTSB","唐山银行");
        mapName.put("BOSZS","石嘴山银行");
        mapName.put("BOSXB","绍兴银行");
        mapName.put("BONX","宁夏银行");
        mapName.put("GDHX","广东华兴银行");
        mapName.put("BOLB","洛阳银行");
        mapName.put("BOJX","嘉兴银行");
        mapName.put("BOIMCB","内蒙古银行");
        mapName.put("BOHN","海南银行");
        mapName.put("BOD","东莞银行");
        mapName.put("CQRCB","重庆农商银行");
        mapName.put("CQTGB","重庆三峡银行");
        mapName.put("BOD","东莞银行");
        mapName.put("CSCB","长沙银行");
        mapName.put("BOB","北京银行");
        mapName.put("GDRCU","广东农信银行");
        mapName.put("BOB","北京银行");
        mapName.put("HRXJB","华融湘江银行");
        mapName.put("HSBC","恒生银行");
        mapName.put("HSB","徽商银行");
        mapName.put("HUNNX","湖南农信");
        mapName.put("HUSRB","湖商村镇银行");
        mapName.put("HXB","华夏银行");
        mapName.put("HNNX","河南农信");
        mapName.put("BNC","江西银行");
        mapName.put("BJRCB","北京农商行");
        mapName.put("JCB","晋城银行");
        mapName.put("JJCCB","九江银行");
        mapName.put("JLB","吉林银行");
        mapName.put("JLNX","吉林农信");
        mapName.put("JNRCB","江南农商");
        mapName.put("JRCB","江阴农商行");
        mapName.put("JSHB","晋商银行");
        mapName.put("HAINNX","海南农信");
        mapName.put("GLB","桂林银行");
        mapName.put("GRCB","广州农商银行");
        mapName.put("GSB","甘肃银行");
        mapName.put("GSNX","甘肃农信");
        mapName.put("GXNX","广西农信");
        mapName.put("GYCB","贵阳银行");
        mapName.put("GZNX","贵州农信");
        mapName.put("HAINNX","海南农信");
        mapName.put("HKB","汉口银行");
        mapName.put("HANAB","韩亚银行");
        mapName.put("HBCB","湖北银行");
        mapName.put("HBNX","湖北农信");
        mapName.put("HDCB","邯郸银行");
        mapName.put("HEBNX","河北农信");
        mapName.put("HFB","恒丰银行");
        mapName.put("HKBEA","东亚银行");
        mapName.put("JCB","JCB ");
        mapName.put("MASTERCARD","MASTERCARD");
        mapName.put("VISA","VISA");
        mapName.put("LQT","零钱通");
    }

    public String query(String bankSname) {
        String bankNumber = map.get(bankSname.toUpperCase());
        if (null == bankNumber) {
            // 暂时只支持五大行
            throw new BizException(BizError.BANK_NUMBER_NOT_FOUND.errorCode(),
                                   BizError.BANK_NUMBER_NOT_FOUND.errorMsg() + bankSname);
        }
        return bankNumber;
    }

    public String queryChName(String bankSname) {
        String bankCh = mapName.get(bankSname.toUpperCase());
        if (null == bankCh) {
            throw new BizException(BizError.BANK_NAME_NOT_FOUND.errorCode(),
                    BizError.BANK_NAME_NOT_FOUND.errorMsg() + bankSname);
        }
        return bankCh;
    }
}