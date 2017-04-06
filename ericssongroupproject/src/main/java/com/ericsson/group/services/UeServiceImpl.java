package com.ericsson.group.services;

import com.ericsson.group.dao.UeDAO;
import com.ericsson.group.entities.UE;

import javax.ejb.*;
import java.util.Collection;

/**
 * Created by mypc1 on 04/04/2017.
 */
@Stateless
@Local
@TransactionAttribute(TransactionAttributeType.REQUIRED)// Ensures Transactions are used for ACID purposes
public class UeServiceImpl implements UeService {
    @EJB
    private UeDAO dao;

    public Collection<UE> getAllUe() {
        return dao.getAllUE();
    }
}
