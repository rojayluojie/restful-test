package com.eefung.restful.server;

/**
 * Created by Administrator on 2017/6/28.
 */
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2017/6/28.
 * @author luojie<luojie@eefung.com>
 */

@XmlRootElement(name = "Product")
public class Product {
    private long id;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        this.description = d;
    }
}

