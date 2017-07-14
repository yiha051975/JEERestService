package com.sheldon.rest.providers;

import com.sheldon.rest.common.representation.ErrorRepresentation;

import javax.persistence.NoResultException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Sheld on 7/13/2017.
 */
@Provider
public class NoResultExceptionMapper implements ExceptionMapper<NoResultException> {
    @Override
    public Response toResponse(NoResultException exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorRepresentation(Response.Status.NOT_FOUND.getStatusCode(), exception.getMessage()))
                .build();
    }
}
