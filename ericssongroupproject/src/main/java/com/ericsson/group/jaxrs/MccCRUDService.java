package com.ericsson.group.jaxrs;

import com.ericsson.group.entities.MccMnc;
import com.ericsson.group.services.MccService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by mypc1 on 04/04/2017.
 */
@Path("/mcc")
public class MccCRUDService {
    @Inject
    private MccService service;

    //--- SELECT ALL (NO FRONT END) ---//
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<MccMnc> getMcc(){
        return service.getAllMcc();
    }
}
