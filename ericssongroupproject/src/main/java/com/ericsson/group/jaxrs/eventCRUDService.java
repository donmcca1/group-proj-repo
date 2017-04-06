package com.ericsson.group.jaxrs;

import com.ericsson.group.entities.EventCause;
import com.ericsson.group.services.EventService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by mypc1 on 03/04/2017.
 */
@Path("/event")
public class eventCRUDService {
    @Inject
    private EventService service;

    //--- SELECT ALL (NO FRONT END) ---//
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EventCause> getEventCause(){
        return service.getAllEventCause();
    }
}
