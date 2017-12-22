package com.vehiclemanagement.restfulcrud.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.ValidationException;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
    public Response toResponse(Throwable ex) {
		Response.StatusType type = getStatusType(ex);
        System.out.println(type);
        Error error = new Error(
                type.getStatusCode(),
                type.getReasonPhrase(),
                ex.getLocalizedMessage());

        return Response.status(error.getStatusCode())
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response.StatusType getStatusType(Throwable ex) {
    	if(ex instanceof ValidationException)
    	{
    		return Response.Status.BAD_REQUEST;
    	}
    	else
        /*if (ex instanceof WebApplicationException) {
            return((WebApplicationException)ex).getResponse().;
        } else*/ {
            return Response.Status.INTERNAL_SERVER_ERROR;
        }
    }

}