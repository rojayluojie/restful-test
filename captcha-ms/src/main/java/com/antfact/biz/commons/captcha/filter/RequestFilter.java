/*
 * Copyright (c) 2010-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha.filter;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>功能描述,该部分必须以中文句号结尾。</p>
 *
 * 创建日期 2017年3月8日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $version$
 */
//@Component
//@WebFilter(urlPatterns = "/*", filterName = "captchaFilter")
public class RequestFilter implements Filter {

    private static final String ERROR_TEMPLATE = "{\"errorCode\":\"%s\",\"description\":\"%s\"}";
    private Logger logger = org.slf4j.LoggerFactory.getLogger(RequestFilter.class);

    @Autowired
    private Auth2Client authClient;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Filter started.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                              ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        try {
            TokenValidate validateResult = authClient.verifyToken(httpRequest);
            String errorCode = validateResult.getErrorCode();
            if (StringUtils.hasText(errorCode)) {
                String json = String.format(ERROR_TEMPLATE, errorCode, validateResult.getErrorDescription());
                response.getOutputStream().write(json.getBytes());
                response.getOutputStream().flush();
                return;
            }
            chain.doFilter(httpRequest, response);
        } catch (Exception e) {
            logger.error("验证token有效性时发生异常", e);
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {

    }

}
