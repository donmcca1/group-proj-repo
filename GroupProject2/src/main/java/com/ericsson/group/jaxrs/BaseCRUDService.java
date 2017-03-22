package com.ericsson.group.jaxrs;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

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
	
	//--- COUNT BY MODEL AND DATE ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/date/ue_type")
	public Long countByModelAndDate(@QueryParam("ue_type") Integer ue_type,
			@QueryParam("start") Date startDate, @QueryParam("end") Date endDate){
		return (Long)service.countByModelAndDate(ue_type, startDate, endDate);
	}

	//--- SELECT BY DATE NUM FAILURES AND DURATION---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/numfail")
	public List<Object[]> getBaseDataByDate2(@QueryParam("start2") Date startDate, @QueryParam("end2") Date endDate){
		List<Object[]> v = service.getNumFailuresAndDurationByDate(startDate, endDate);
		return v;
	}
	
	//--- SELECT BY IMSI, COUNT FAILURES BY DATE ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/date/imsi")
	public Long getFailuresByDate(@QueryParam("imsi") Long imsi, @QueryParam("start") Date startDate, 
			@QueryParam("end") Date endDate){
		System.out.println("CRUD");
		return (Long)service.getFailuresByDate(imsi, startDate, endDate);
	}
	
	//--- LILY COUNT OF EVENTID/CAUSECODE BY MODEL ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ue_type/count")
	public Collection<?> countByModelEventIdCauseCode(@QueryParam("ue_type") Integer ue_type){
		Collection<?> c = service.countByModelEventIdCauseCode(ue_type);
		return c;
	}

}
