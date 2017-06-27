/*
 *  Copyright (c) 2016 EEFUNG Software Co.Ltd. All rights reserved.
 *  版权所有(c) 2016 湖南蚁坊软件有限公司。保留所有权利。
 *
 */

package com.antfact.biz.commons.captcha.provider;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Component
public class ExceptionHandler implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}