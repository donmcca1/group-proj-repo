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
	public Collection<BaseData> getBaseDataByDate(String date){
		
		System.out.println("The date given to the base service is: " + date);
		
		//turn string into a date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate;
		try {
			utilDate = sdf.parse(date);
		} catch (ParseException e) {
			utilDate = new Date(0000-00-00);
		}
		
		System.out.println("The Util Date is: " + utilDate);
		
		java.sql.Date sqlDate = new Date(utilDate.getTime()); 
		
		System.out.println("The SQL date is: " + sqlDate);
		
		return dao.getBaseDataByDate(sqlDate);
	}
}
