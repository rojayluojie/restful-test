package com.rojay.date;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建时间 2017/6/21.
 * @author luojie(luojie@eefung.com)
 *@since $version$
 */
public class DateRestService {

    /*
     *若希望一个Java类能够处理REST请求，则这个类必须至少添加一个@Path("/")的annotation；
     对于方法，这个annotation是可选的，如果不添加，则继承类的定义。
     */
    @Path("/date")
    //get请求注解
    @GET
    //表示类或者方法返回的MIME数据类型。MediaType.APPLICATION_XML--生成xml页面
    @Produces(MediaType.APPLICATION_XML)
    public Response praseDateString(@QueryParam("time") long time,@QueryParam("formet")
                                    @DefaultValue("yyyy-MM-dd HH:mm:ss") String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        String dateTime = dateFormat.format(new Date(time));
        DateBean dateBean = new DateBean();
        dateBean.setDateString(format);
        dateBean.setDateFormat(dateTime);
        return  Response.status(Response.Status.OK).entity(dateBean).build();
    }
}
