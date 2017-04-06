package com.ericsson.group.services;

import com.ericsson.group.dao.BaseDAO;
import com.ericsson.group.dao.FailureDAO;
import com.ericsson.group.entities.FailureClass;

import javax.ejb.*;
import java.util.Collection;

/**
 * Created by mypc1 on 03/04/2017.
 */

@Stateless
@Local
@TransactionAttribute(TransactionAttributeType.REQUIRED)// Ensures Transactions are used for ACID purposes
public class failureServiceImpl implements failureService {
    @EJB
    private FailureDAO dao;

    @Override
    public Collection<FailureClass> getAllFailureClass() {
        return dao.getAllFailureClass();
    }
}
