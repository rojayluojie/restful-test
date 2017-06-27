/*
 * Copyright (c) 2016-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2016-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha.filter;

import com.antfact.biz.commons.captcha.exception.AuthException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>功能描述,该部分必须以中文句号结尾。</p>
 *
 * 创建日期 2017年1月5日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $1.0.0$
 */
@Component
public class Auth2Client {

    private static final ParameterizedTypeReference<TokenValidate> TOKEN_VALIDATE_TYPE = new ParameterizedTypeReference<TokenValidate>() {
    };
    private static final String AUTHZ_VALIDATE_PERMISSION = "{\"REST\":{\"apis\":{\"%1$s\":\"%2$s\"}}}";
    private static final String WL_PROXY_CLIENT_IP_HEADER_NAME = "WL-Proxy-Client-IP";
    private static final String X_FORWARDED_FOR_HEADER_NAME = "x-forwarded-for";
    private static final String PROXY_CLIENT_IP_HEADER_NAME = "Proxy-Client-IP";
    private static final String UNKNOWN_HEADER_VALUE = "unknown";

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    private Logger logger = LoggerFactory.getLogger(Auth2Client.class);

    @Value(value = "${token.validate.service.url}")
    private String tokenValidateUrl;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate.setErrorHandler(new RestResponseErrorHandler());
    }

    public TokenValidate verifyToken(HttpServletRequest request) throws Exception {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);
        TokenValidate info;
        if (null == authHeader || authHeader.trim().length() == 0) {
            info = new TokenValidate();
            info.setErrorCode("00116");
            info.setErrorDescription("Miss Authorization's header");
            return info;
        }
        if (!authHeader.toLowerCase().contains("oauth2 ")) {
            info = new TokenValidate();
            info.setErrorCode("00117");
            info.setErrorDescription("Illegal format authorization's header: " + authHeader);
            return info;
        }

        String token = authHeader.split(" ")[1];
        String uri = request.getRequestURI();
        String methodType = request.getMethod();
        String clientIp = getClientIP(request);
        String userIp = request.getHeader(FilterConstants.USER_IP_REQ_PARAM_NAME);
        if (null == userIp || userIp.trim().length() == 0) {
            userIp = clientIp;
        }
        String userAgent = request.getHeader(FilterConstants.USER_AGENT_REP_PARAM_NAME);

        info = verifyToken(token, uri, methodType, clientIp, userIp, userAgent);
        request.setAttribute("USER_ID", info.getUserId());
        request.setAttribute("CLIENT_ID", info.getClientId());

        return info;
    }

    /**
     * 通过认证中心验证token有效性
     * @param token {@link String} token值
     * @param uri {@link String} 请求目标服务uri
     * @param methodType {@link String} 请求方式(GET,POST,PUT,DELETE)
     * @param clientIp {@link String} 客户端ip
     * @param userIp {@link String} 用户浏览器端ip
     * @return {@link TokenValidate} 认证信息
     */
    public TokenValidate verifyToken(String token, String uri, String methodType, String clientIp, String userIp,
                                     String userAgent) {

        try {
            String permission = String.format(AUTHZ_VALIDATE_PERMISSION, uri, methodType.toUpperCase());

            MultiValueMap<String, Object> multiMap = new LinkedMultiValueMap<>(5);
            multiMap.add(FilterConstants.REMOTE_ADDR_REQ_PARAM_NAME, clientIp);
            multiMap.add(FilterConstants.USER_AGENT_REP_PARAM_NAME, userAgent);
            multiMap.add(FilterConstants.PERMISSION_PARAM_NAME, permission);
            multiMap.add(FilterConstants.USER_IP_REQ_PARAM_NAME, userIp);
            multiMap.add(FilterConstants.REST_ID_REQ_PARAM_NAME, uri);

            // 增加请求头
            HttpHeaders headers = new HttpHeaders();
            // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set(AUTHORIZATION_HEADER_NAME, "oauth2 " + token);

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(multiMap,
                                                                                                             headers);

            TokenValidate validate = restTemplate
                .exchange(tokenValidateUrl, HttpMethod.POST, entity, TOKEN_VALIDATE_TYPE, (Object) null)
                .getBody();

            return validate;

        } catch (AuthException e) {
            TokenValidate validate = new TokenValidate();
            validate.setErrorCode(e.getErrorCode());
            validate.setErrorDescription(e.getErrorDescription());
            return validate;
        } catch (Exception e) {
            String msg = String.format(FilterConstants.AUTH2_SERVICE_ERROR_MSG_TEMP,
                                       token, uri, methodType, clientIp, userIp, "");
            logger.error(msg, e);
            throw e;
        }

    }

    private String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader(X_FORWARDED_FOR_HEADER_NAME);
        if (null == ip || ip.trim().length() == 0 || UNKNOWN_HEADER_VALUE.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP_HEADER_NAME);
        }
        if (null == ip || ip.trim().length() == 0 || UNKNOWN_HEADER_VALUE.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP_HEADER_NAME);
        }
        if (null == ip || ip.trim().length() == 0 || UNKNOWN_HEADER_VALUE.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
