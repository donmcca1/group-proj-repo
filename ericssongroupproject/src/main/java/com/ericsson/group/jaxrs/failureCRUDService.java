package com.ericsson.group.jaxrs;

import com.ericsson.group.entities.FailureClass;
import com.ericsson.group.services.failureService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by mypc1 on 03/04/2017.
 */
@Path("/failure")
public class failureCRUDService {


        @Inject
        private failureService service;

        //--- SELECT ALL (NO FRONT END) ---//
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Collection<FailureClass> getBaseData(){
            return service.getAllFailureClass();
        }

    }
