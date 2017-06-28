package com.eefung.client;

import com.eefung.restful.server.Customer;
import com.eefung.utils.IOsUitils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.*;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.resource.URIResolver;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/6/28.
 * @author luojie<luojie@eefung.com>
 */
public class ClientTest {
    /**
     * 请求地址 ：URL
     */
   final static String urls = "http://localhost:9000/customerservice/";
   URL url = null;
   InputStream in =null;

    /**
     * GET请求
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        ;
        System.out.println("HTTP GET request to query customer info+");
        url = new URL(urls+"customers/123");
        InputStream in = url.openStream();
        System.out.println(IOsUitils.getStringFromInputStream(in));
    }

    /**
     * GET请求
     * @throws Exception
     */
    @Test
    public void testGets() throws Exception{
        System.out.println("HTTP GET request to query +嵌套 info+****");
        url = new URL(urls+"orders/223/products/323");
        in = url.openStream();
        System.out.println(IOsUitils.getStringFromInputStream(in));
    }

    /**
     * test put
     * @throws Exception
     */
    @Test
    public void testPut() throws Exception {
        System.out.println("HTTP PUT request to update customer info+");
        ClientTest client = new ClientTest();
        //加载xml文件
        String inputFile = client.getClass().getResource("/update_customer.xml").getFile();
        URIResolver resolver = new URIResolver(inputFile);
        File input = new File(resolver.getURI());
        PutMethod put = new PutMethod(urls + "customers");
        RequestEntity entity = new FileRequestEntity(input, "text/xml; charset=UTF-8");
        put.setRequestEntity(entity);
        HttpClient httpclient = new HttpClient();

        try {
            int result = httpclient.executeMethod(put);
            System.out.println("Response status code+++++: " + result);
            System.out.println("Response body: +======");
            System.out.println(put.getResponseBodyAsString() + "000");
        } finally {

            put.releaseConnection();
        }

    }

    /**
     * Test post
     *
     * @throws Exception
     */
    @Test
    public void testPost() throws Exception{
        System.out.println("HTTP POST request to add customer+");
        ClientTest client = new ClientTest();
        String inputFile = client.getClass().getResource("/add_customer.xml").getFile();
        URIResolver resolver = new URIResolver(inputFile);
        File input = new File(resolver.getURI());
        PostMethod post = new PostMethod(urls+"customers");
        post.addRequestHeader("Accept", "text/xml");
        FileRequestEntity entity = new FileRequestEntity(input, "text/xml; charset=UTF-8");
        post.setRequestEntity(entity);
        HttpClient httpclient = new HttpClient();

        try {
            int result = httpclient.executeMethod(post);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(post.getResponseBodyAsString());
        } finally {
            post.releaseConnection();
        }

    }

    /**
     * test Delete
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception{
        System.out.println("HTTP Delete request to delete customer+");
        DeleteMethod deleteMethod = new DeleteMethod(urls+"customers/123");
        HttpClient httpClient = new HttpClient();
       int result =  httpClient.executeMethod(deleteMethod);
       System.out.println(result);
    }
}
