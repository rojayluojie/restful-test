/*
 * Copyright (c) 2010-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * <p>token有效性检测正常结果封装。</p>
 *
 * 创建日期 2016年7月6日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $version$
 */
public class TokenValidate implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3194296931454907812L;

    @JsonProperty(value = "error_description")
    private String errorDescription;

    @JsonProperty(value = "error_code")
    private String errorCode;

    @JsonProperty(value = "client_id")
    private String clientId; // token关联应用id

    @JsonProperty(value = "user_id")
    private String userId; // token关联用户id

    @JsonProperty(value = "user_name")
    private String userName; // token关联用户名称

    @JsonProperty(value = "nick_name")
    private String nickName; // token关联用户昵称

    private String platforms; // token关联有权限的平台列表,多个平台之间用英文逗号分隔

    private String realm; // token关联权限域

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
