package com.ericsson.group.dao;

import com.ericsson.group.entities.MccMnc;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by mypc1 on 04/04/2017.
 */
@Stateless
@Local
public class MccDAOImpl implements MccDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<MccMnc> getAllMcc() {
        Query query = em.createQuery("from MccMnc");
        return (List<MccMnc>)query.getResultList();
    }
}
