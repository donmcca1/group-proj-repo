package com.ericsson.group.services;

import com.ericsson.group.dao.BaseDAO;
import com.ericsson.group.entities.BaseData;

import javax.ejb.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)// Ensures Transactions are used for ACID purposes
public class BaseServiceJPA implements BaseService{
	
	@EJB
	private BaseDAO dao;
	
	//--- SELECT ALL (NO FRONT END) ---//
	public Collection<BaseData> getAllBaseData() {
		return dao.getBaseData();
	}
	
	//*******************//
	//*** CSR QUERIES ***//
	//*******************//
	
	//--- 1. SELECT BY IMSI, RETURN EVENT_ID, CAUSE_CODE ---//
	// currently returns all; selection made at front end
	public Collection<BaseData> getBaseDataByImsi(Long imsi){
		return dao.getBaseDataByImsi(imsi);
	}

	//--- 2. SELECT BY IMSI & DATE, COUNT NUMBER OF FAILURES ---//
	public Long getFailuresByDate(Long imsi, Date startDate, Date endDate) {
		return dao.getFailuresByDate(imsi, startDate, endDate);
	}

	//--- 3. SELECT BY IMSI, RETURN UNIQUE CAUSE CODES ---//
	public Collection<?> getCauseCodeByImsi(Long imsi) {
		return dao.getCauseCodeByImsi(imsi);
	}
	
	//******************//
	//*** SE QUERIES ***//
	//******************//
	
	//--- 4. SELECT BY DATE, RETURN IMSI ---//
	// currently returns all; selection made at front end
	public Collection<BaseData> getBaseDataByDate(Date startDate, Date endDate){		
		return dao.getBaseDataByDate(startDate, endDate);
	}

	//--- 5. SELECT BY MODEL & DATE, COUNT NUMBER OF FAILURES ---//
	public Long countByModelAndDate(String ue_type, Date startDate, Date endDate){	
		return dao.countByModelAndDate(ue_type, startDate, endDate);
	}
	
	//--- 6. SELECT BY CAUSE_CODE, RETURN IMSIs ---//
	public Collection<BaseData> getImsiByCauseCode(Integer cause_code){
		return dao.getImsiByCauseCode(cause_code);
	}
	
	//*******************//
	//*** NME QUERIES ***//
	//*******************//
	
	//--- 7. SELECT BY IMSI & DATE, COUNT FAILURES, SUM DURATION ---//
	public Collection<?> getNumFailuresAndDurationByDate(Date startDate, Date endDate) {
		return dao.getNumFailuresAndDurationByDate(startDate, endDate);
	}
	
	//--- 8. SELECT BY UE_TYPE, RETURN UNIQUE EVENT_ID, CAUSE_CODE COMBINATIONS & COUNT ---//
	public Collection<?> countByModelEventIdCauseCode(String ue_type){
		return dao.countByModelEventIdCauseCode(ue_type);
	}

    //--- 9. SELECT BY DATE, RETURN TOP 10 MARKET/OPERATOR/CELL_ID COMBINATIONS ---//
	@Override
	public Collection<BaseData> top10MarketOperatorCell(Date startDate, Date endDate) {
		return dao.top10MarketOperatorCell(startDate, endDate);
	}
	
	//--- 10. SELECT BY DATE, RETURN TOP 10 IMSIs ---//

	//***********************//
	//*** SA ONLY QUERIES ***//
	//***********************//
	
	//assign users
	
	//--- DATA IMPORT ---//
	
}
