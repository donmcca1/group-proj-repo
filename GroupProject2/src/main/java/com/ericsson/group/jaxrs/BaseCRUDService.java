package com.ericsson.group.jaxrs;

import java.sql.Date;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	public Collection<?> getBaseData(){
		return service.getAllBaseData();
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
	@Path("/date")
	public BaseDataList getBaseDataByDate(@QueryParam("start") Date startDate, @QueryParam("end") Date endDate){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getBaseDataByDate(startDate, endDate));
		return list;
	}
	
	//--- SELECT BY DATE ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/numfail")
	public Collection<?> getBaseDataByDate2(@QueryParam("start2") Date startDate, @QueryParam("end2") Date endDate){
		System.out.println("here in date 2");
		Collection<?> v = service.getNumFailuresAndDurationByDate(startDate, endDate);
		System.out.println(v.size());
		return v;
	}
}
