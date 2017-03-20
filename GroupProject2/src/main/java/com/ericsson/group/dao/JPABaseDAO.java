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
	
	//will need to add enddate
	public Collection<BaseData> getBaseDataByDate(Date startDate, Date endDate){
		Query query = em.createQuery("from BaseData c where c.date >= :sDate AND c.date <= :eDate");
		query.setParameter("sDate",startDate);
		query.setParameter("eDate", endDate);
		return (List<BaseData>)query.getResultList();
	}

	public Collection<?> getNumFailuresAndDurationByDate(Date startDate, Date endDate) {
		System.out.println("here aaa");
		Query query = em.createNativeQuery("select imsi, count(*), sum(duration) from base_data where (date_time between ? AND ?) group by imsi;");
		query.setParameter(1, startDate);
		query.setParameter(2, endDate);
		return (List<?>)query.getResultList();
	}

}
