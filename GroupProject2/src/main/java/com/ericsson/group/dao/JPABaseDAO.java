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
	public Collection<?> getBaseData() {
		Query query = em.createQuery("from BaseData");
		return query.getResultList();
	}
	
	//--- SELECT BY IMSI ---//
	public Collection<BaseData> getBaseDataByImsi(Long imsi){
		Query query = em.createQuery("from BaseData c where c.imsi = :imsi");
		query.setParameter("imsi",imsi);
		return (List<BaseData>)query.getResultList();
	}
	
	//--- SELECT BY DATE ---//
	public Collection<BaseData> getBaseDataByDate(Date startDate, Date endDate){
		Query query = em.createQuery("from BaseData c where c.date >= :sDate AND c.date <= :eDate");
		query.setParameter("sDate",startDate);
		query.setParameter("eDate", endDate);
		return (List<BaseData>)query.getResultList();
	}
	
	//--- COUNT BY MODEL AND DATE ---//
	public Long countByModelAndDate(Integer ue_type, Date startDate, Date endDate){
		Query query = em.createQuery("select count (c) from BaseData c where c.ue_type = :ue_type AND c.date >= :sDate AND c.date <= :eDate");
		query.setParameter("ue_type", ue_type);
		query.setParameter("sDate",startDate);
		query.setParameter("eDate", endDate);
		return (Long)query.getSingleResult();
	}

	//--- SELECT BY DATE NUM FAILURES AND DURATION---//
	public List<Object[]> getNumFailuresAndDurationByDate(Date startDate, Date endDate) {
		Query query = em.createQuery("select imsi, count(c), sum(duration) from BaseData c where c.date >= :sDate AND c.date <= :eDate group by imsi");
		query.setParameter("sDate",startDate);
		query.setParameter("eDate", endDate);
		return (List<Object[]>)query.getResultList();
	}
	
	//--- SELECT BY IMSI, COUNT FAILURES BY DATE ---//
	public Long getFailuresByDate(Long imsi, Date startDate, Date endDate) {
		Query query = em.createQuery("select count (c) from BaseData c where c.imsi = :imsi AND c.date >= :sDate AND c.date <= :eDate");
		query.setParameter("imsi", imsi);
		query.setParameter("sDate",startDate);
		query.setParameter("eDate", endDate);
		System.out.println("DAO");
		return (Long)query.getSingleResult();
	}
	
	//--- LILY COUNT OF EVENTID/CAUSECODE BY MODEL ---//
	public Collection<?> countByModelEventIdCauseCode(Integer ue_type){
		Query query = em.createQuery("select count (c), c.event_id, c.cause_code from BaseData c where c.ue_type = :ue_type group by c.event_id, c.cause_code");
		query.setParameter("ue_type", ue_type);
		return (List<?>)query.getResultList();
	}

}
