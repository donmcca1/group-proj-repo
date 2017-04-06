package com.ericsson.group.services;

import com.ericsson.group.dao.EventDAO;
import com.ericsson.group.entities.EventCause;

import javax.ejb.*;
import java.util.Collection;

/**
 * Created by mypc1 on 03/04/2017.
 */

@Stateless
@Local
@TransactionAttribute(TransactionAttributeType.REQUIRED)// Ensures Transactions are used for ACID purposes
public class EventServiceImpl implements EventService {
    @EJB
    private EventDAO dao;

    @Override
    public Collection<EventCause> getAllEventCause() {
        return dao.getAllEventCause();
    }
}
