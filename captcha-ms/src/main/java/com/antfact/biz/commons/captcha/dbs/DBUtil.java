package com.antfact.biz.commons.captcha.dbs;

import com.antfact.biz.commons.captcha.mstore.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/23.
 */
public class DBUtil {
    //集合代替数据库
    private static Map<Integer, Person> personMap;

    static{
        personMap = new HashMap<Integer, Person>();
        int id = 1;
        Person person = new Person();
        person.setId(id++);
        person.setName("rojay");
        person.setDepartment("业务部");
        person.setDesc("java开发组成员");
        personMap.put(person.getId(), person);

        person = new Person();
        person.setId(id++);
        person.setName("lucy");
        person.setDepartment("数据部");
        person.setDesc("db开发组成员");
        personMap.put(person.getId(), person);

        person = new Person();
        person.setId(id++);
        person.setName("jack");
        person.setDepartment("Ui");
        person.setDesc("Ui开发组成员");
        personMap.put(person.getId(), person);
    }

    public static Map<Integer,Person> getPersonMap(){
        return personMap;
    }
}
