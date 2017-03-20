package com.ericsson.group.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.ericsson.group.dao.BaseDAO;
import com.ericsson.group.entities.BaseData;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)// Ensures Transactions are used for ACID purposes
public class BaseServiceJPA implements BaseService{
	
	@EJB
	private BaseDAO dao;
	
	//--- SELECT ALL ---//
	public Collection<?> getAllBaseData() {
		return dao.getBaseData();
	}

	//--- SELECT BY IMSI ---//
	public Collection<BaseData> getBaseDataByImsi(Long imsi){
		return dao.getBaseDataByImsi(imsi);
	}
	
	//-- SELECT BY DATE ---//
	public Collection<BaseData> getBaseDataByDate(Date startDate, Date endDate){		
		return dao.getBaseDataByDate(startDate, endDate);
	}

	//-- SELECT BY DATE NUM FAILURES AND DURATION---//
	public Collection<?> getNumFailuresAndDurationByDate(Date startDate, Date endDate) {
		System.out.println("here again in 2");
		return dao.getNumFailuresAndDurationByDate(startDate, endDate);
	}
}
