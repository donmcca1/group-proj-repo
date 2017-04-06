package com.ericsson.group.dao;

import com.ericsson.group.entities.EventCause;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by mypc1 on 03/04/2017.
 */
@Local
public interface EventDAO {
    Collection<EventCause> getAllEventCause();
}
