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
	
	public Collection<BaseData> getBaseData() {
		Query query = em.createQuery("from BaseData");
		List<BaseData> list = query.getResultList();
		return list;
	}
	
	public Collection<?> getEventIdCauseCode(Long imsi){
		Query query = em.createQuery("select c.imsi, c.eventId, c.causeCode from BaseData c where c.imsi = :imsi");
		query.setParameter("imsi",imsi);
		return (List<Object>)query.getResultList();
	}
	
	public Collection<?> getCallFailures(Long imsi, Date dateTime){
		Query query = em.createQuery("from BaseData c where c.imsi = :imsi and c.dateTime = :dateTime");
		query.setParameter("imsi",imsi);
		query.setParameter("dateTime",dateTime);
		return (List<Object>)query.getResultList();
	}

}
