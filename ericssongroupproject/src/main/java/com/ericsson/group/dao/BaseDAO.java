package com.ericsson.group.dao;

import com.ericsson.group.entities.BaseData;

import javax.ejb.Local;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Local
public interface BaseDAO {
	//--- SELECT ALL (NO FRONT END) ---//
	public Collection<BaseData> getBaseData();
	
	//*******************//
	//*** CSR QUERIES ***//
	//*******************//
	
	//--- 1. SELECT BY IMSI, RETURN EVENT_ID, CAUSE_CODE ---//
	// currently returns all; selection made at front end
	public Collection<BaseData> getBaseDataByImsi(Long imsi);

	//--- 2. SELECT BY IMSI & DATE, COUNT NUMBER OF FAILURES ---//
	public Long getFailuresByDate(Long imsi, Date startDate, Date endDate);

	//--- 3. SELECT BY IMSI, RETURN UNIQUE CAUSE CODES ---//
	public Collection<?> getCauseCodeByImsi(Long imsi);


	//******************//
	//*** SE QUERIES ***//
	//******************//
	
	//--- 4. SELECT BY DATE, RETURN IMSI ---//
	// currently returns all; selection made at front end
	public Collection<BaseData> getBaseDataByDate(Date startDate, Date endDate);

	//--- 5. SELECT BY MODEL & DATE, COUNT NUMBER OF FAILURES ---//
	public Long countByModelAndDate(String ue_type, Date startDate, Date endDate);

	//--- 6. SELECT BY CAUSE_CODE, RETURN IMSIs ---//
	public Collection<BaseData> getImsiByCauseCode(Integer cause_code);
	
	//*******************//
	//*** NME QUERIES ***//
	//*******************//
	
	//--- 7. SELECT BY DATE, COUNT FAILURES, SUM DURATION BY IMSI ---//
	public Collection<?> getNumFailuresAndDurationByDate(Date startDate, Date endDate);

	//--- 8. SELECT BY UE_TYPE, RETURN UNIQUE EVENT_ID, CAUSE_CODE COMBINATIONS & COUNT ---//
	public Collection<?> countByModelEventIdCauseCode(String ue_type);

    //--- 9. SELECT BY DATE, RETURN TOP 10 MARKET/OPERATOR/CELL_ID COMBINATIONS ---//
    public Collection<BaseData> top10MarketOperatorCell(Date startDate, Date endDate);
	
	//--- 10. SELECT BY DATE, RETURN TOP 10 IMSIs ---//
    public Collection<BaseData> top10imsi(Date startDate, Date endDate);

	//***********************//
	//*** SA ONLY QUERIES ***//
	//***********************//
	
    //****************************//
  	//*** AUTOCOMPLETE QUERIES ***//
  	//****************************//
    
    public Collection<?> allIMSI(Long imsi);
    
    public Collection<?> allModels(String model);

}
