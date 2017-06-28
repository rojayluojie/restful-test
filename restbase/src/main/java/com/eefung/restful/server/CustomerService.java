package com.eefung.restful.server;

import com.eefung.dbs.DBs;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;
/**
 * Created by Administrator on 2017/6/28.
 * @author luojie<luojie@eefung.com>
 */
@Path("/customerservice/")
@Produces("text/xml")
public class CustomerService {
    long currentId = 123;
    Map<Long, Customer> customers = DBs.getCustomersDB();
    Map<Long, Order> orders = DBs.getOrdersDB();

    public CustomerService() {

    }

    @GET
    @Path("/customers/{id}/")
    public Customer getCustomer(@PathParam("id") String id) {
        System.out.println("----invoking getCustomer, Customer id is: " + id);
        long idNumber = Long.parseLong(id);
        Customer c = customers.get(idNumber);
        return c;
    }

    @PUT
    @Path("/customers/")
    public Response updateCustomer(Customer customer) {
        System.out.println("----invoking updateCustomer, Customer name is: " + customer.getName()+customer.getId());

        Customer c = customers.get(customer.getId());
       System.out.println("更新之前："+customers);
        Response r;
        if (c != null) {
            customers.put(customer.getId(), customer);
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }
        System.out.println("更新之后："+customers+customer.getName());
        return r;
    }

    @POST
    @Path("/customers/")
    public Response addCustomer(Customer customer) {
        System.out.println("----invoking addCustomer, Customer name is: " + customer.getName());
        customer.setId(++currentId);

        customers.put(customer.getId(), customer);

        return Response.ok(customer).build();
    }

    @DELETE
    @Path("/customers/{id}/")
    public Response deleteCustomer(@PathParam("id") String id) {
        System.out.println("----invoking deleteCustomer, Customer id is: " +id);
        long idNumber = Long.parseLong(id);
        Customer c = customers.get(idNumber);

        Response r;
        if (c != null) {
            r = Response.ok().build();
            customers.remove(idNumber);
        } else {
            r = Response.notModified().build();
        }
        System.out.println("test-delete");
        return r;
    }

    @Path("/orders/{orderId}/")
    public Order getOrder(@PathParam("orderId") String orderId) {
        System.out.println("----invoking getOrder, Order id is: " + orderId);
        long idNumber = Long.parseLong(orderId);
        Order c = orders.get(idNumber);
        return c;
    }

}
