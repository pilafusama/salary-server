package com.tenpay.wxwork.salary.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties(prefix="keystore")
public class KeystoreConfig {

    private int keyVersion;

    private String salaryPrivateKeyId;

    private String salaryCertId;

    private String passwordSaltId;

    private String salarySecretId;

    private String accountSecretId;

    private String openidSecretId;

    public String getOpenidSecretId() {
        return openidSecretId;
    }

    public void setOpenidSecretId(String openidSecretId) {
        this.openidSecretId = openidSecretId;
    }

    public String getAccountSecretId() {
        return accountSecretId;
    }

    public void setAccountSecretId(String accountSecretId) {
        this.accountSecretId = accountSecretId;
    }

    public int getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(int keyVersion) {
        this.keyVersion = keyVersion;
    }

    public String getSalaryPrivateKeyId() {
        return salaryPrivateKeyId;
    }

    public void setSalaryPrivateKeyId(String salaryPrivateKeyId) {
        this.salaryPrivateKeyId = salaryPrivateKeyId;
    }

    public String getSalaryCertId() {
        return salaryCertId;
    }

    public void setSalaryCertId(String salaryCertId) {
        this.salaryCertId = salaryCertId;
    }

    public String getPasswordSaltId() {
        return passwordSaltId;
    }

    public void setPasswordSaltId(String passwordSaltId) {
        this.passwordSaltId = passwordSaltId;
    }

    public String getSalarySecretId() {
        return salarySecretId;
    }

    public void setSalarySecretId(String salarySecretId) {
        this.salarySecretId = salarySecretId;
    }
}
