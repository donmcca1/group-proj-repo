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
	public Collection<BaseData> getAllBaseData() {
		return dao.getBaseData();
	}

	//--- SELECT BY IMSI ---//
	public Collection<BaseData> getBaseDataByImsi(Long imsi){
		return dao.getBaseDataByImsi(imsi);
	}
	
	//-- SELECT BY DATE ---//
	public Collection<BaseData> getBaseDataByDate(String startDate, String endDate){
		
		System.out.println("The date given to the base service is: " + startDate);
		
		//turn string into a date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilStartDate;
		java.util.Date utilEndDate;
		try {
			utilStartDate = sdf.parse(startDate);
			utilEndDate = sdf.parse(endDate);
		} catch (ParseException e) {
			utilStartDate = new Date(0000-00-00);
			utilEndDate = new Date(0000-00-00);
		}
		
		System.out.println("The Util s Date is: " + utilStartDate);
		System.out.println("The Util e Date is: " + utilEndDate);
		
		java.sql.Date sqlStartDate = new Date(utilStartDate.getTime()); 
		java.sql.Date sqlEndDate = new Date(utilEndDate.getTime()); 
		
		System.out.println("The SQL startdate is: " + sqlStartDate);
		System.out.println("The SQL enddate is: " + sqlEndDate);
		
		return dao.getBaseDataByDate(sqlStartDate, sqlEndDate);
	}
}
