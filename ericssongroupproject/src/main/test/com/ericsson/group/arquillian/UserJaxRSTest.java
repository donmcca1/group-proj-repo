package com.ericsson.group.arquillian;

import com.ericsson.group.dao.JPABaseDAO;
import com.ericsson.group.entities.BaseData;
import com.ericsson.group.entities.User;
import com.ericsson.group.jaxrs.BaseCRUDService;
import com.ericsson.group.services.BaseService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.MediaType;
import java.net.URL;

/**
 * Created by mypc1 on 27/04/2017.
 */
@RunWith(Arquillian.class)
public class UserJaxRSTest {

    private static final String REST_PATH = "front/rest";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive web = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(JPABaseDAO.class.getPackage())
                .addPackage(BaseData.class.getPackage())
                .addPackage(BaseService.class.getPackage())
                .addPackage(BaseCRUDService.class.getPackage())
                .addAsResource("resources-wildfly/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .setWebXML("resources-wildfly/test-web.xml")
                ;

        return web;
    }
    @ArquillianResource
    URL base;

    @Test
    public void testGetUser() throws Exception
    {
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/users").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        ClientResponse<String> responseObj = request.get(String.class);
        System.out.println(responseObj.getEntity());
        Assert.assertEquals(200, responseObj.getStatus());

        Assert.assertEquals("[{\"id\":1,\"username\":\"test user\",\"password\":\"test_password\",\"role\":\"System Administrator\"}]", responseObj.getEntity());
    }

    @Test
    public void testUserLogin() throws Exception
    {
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/users/{username}/{password}").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);
        request.pathParameter("username", "test user");
        request.pathParameter("password", "test_password");
        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals(200, responseObj.getStatus());
    }

    @Test
    public void testUserCheck() throws Exception
    {
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/users/{username}").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);
        request.pathParameter("username", "test user");
        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals(200, responseObj.getStatus());

        Assert.assertEquals("false", responseObj.getEntity());
    }


}
