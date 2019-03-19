package com.tenpay.wxwork.salary.config;

/**
 * 统一定义企业微信 OAuth 的 state 值，据此登录之后重定向到不同的地方。
 *
 * https://work.weixin.qq.com/api/doc#10975/%E7%BD%91%E9%A1%B5%E6%8E%88%E6%9D%83%E7%99%BB%E5%BD%95%E7%AC%AC%E4%B8%89%E6%96%B9
 * 其中提到 STATE 可取值要求：
 * 重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值，长度不可超过128个字节
 *
 */
public class WxworkOAuthState {
    public static String OPEN_ACCOUNT = "OpenAccount"; // 开户

    public static String CHECK_SALARY_DETAIL = "CheckSalaryDetail"; // 查看薪资明细
}