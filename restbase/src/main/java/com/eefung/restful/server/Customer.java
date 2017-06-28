package com.eefung.restful.server;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2017/6/28.
 * @author luojie<luojie@eefung.com>
 */
@XmlRootElement(name = "Customer")
public class Customer {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
