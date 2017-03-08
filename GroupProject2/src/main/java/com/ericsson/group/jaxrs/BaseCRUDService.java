package com.ericsson.group.jaxrs;

import java.sql.Date;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ericsson.group.entities.BaseData;
import com.ericsson.group.entities.BaseDataList;

import com.ericsson.group.services.FacadeServiceInterface;

@Path("/base")
public class BaseCRUDService {

	@Inject
	private FacadeServiceInterface service;
	
	//--- SELECT ALL ---//
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public BaseDataList getBaseData(){
		BaseDataList list = new BaseDataList();
		
		String request = "GetAllBaseDataCommand";
		
		list.setBaseDataList((Collection<BaseData>) service.processRequest(request));
		
		return list;
	}
	
	//--- SELECT BY IMSI ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{imsi}")
	public BaseDataList getBaseDataByImsi(@PathParam("imsi") Long imsi){
		String request = "GetBaseDataByImsiCommand:" + imsi;
		
		BaseDataList list = new BaseDataList();
		
		list.setBaseDataList((Collection<BaseData>) service.processRequest(request));
		
		return list;
	}
	
	//--- SELECT BY DATE ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/date/{date}")
	public BaseDataList getBaseDataByDate(@PathParam("date") String date){
		String request = "GetBaseDataByDateCommand:" + date;
		
		BaseDataList list = new BaseDataList();
		
		list.setBaseDataList((Collection<BaseData>) service.processRequest(request));
		
		return list;
	}
	
}
