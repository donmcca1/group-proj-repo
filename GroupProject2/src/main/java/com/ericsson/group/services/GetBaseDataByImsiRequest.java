package com.ericsson.group.services;

import com.ericsson.group.businessEntities.RequestAdapter;

public class GetBaseDataByImsiRequest extends RequestAdapter{
	protected Long Imsi;
	
	public GetBaseDataByImsiRequest(String[] request) {
		super(request);
		setImsi(request[1]);
	}
	
	public Long getImsi() {
		return Imsi;
	}
	
	public void setImsi(String Imsi) {
		this.Imsi = Long.parseLong(Imsi);
	}
	

}
