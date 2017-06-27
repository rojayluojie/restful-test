package com.antfact.biz.commons.captcha.rest;

import com.antfact.biz.commons.captcha.mstore.Person;
import com.antfact.biz.commons.captcha.mstore.PersonService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2017/6/23.
 * @author luojie(luojie@eefung.com)
 *
 */
@Component
public class PersonRest {

    private Logger logger = LoggerFactory.getLogger(PersonRest.class);
    @Autowired
    private PersonService personService;
    @GET
    @Path("getPerson")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest request,
                        @QueryParam("id") int id
                        ) throws Exception {

        if (id <= 0 ) {
            logger.error("参数错误. id必须大于等于0");

        }
        return Response.ok(personService.getPerson(id)).build();
    }

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest request){

        return Response.ok(personService.getAll()).build();
    }

    @PUT
    @Path("updatePerson")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(@Context HttpServletRequest request,
                                 @QueryParam("id") int id,
                                 @QueryParam("Person") Person person
    ){
        if(id <= 0 || person == null){
            logger.error("参数错误");
        }
        return Response.ok(personService.updatePerson(id, person)).build();
    }

    @POST
    @Path("addPerson")
    public Response addPerson(@Context HttpServletRequest request,
                              @QueryParam("Person") Person person

    ){
        if(person==null){
            logger.error("参数错误,空对象");
        }
        return Response.ok(personService.addPerson(person)).build();
    }
}
