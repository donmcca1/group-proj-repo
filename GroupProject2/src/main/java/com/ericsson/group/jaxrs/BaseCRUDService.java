package com.ericsson.group.jaxrs;

import java.sql.Date;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ericsson.group.entities.BaseDataList;
import com.ericsson.group.services.BaseService;

@Path("/base")
public class BaseCRUDService {

	@Inject
	private BaseService service;
	
	//--- SELECT ALL ---//
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public BaseDataList getBaseData(){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getAllBaseData());
		return list;
	}
	
	//--- SELECT BY IMSI ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{imsi}")
	public BaseDataList getBaseDataByImsi(@PathParam("imsi") Long imsi){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getBaseDataByImsi(imsi));
		return list;
	}
	
	//--- SELECT BY DATE ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{startDate}/{endDate}")
	public BaseDataList getBaseDataByDate(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getBaseDataByDate(startDate, endDate));
		return list;
	}
	
}
