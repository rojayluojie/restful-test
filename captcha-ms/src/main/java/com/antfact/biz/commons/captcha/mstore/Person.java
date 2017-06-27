package com.antfact.biz.commons.captcha.mstore;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/23.
 * @author luojie(luojie@eefung.com)
 * @since $version$
 */
public class Person implements Serializable{
    private static final long serialVersionUID = -8089404520256917493L;
    /**
     * 编号
     */
    private int id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 所属部门
     */
    private String department;

    /**
     * 个人简介
     */
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
