/*
 * Copyright (c) 2010-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>功能描述,该部分必须以中文句号结尾。</p>
 *
 * 创建日期 2017年3月13日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $version$
 */
public class AuthException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -3294333129608061511L;
    @JsonProperty(value = "error_description")
    private String errorDescription;
    @JsonProperty(value = "error_code")
    private String errorCode;
    private String error;
    @JsonProperty("detail_error_description")
    private String detailErrorDexcription;
    @JsonProperty("http_status_code")
    private int httpStatusCode;
    private Object data;
    private String message;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetailErrorDexcription() {
        return detailErrorDexcription;
    }

    public void setDetailErrorDexcription(String detailErrorDexcription) {
        this.detailErrorDexcription = detailErrorDexcription;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
