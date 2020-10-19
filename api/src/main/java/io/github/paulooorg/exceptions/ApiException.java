package io.github.paulooorg.exceptions;

import javax.ws.rs.core.Response;

public class ApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String i18n;
	
	private Object[] params;
	
	private String errorCode;
	
	private Response.Status httpStatus;
	
	public ApiException(String i18n, Object[] params, String errorCode, Response.Status httpStatus) {
		this.i18n = i18n;
		this.params = params;
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public String getI18n() {
		return i18n;
	}

	public Object[] getParams() {
		return params;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Response.Status getHttpStatus() {
		return httpStatus;
	}
}
