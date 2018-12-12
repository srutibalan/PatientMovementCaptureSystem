package com.cerner.pmcs.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.cerner.pmcs.datamodel.ErrorMessage;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
