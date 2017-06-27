/*
 * Copyright (c) 2010-2017 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2017湖南蚁坊软件股份有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha.filter;

import com.antfact.biz.commons.captcha.JsonUtil;
import com.antfact.biz.commons.captcha.exception.AuthException;

import com.fasterxml.jackson.core.type.TypeReference;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * 
 * <p>rest调用错误处理器。</p>
 *
 * 创建日期 2017年2月15日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $version$
 */
public class RestResponseErrorHandler implements ResponseErrorHandler {

    private Logger LOGGER = LoggerFactory.getLogger(RestResponseErrorHandler.class);

    private DefaultResponseErrorHandler defaultResponseErrorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().equals(HttpStatus.OK);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        MediaType mediaTypeJson = MediaType.APPLICATION_JSON;
        MediaType mediaTypeResponse = response.getHeaders().getContentType();

        if (mediaTypeResponse != null
            && mediaTypeJson.getType().equalsIgnoreCase(mediaTypeResponse.getType())
            && mediaTypeJson.getSubtype().equalsIgnoreCase(mediaTypeResponse.getSubtype())) {

            String jsonResult = IOUtils.toString(response.getBody(), CharEncoding.UTF_8);

            if (StringUtils.isNotBlank(jsonResult)) { // 服务器调用后，返回了错误信息。
                LOGGER.error("REST请求发生错误. " + jsonResult);
                AuthException ae = null;
                try {
                    ae = JsonUtil.parseJson(jsonResult, new TypeReference<AuthException>() {
                    });
                } catch (Exception e) {
                    LOGGER.error("REST请求错误信息转换时发生错误. ", e);
                }
                if (ae != null) {
                    throw ae;
                }
                defaultResponseErrorHandler.handleError(response);
            } else {
                defaultResponseErrorHandler.handleError(response);
            }
        } else {
            defaultResponseErrorHandler.handleError(response);
        }
    }
}
