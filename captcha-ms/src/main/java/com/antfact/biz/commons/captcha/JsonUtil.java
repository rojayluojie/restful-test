/*
 * Copyright (c) 2016-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2016-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.zip.DataFormatException;

/**
 * <p>Json数据转换工作类。</p>
 *
 * 创建日期 2016年12月28日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $1.0.0$
 */
public class JsonUtil {

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper JSON_OBJECT_MAPPER;

    static {
        SimpleModule simpleModule = new SimpleModule("AntCensor Jackson Module", Version.unknownVersion());

        JSON_OBJECT_MAPPER = new ObjectMapper();
        JSON_OBJECT_MAPPER.registerModule(simpleModule);
    }

    /**
     * 序列化对象为 JSON 字符串
     * 
     * @param obj
     *        {@link Object} Object对象,当转换失败时则返回null
     * @return {@link Stirng} 符合json规范的String串
     */
    public static String toJson(Object obj) {
        try {
            return JSON_OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception ex) {
            logger.error("序列化对象为 JSON 字符串时出现错误！", ex);
            return null;
        }
    }
    
    /**
     * 反序列化 JSON 字符串为指定类型的对象
     * 
     * @param json
     *        {@link Stirng} 符合json规范的字符串
     * @param clazz
     *        {@link Class} 转换的目标类型
     * @return {@link Object} 目标对象,如果转换过程中发生错误则返回null
     * @throws DataFormatException
     *         当数据格式不正确或者转换过程是发生不可预知错误时抛出此异常
     */
    public static <T> T parseJson(String json, Class<T> clazz) throws Exception {
        try {
            return JSON_OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            logger.error("反序列化 JSON 字符串为指定类型的对象时出现错误！" + json, e);
            throw e;
        }
    }

    /**
     * 将Json格式字符串转换成指定类型的数据对象
     * 
     * @param json
     *        {@link Stirng} 符合json规范的字符串
     * @param valueTypeRef
     *        {@link TypeReference} 符合json规范的字符串
     * @return {@link Object} 目标对象,如果转换过程中发生错误则返回null
     * @throws DataFormatException
     *         当数据格式不正确或者转换过程是发生不可预知错误时抛出此异常
     */
    public static <T> T parseJson(String json, TypeReference<T> valueTypeRef) throws Exception {
        try {
            return JSON_OBJECT_MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            logger.error("反序列化 JSON 字符串为指定类型的对象时出现错误！" + json, e);
            throw e;
        }
    }
}
