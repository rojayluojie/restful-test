/*
 * Copyright (c) 2010-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha.rest;

import com.antfact.biz.common.captcha.factory.CaptchaFactory;
import com.antfact.biz.commons.captcha.JsonUtil;
import com.antfact.biz.commons.captcha.mstore.CaptchaCode;
import com.antfact.biz.commons.captcha.mstore.CaptchaDBManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * <p>功能描述,该部分必须以中文句号结尾。</p>
 *
 * 创建日期 2017年3月8日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $version$
 */
@Component
@Path(value = "/captcha")
@Produces({ MediaType.APPLICATION_JSON })
public class CaptchaRest {

    private Logger logger = LoggerFactory.getLogger(CaptchaRest.class);

    private static final String[] words = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                                                         "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
                                                         "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                                                         "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                                                         "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
                                                         "y", "z" };
    private static final Long EFFECTIVE_TIME = 20L * 60L * 1000L;

    @Autowired
    private CaptchaDBManager captchaDBManager;

    @GET
    @Produces("text/plain")
    public Response get(@Context HttpServletRequest request,
                        @QueryParam("width") int width,
                        @QueryParam("height") int height,
                        @QueryParam("effectiveTime") long effectiveTime) throws Exception {

        String userId = (String) request.getAttribute("USER_ID");
        String clientId = (String) request.getAttribute("CLIENT_ID");

        if (width <= 0 || height <= 0) {
            logger.error("参数错误. 宽度和高度都必须大于0. ");
            return Response.status(Status.BAD_REQUEST).entity("{\"errorCode\":\"00103\",\"description\":\"请求参数错误!\"}")
                .build();
        }

        effectiveTime = effectiveTime <= 0 ? EFFECTIVE_TIME : effectiveTime;

        String text = randomCode(4);
        byte[] byt = CaptchaFactory.getCaptchaData(text, height, width);
        CaptchaCode code = captchaDBManager.insert(clientId, userId, text, System.currentTimeMillis() + effectiveTime);

        Map<String, String> map = new HashMap<>();
        map.put(code.getCaptchaId(), Base64.encodeBase64String(byt));

        return Response.ok(JsonUtil.toJson(map)).build();
    }

    @GET
    @Path("verification")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest request,
                        @QueryParam("id") String id,
                        @QueryParam("text") String text) throws Exception {
        
        if (StringUtils.isBlank(id) || StringUtils.isBlank(text)) {
            logger.error("参数错误. id与text必须同时不为空. ");
            return Response.status(Status.BAD_REQUEST).entity("{\"errorCode\":\"00103\",\"description\":\"请求参数错误!\"}")
                .build();
        }
        return Response.ok(captchaDBManager.verification(id, text)).build();
    }

    /**
     * person
     * @param length
     * @return
     */


    private String randomCode(int length) {
        String code = "";
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * words.length);
            code = code + words[index];
        }
        return code;
    }
}
