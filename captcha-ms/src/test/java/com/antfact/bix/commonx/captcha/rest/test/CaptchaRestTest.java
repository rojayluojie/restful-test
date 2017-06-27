/*
 * Copyright (c) 2010-2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2010-2016湖南蚁坊软件有限公司。保留所有权利。
 */
package com.antfact.bix.commonx.captcha.rest.test;

import com.antfact.biz.commons.captcha.mstore.Person;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * <p>功能描述,该部分必须以中文句号结尾。</p>
 *
 * 创建日期 2017年3月13日
 * @author chenzhixiong(chenzhixiong@eefung.com)
 * @since $version$
 */
public class CaptchaRestTest {

    RestTemplate restTemp = new RestTemplate();


    @Test
    public void testCaptchaValidate(){
        org.springframework.http.ResponseEntity<Object> result = restTemp
                .getForEntity("http://127.0.0.1:8059/captcha/verification?id=e3rewrrweq&text=ffff",
                        Object.class);
        String str = result.toString();
        //Boolean result = restTemp.getForObject("http://127.0.0.1:8059/captcha/verification?id=e3rewrrweq&text=ffff",
          //                                     Boolean.class);
        System.out.println(result.getBody()+"   ***"+str);

    }
    @Test
    public void testGetPerson() throws Exception{

        org.springframework.http.ResponseEntity<Person> result = restTemp
                .getForEntity("http://127.0.0.1:8059/captcha/getPerson?id=2",Person.class);
        System.out.println(result.getBody());
    }

    @Test
    public void testGetAll() throws Exception{

        org.springframework.http.ResponseEntity<Person> result = restTemp
                .getForEntity("http://127.0.0.1:8059/captcha/getAll",Person.class);
        System.out.println(result.getBody());
    }
    @Test
    public void testUpdatePerson() throws Exception{
        Person person = new Person();
        person.setId(12);
        person.setName("lulu");
        person.setDepartment("dep");
        person.setDesc("开发");
        org.springframework.http.ResponseEntity<Person> result = restTemp
                .getForEntity("http://127.0.0.1:8059/captcha/updatePerson?id=12&person=person",Person.class);
        System.out.println(result.getBody());
    }

}
