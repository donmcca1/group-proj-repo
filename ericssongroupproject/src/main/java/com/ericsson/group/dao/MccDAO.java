package com.ericsson.group.dao;

import com.ericsson.group.entities.MccMnc;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by mypc1 on 04/04/2017.
 */
@Local
public interface MccDAO {
    Collection<MccMnc> getAllMcc();
}
