package com.hospital.baronic.model;


import com.hospital.baronic.define.ResponseCode;
import lombok.Data;

@Data
public class ResponseModel<E>  {
	private ResponseCode statusCode;
	private String message;
	private E data;
	
	public ResponseModel() {}
	
	public ResponseModel(ResponseCode statusCode, E data) {
		this.setStatusCode(statusCode);
		this.setMessage(statusCode.getMessage());
		this.setData(data);
	}
	
	public ResponseModel(ResponseCode statusCode, E data, Object msg) {
		this.setStatusCode(statusCode);
		this.setData(data);
		if(msg == null) {
			this.setMessage(statusCode.getMessage());
		}else {
			this.setMessage(String.format("%s[%s]", statusCode.getMessage(),msg.toString()));
		}
				
	}
}
