/*
 * Copyright (c) 2010-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha.mstore;

import com.antfact.biz.common.captcha.Captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>功能描述,该部分必须以中文句号结尾。</p>
 *
 * 创建日期 2017年3月8日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $version$
 */
@Component
public class CaptchaDBManager {

    private static final String COLLECTION_NAME = "captchas";
    private static final String CAPTCHA_ID = "captchaId";
    private static final String TEXT = "text";

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 验证指定id与验证码的信息是否正确
     * @param id {@link String} id
     * @param text {@link String} 验证码
     * @return {@link Boolean} 正确返回true,否则返回false
     */
    public boolean verification(String id, String text) {
        Query query = new Query().addCriteria(Criteria.where(CAPTCHA_ID).is(id))
            .addCriteria(Criteria.where(TEXT).is(text.toLowerCase()));

        CaptchaCode code = mongoTemplate.findOne(query, CaptchaCode.class, COLLECTION_NAME);

        if (null == code || code.getExpirationTime() < System.currentTimeMillis()) {
            return false;
        }
        mongoTemplate.remove(query, COLLECTION_NAME);
        return true;
    }

    /**
     * 插入验证码信息
     * @param clientId {@link String} 应用id
     * @param userId {@link String} 用户id
     * @param text {@link String} 验证码
     * @param expirationTime {@link String} 过期时间点
     * @return {@link Captcha} 验证码信息
     */
    public CaptchaCode insert(String clientId, String userId, String text, long expirationTime) {
        CaptchaCode code = new CaptchaCode();
        code.setCaptchaId(UUID.randomUUID().toString());
        code.setClientId(clientId);
        code.setExpirationTime(expirationTime);
        code.setText(text.toLowerCase());
        code.setUserId(userId);

        mongoTemplate.insert(code, COLLECTION_NAME);

        return code;
    }
}
