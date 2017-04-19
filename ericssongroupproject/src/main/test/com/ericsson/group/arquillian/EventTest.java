package com.ericsson.group.arquillian;

import com.ericsson.group.dao.EventDAO;
import com.ericsson.group.dao.EventDAOImpl;
import com.ericsson.group.entities.EventCause;
import com.ericsson.group.entities.EventCausePK;
import com.ericsson.group.jaxrs.eventCRUDService;
import com.ericsson.group.services.EventService;
import com.ericsson.group.services.EventServiceImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * Created by mypc1 on 17/04/2017.
 */

@RunWith(Arquillian.class)
public class EventTest {



    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(EventService.class, EventServiceImpl.class, EventDAO.class, EventDAOImpl.class, EventCause.class, EventCausePK.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    @Inject
    EventService eventService;

    @Test
    public final void test_eventdao_getallevents() throws Exception {
        Assert.assertNotNull(eventService);

        List<EventCause> testEvents = (List<EventCause>) eventService.getAllEventCause();
        Assert.assertEquals("test event description", testEvents.get(0).getDescription());
    }


}
