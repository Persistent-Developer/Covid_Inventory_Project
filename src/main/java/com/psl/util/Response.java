package com.psl.util;

public class Response<T> {
	int status;
	String statusMessage;
	T result;
    String Token;
    int totalElements;
    private Object object;
//    private DetailError detailsError;
    
//	public DetailError getDetailsError() {
//		return detailsError;
//	}
//
//	public void setDetailsError(DetailError detailsError) {
//		this.detailsError = detailsError;
//	}

	public Response(int status, String statusMessage, T result) {
		super();
		this.status = status;
		this.statusMessage = statusMessage;
		this.result = result;
	}

	public Response(int status, String statusMessage) {
		super();
		this.status = status;
		this.statusMessage = statusMessage;
	}

	   
	public Response(int status, String statusMessage, T result, String token) {
		super();
		this.status = status;
		this.statusMessage = statusMessage;
		this.result = result;
		Token = token;
	}
	
	public Response(int status, String statusMessage, T result, String token, Object object) {
		super();
		this.status = status;
		this.statusMessage = statusMessage;
		this.result = result;
		Token = token;
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public Response() {
		super();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getstatusMessage() {
		return statusMessage;
	}

	public void setstatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", statusMessage=" + statusMessage + ", result=" + result + "]";
	}
	
}
