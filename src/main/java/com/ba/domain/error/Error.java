package com.ba.domain.error;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
	private String code;
    private String businessMessage;
    private String developerMessage;
    private String developerLink;
    private List<Error> errors;
    Date timeStamp;
    String uniqueIdentifier;
 
    public Error(String code, String businessMessage, 
    		String developerMessage, String developerLink,
    		Date timeStamp, String uniqueIdentifier,
    		List<Error> errors) {
        super();
        this.code = code;
        this.businessMessage = businessMessage;
        this.developerMessage = developerMessage;
        this.developerLink = developerLink;
        this.timeStamp = timeStamp;
        this.uniqueIdentifier =  uniqueIdentifier;
        
        this.errors = errors;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBusinessMessage() {
		return businessMessage;
	}

	public void setBusinessMessage(String businessMessage) {
		this.businessMessage = businessMessage;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getDeveloperLink() {
		return developerLink;
	}

	public void setDeveloperLink(String developerLink) {
		this.developerLink = developerLink;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}
}
