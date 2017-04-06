package com.ericsson.group.services;

import com.ericsson.group.entities.BaseData;
import com.ericsson.group.entities.FailureClass;

import java.util.Collection;

/**
 * Created by mypc1 on 03/04/2017.
 */
public interface failureService {
    //--- SELECT ALL (NO FRONT END) ---//
    public Collection<FailureClass> getAllFailureClass();


}
