package io.github.paulooorg.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

public class ValidationException extends ApiException {
	private static final long serialVersionUID = 1L;
	
	private List<FieldError> fieldErrors = new ArrayList<>();
	
	public ValidationException(String i18n, Object[] params, List<FieldError> fieldErrors) {
		super(i18n, params, ErrorCodes.VALIDATION_ERROR, Response.Status.BAD_REQUEST);
		this.fieldErrors = fieldErrors;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}
}
