/*
 * Copyright (c) 2016-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2016-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha.filter;

/**
 * 
 * <p>过滤器服务相关常量。</p>
 *
 * 创建日期 2016年7月27日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $1.0.0$
 */
public abstract class FilterConstants {

    public static final String AUTH2_SERVICE_ERROR_MSG_TEMP = "请求认证中心验证token时发生错误.相关参数: token: %1$s , request uri: %2$s , request method: %3$s , client ip: %4$s , user ip : %5$s , 认证服务响应数据: %6$s";

    public static final String REST_ID_REQ_PARAM_NAME = "rest_id";
    public static final String REMOTE_ADDR_REQ_PARAM_NAME = "remote_addr";
    public static final String USER_IP_REQ_PARAM_NAME = "user_ip";
    public static final String PERMISSION_PARAM_NAME = "permission";
    public static final String USER_AGENT_REP_PARAM_NAME = "User-Agent";

    public static final String ERROR_CODE_RES_NAME = "error_code";
    public static final String USER_ID_RES_NAME = "user_id";
    public static final String USER_NAME_RES_NAME = "user_name";
    public static final String NICK_NAME_RES_NAME = "nick_name";
    public static final String CLIENG_ID_RES_NAME = "client_id";
    public static final String DESCRIPTION_RES_NAME = "error_description";

}
