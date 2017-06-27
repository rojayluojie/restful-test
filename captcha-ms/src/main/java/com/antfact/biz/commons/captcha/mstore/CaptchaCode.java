/*
 * Copyright (c) 2010-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha.mstore;

import java.io.Serializable;

/**
 * <p>验证码信息。</p>
 *
 * 创建日期 2017年3月8日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $version$
 */
public class CaptchaCode implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8089404520256917499L;

    private String captchaId; // 验证码id
    private String clientId; // 验证码生成者应用id
    private String userId; // 验证码生成者Id
    private String text; // 验证码内容
    private long expirationTime; // 验证码过期时间

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

}
