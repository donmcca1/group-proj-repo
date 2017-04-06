package com.ericsson.group.services;

import com.ericsson.group.entities.MccMnc;

import java.util.Collection;

/**
 * Created by mypc1 on 04/04/2017.
 */
public interface MccService {
    Collection<MccMnc> getAllMcc();
}
