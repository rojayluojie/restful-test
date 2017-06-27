/*
 * Copyright (c) 2010-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.biz.commons.captcha;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.ApplicationPath;

/**
 * <p>验证码相关服务应用入口。</p>
 *
 * 创建日期 2017年3月8日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $version$
 */
@SpringBootApplication
@EnableDiscoveryClient
@ApplicationPath("/")
public class CaptchaApplication {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(CaptchaApplication.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
