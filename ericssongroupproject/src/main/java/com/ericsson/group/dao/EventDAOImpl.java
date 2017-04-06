package com.ericsson.group.dao;

import com.ericsson.group.entities.EventCause;
import com.ericsson.group.entities.FailureClass;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by mypc1 on 03/04/2017.
 */
@Stateless
@Local
public class EventDAOImpl implements EventDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<EventCause> getAllEventCause() {
        Query query = em.createQuery("from EventCause");
        return (List<EventCause>)query.getResultList();
    }
}
