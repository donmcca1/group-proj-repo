package com.ericsson.group.businessEntities;


// RequestAdapter to allow the FacadeService and FacadeDAO to communicate with different parameters
public class RequestAdapter {
	protected String type;
	protected String[] request;
	
	public RequestAdapter(String[] request) {
		this.request = request;
		setType(request[0]);
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
