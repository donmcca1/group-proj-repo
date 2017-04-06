package com.ericsson.group.services;

import com.ericsson.group.entities.EventCause;

import java.util.Collection;

/**
 * Created by mypc1 on 03/04/2017.
 */
public interface EventService {
    Collection<EventCause> getAllEventCause();
}
