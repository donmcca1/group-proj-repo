package com.ericsson.group.arquillian;

import com.ericsson.group.dao.BaseDAO;
import com.ericsson.group.dao.JPABaseDAO;
import com.ericsson.group.entities.BaseData;
import com.ericsson.group.jaxrs.BaseCRUDService;
import com.ericsson.group.services.BaseService;
import com.ericsson.group.utilities.EditExcel;
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
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

import javax.ws.rs.core.MediaType;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;


/**
 * Created by mypc1 on 23/04/2017.
 */
@RunWith(Arquillian.class)
public class BaseJaxRSTest {

    private static final String REST_PATH = "front/rest";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(JPABaseDAO.class.getPackage())
                .addPackage(BaseData.class.getPackage())
                .addPackage(BaseService.class.getPackage())
                .addPackage(BaseCRUDService.class.getPackage())
                .addAsResource("resources-wildfly/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .setWebXML("resources-wildfly/test-web.xml")
                ;
    }
    @ArquillianResource
    URL base;

    @Test
    public void testGetBaseDataClientRequest() throws Exception
    {
        // GET http://localhost:8080/test/front/rest/base
        System.out.println(base + REST_PATH);
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/cause").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);
        request.queryParameter( "imsi",344930000000011L);

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals(200, responseObj.getStatus());


        Assert.assertEquals(responseObj.getEntity(), "[[1,\"test event description\"]]");
    }

    /*@Test
    public void testBaseHTTPUnit(@ArquillianResource URL base) throws Exception
    {
        WebConversation webConversation = new WebConversation();
        GetMethodWebRequest request = new GetMethodWebRequest(base + "front/rest/base");
        WebResponse response = webConversation.getResponse(request);
        System.out.println(response.getText());
    }*/

    @Test
    public void query1()  throws Exception{
       ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/{imsi}").toExternalForm());
       request.header("Accept", MediaType.APPLICATION_JSON);
       request.pathParameter( "imsi",344930000000011L);

       ClientResponse<String> responseObj = request.get(String.class);

       Assert.assertEquals(true, responseObj.getEntity().contains("\"id\":1"));
    }

    @Test
    public void query2() throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/date/imsi").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);
        request.queryParameter( "imsi",344930000000011L);
        request.queryParameter( "start","2013-01-11");
        request.queryParameter( "end","2013-01-11");

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals("1", responseObj.getEntity());
    }

    @Test
    public void query3() throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/cause").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);
        request.queryParameter( "imsi",344930000000011L);

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals("[[1,\"test event description\"]]", responseObj.getEntity());
    }

    @Test
    public void query4()throws Exception {
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/date/").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        request.queryParameter( "start","2013-01-11");
        request.queryParameter( "end","2013-01-11");

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals(true, responseObj.getEntity().contains("\"imsi\":344930000000011"));
    }

    @Test
    public void query5()throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/date/ue_type").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        request.queryParameter( "ue_type","test ue ue_type");
        request.queryParameter( "start","2013-01-11");
        request.queryParameter( "end","2013-01-11");

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals("0", responseObj.getEntity());
    }


    @Test
    public void query6() throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/cause/imsi").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        request.queryParameter( "cause_code",1);

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals(true, responseObj.getEntity().contains("\"imsi\":344930000000011"));
    }

    @Test
    public void query7() throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/numfail").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        request.queryParameter( "start","2013-01-11");
        request.queryParameter( "end","2013-01-11");

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals("[[344930000000011,\"test mcc country\",\"test mnc operator\",1,1]]", responseObj.getEntity());
    }

    @Test
    public void query7_For_Graph()throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/numfailcountry").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        request.queryParameter( "start","2013-01-11");
        request.queryParameter( "end","2013-01-11");

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals("[[1,\"test mcc country\"]]",responseObj.getEntity());
    }

    @Test
    public void query8()throws Exception{

        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/ue_type/count").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        request.queryParameter( "ue_type","1");

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals(200, responseObj.getStatus());
    }

    @Test
    public void query9()throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/top10MOC").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        request.queryParameter( "start","2013-01-11");
        request.queryParameter( "end","2013-01-11");

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals("[[\"test mcc country\",\"test mnc operator\",1,1,1,1]]", responseObj.getEntity());
    }

    @Test
    public void query9_For_Graph()throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/count").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals(1,1);
    }

    @Test
    public void query10()throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/top10imsi").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        request.queryParameter( "start","2013-01-11");
        request.queryParameter( "end","2013-01-11");

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals("[[344930000000011,\"test mcc country\",\"test mnc operator\",1]]", responseObj.getEntity());
    }

    @Test
    public void autocomplete_query1()throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/auto/344").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals("[\"344930000000011\"]",responseObj.getEntity());
    }

    @Test
    public void autocomplete_query2()throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/models").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);

        request.queryParameter( "term","test ue model");

        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals("[\"test ue model\"]",responseObj.getEntity());
    }

}
