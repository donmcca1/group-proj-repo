package com.ericsson.group.dao;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ericsson.group.entities.BaseData;

@Stateless
@Local
public class JPABaseDAO implements BaseDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	//--- SELECT ALL ---//
	public Collection<BaseData> getBaseData() {
		Query query = em.createQuery("from BaseData");
		return query.getResultList();
	}
	
	//--- SELECT BY IMSI ---//
	public Collection<BaseData> getBaseDataByImsi(Long imsi){
		Query query = em.createQuery("from BaseData c where c.imsi = :imsi");
		query.setParameter("imsi",imsi);
		return (List<BaseData>)query.getResultList();
	}
	
/*	public Collection<?> getCallFailures(Long imsi, Date dateTime){
		Query query = em.createQuery("from BaseData c where c.imsi = :imsi and c.dateTime = :dateTime");
		query.setParameter("imsi",imsi);
		query.setParameter("dateTime",dateTime);
		return (List<Object>)query.getResultList();
	}*/

}
