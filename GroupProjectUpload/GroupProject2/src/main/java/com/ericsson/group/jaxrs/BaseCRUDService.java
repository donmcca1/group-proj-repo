package com.ericsson.group.jaxrs;

import com.ericsson.group.entities.BaseDataList;
import com.ericsson.group.services.BaseService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Path("/base")
public class BaseCRUDService {
	@Inject
	private BaseService service;
	
	//--- SELECT ALL (NO FRONT END) ---//
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public BaseDataList getBaseData(){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getAllBaseData());
		return list;
	}
	
	
	//*******************//
	//*** CSR QUERIES ***//
	//*******************//
	
	//--- 1. SELECT BY IMSI, RETURN EVENT_ID, CAUSE_CODE ---//
	// currently returns all; selection made at front end
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{imsi}")
	public BaseDataList getBaseDataByImsi(@PathParam("imsi") Long imsi){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getBaseDataByImsi(imsi));
		return list;
	}
	
	//--- 2. SELECT BY IMSI & DATE, COUNT NUMBER OF FAILURES ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/date/imsi")
	public Long getFailuresByDate(@QueryParam("imsi") Long imsi, @QueryParam("start") Date startDate, 
			@QueryParam("end") Date endDate){
		System.out.println("CRUD");
		return (Long)service.getFailuresByDate(imsi, startDate, endDate);
	}
	
	//--- 3. SELECT BY IMSI, RETURN UNIQUE CAUSE CODES ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cause")
	public BaseDataList getCauseCodeByImsi(@QueryParam("imsi") Long imsi){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getBaseDataByImsi(imsi));
		return list;
	}


	//******************//
	//*** SE QUERIES ***//
	//******************//
	
	//--- 4. SELECT BY DATE, RETURN IMSI ---//
	// currently returns all; selection made at front end
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/date")
	public BaseDataList getBaseDataByDate(@QueryParam("start") Date startDate, @QueryParam("end") Date endDate){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getBaseDataByDate(startDate, endDate));
		return list;
	}
	
	//--- 5. SELECT BY MODEL & DATE, COUNT NUMBER OF FAILURES ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/date/ue_type")
	public Long countByModelAndDate(@QueryParam("ue_type") Integer ue_type,
			@QueryParam("start") Date startDate, @QueryParam("end") Date endDate){
		return (Long)service.countByModelAndDate(ue_type, startDate, endDate);
	}

	//--- 6. SELECT BY CAUSE_CODE, RETURN IMSIs ---//
	@Path("/numfail")
	public List<Object[]> getBaseDataByDate2(@QueryParam("start2") Date startDate, @QueryParam("end2") Date endDate){
		List<Object[]> v = service.getNumFailuresAndDurationByDate(startDate, endDate);
		return v;
	}
	
	//--- 8. SELECT BY UE_TYPE, RETURN UNIQUE EVENT_ID, CAUSE_CODE COMBINATIONS & COUNT ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ue_type/count")
	public Collection<?> countByModelEventIdCauseCode(@QueryParam("ue_type") Integer ue_type){
		Collection<?> c = service.countByModelEventIdCauseCode(ue_type);
		return c;
	}

	
	//--- 9. SELECT BY DATE, RETURN TOP 10 MARKET/OPERATOR/CELL_ID COMBINATIONS ---//
	
	//--- 10. SELECT BY DATE, RETURN TOP 10 IMSIs ---//

	//***********************//
	//*** SA ONLY QUERIES ***//
	//***********************//
	
	//assign users

}
