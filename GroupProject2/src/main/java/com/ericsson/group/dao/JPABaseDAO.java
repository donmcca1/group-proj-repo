package com.ericsson.group.dao;

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
public class JPABaseDAO implements BaseDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public Collection<BaseData> getBaseData() {
		Query query = em.createQuery("from BaseData");
		List<BaseData> list = query.getResultList();
		return list;
	}

}
