package com.ericsson.group.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ericsson.group.entities.BaseDataList;
import com.ericsson.group.services.BaseService;

@Path("/base")
public class BaseCRUDService {

	@Inject
	private BaseService service;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public BaseDataList getBaseData(){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getAllBaseData());
		return list;
		
	}
}
