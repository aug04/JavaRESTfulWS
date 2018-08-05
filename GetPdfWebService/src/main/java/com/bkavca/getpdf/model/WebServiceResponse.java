package com.bkavca.getpdf.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Kết quả trả về của web service
 * 
 * @author HungDMc
 *
 */
@XmlRootElement(name = "webServiceResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebServiceResponse {

	private boolean status;
	private String message;
	private String data;
	
	public WebServiceResponse() {
		
	}

	public WebServiceResponse(boolean status, String message, String data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
