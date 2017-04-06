package com.ericsson.group.dao;

import com.ericsson.group.entities.EventCause;
import com.ericsson.group.entities.UE;

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
public class UeDAOImpl implements UeDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<UE> getAllUE() {
        Query query = em.createQuery("from UE");
        return (List<UE>)query.getResultList();
    }
}
