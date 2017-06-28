package com.eefung.dbs;

import com.eefung.restful.server.Customer;
import com.eefung.restful.server.Order;
import com.eefung.restful.server.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 * @author luojie<luojie@eefung.com>
 */
public class DBs {
    private static Map<Long, Product> products = new HashMap<Long, Product>();
    private static Map<Long, Customer> customers = new HashMap<Long, Customer>();
    private  static Map<Long, Order> orders = new HashMap<Long, Order>();
    static {
        Product p = new Product();
        p.setId(323);
        p.setDescription("product 323");
        products.put(p.getId(), p);

        Customer c = new Customer();
        c.setName("John");
        c.setId(123);
        customers.put(c.getId(), c);

        Order o = new Order();
        o.setDescription("order 223");
        o.setId(223);
        orders.put(o.getId(), o);

    }

    /**
     * 订单数据
     * @return products
     */
    public static Map<Long, Product>  getProductsDB(){
        return products;
    }

    /**
     * 返回顾客数据
     * @return
     */
    public static Map<Long, Customer> getCustomersDB(){
        return customers;
    }

    public static Map<Long, Order> getOrdersDB(){
        return orders;
    }

}
