package com.ericsson.group.jaxrs;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ericsson.group.entities.BaseData;
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{imsi}")
	public Collection<?> getEventIdCauseCode(@PathParam("imsi") Long imsi){
		return service.getEventIdCauseCode(imsi);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{imsi}/{date}")
	public Collection<?> getCallFailures(@PathParam("imsi") Long imsi, @PathParam("date") Date date){
		return service.getCallFailures(imsi,date);
	}
	
}
