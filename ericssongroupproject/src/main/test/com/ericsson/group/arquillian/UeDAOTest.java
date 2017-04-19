package com.ericsson.group.arquillian;

import com.ericsson.group.dao.EventDAO;
import com.ericsson.group.dao.UeDAO;
import com.ericsson.group.entities.EventCause;
import com.ericsson.group.entities.UE;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by mypc1 on 17/04/2017.
 */

@RunWith(Arquillian.class)
public class UeDAOTest {
    @Inject
    UeDAO ueDAO;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(UeDAO.class.getPackage())
                .addPackage(UE.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public final void test_eventdao_getallevents() throws Exception {
        Assert.assertNotNull(ueDAO);

        List<UE> testUes = (List<UE>) ueDAO.getAllUE();
        Assert.assertEquals("test ue marketing_name", testUes.get(0).getMarketing_name());
    }
}
