package com.ericsson.group.services;

import com.ericsson.group.dao.MccDAO;
import com.ericsson.group.entities.MccMnc;

import javax.ejb.*;
import java.util.Collection;

/**
 * Created by mypc1 on 04/04/2017.
 */
@Stateless
@Local
@TransactionAttribute(TransactionAttributeType.REQUIRED)// Ensures Transactions are used for ACID purposes
public class MccServiceImpl implements MccService {
    @EJB
    private MccDAO dao;

    @Override
    public Collection<MccMnc> getAllMcc() {
        return dao.getAllMcc();
    }
}
