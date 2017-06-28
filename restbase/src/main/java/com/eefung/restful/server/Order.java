package com.eefung.restful.server;

import com.eefung.dbs.DBs;

import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2017/6/28.
 * @author luojie<luojie@eefung.com>
 */
@XmlRootElement(name = "Order")
public class Order {
    private long id;
    private String description;
    private Map<Long, Product> products =  DBs.getProductsDB();

    public Order() {

    }

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

    @GET
    @Path("products/{productId}/")
    public Product getProduct(@PathParam("productId")int productId) {
        System.out.println("----invoking getProduct with id: " + productId);
        Product p = products.get(new Long(productId));
        return p;
    }

}

