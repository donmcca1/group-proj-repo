package com.ericsson.group.services;

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

	
}
