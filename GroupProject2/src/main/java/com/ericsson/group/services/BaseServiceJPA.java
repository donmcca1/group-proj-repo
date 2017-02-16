package com.ericsson.group.services;

import java.sql.Date;
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
	
	public Collection<BaseData> getAllBaseData() {
		return dao.getBaseData();
	}

	public Collection<?> getEventIdCauseCode(Long imsi){
		return dao.getEventIdCauseCode(imsi);
	}
	
	public Collection<?> getCallFailures(Long imsi, Date date){
		return dao.getCallFailures(imsi, date);
	}
}
