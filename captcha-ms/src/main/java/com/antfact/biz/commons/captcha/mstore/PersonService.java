package com.antfact.biz.commons.captcha.mstore;

import com.antfact.biz.commons.captcha.dbs.DBUtil;
import com.antfact.biz.commons.captcha.mstore.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/23.
 * @author luojie()
 */
@Component
public class PersonService {

    private Map<Integer,Person> personMap = DBUtil.getPersonMap();

    public Person[] getAll() {
        return personMap.values().toArray(new Person[]{});
    }

    public Person getPerson(int id) {
        return personMap.get(id);
    }

    public Person addPerson(Person person) {
        System.out.println(  " name:" + person.getName());
        personMap.put(personMap.size()+1, person);
       // customers.put(customer.getId(), customer);
        return person;
    }

    public Person updatePerson(int id, Person person) {
        person.setId(id);
        System.out.println("update:"+person.getId() + " name:" + person.getName());
        System.out.println();
        personMap.put(id, person);
        return person;
    }

}
