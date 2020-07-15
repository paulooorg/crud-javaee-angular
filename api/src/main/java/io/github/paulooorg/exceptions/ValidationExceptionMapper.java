package io.github.paulooorg.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.github.paulooorg.infra.Messages;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
	@Override
	public Response toResponse(ValidationException exception) {
		ValidationError error = new ValidationError();
		error.setMessage(Messages.get(exception.getI18n(), exception.getParams()));
		error.setCode(exception.getErrorCode());
		error.setFieldErrors(exception.getFieldErrors());
		return Response.status(exception.getHttpStatus()).entity(error).build();
	}
}
