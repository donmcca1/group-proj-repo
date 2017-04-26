package com.ericsson.group.arquillian;

import com.ericsson.group.dao.BaseDAO;
import com.ericsson.group.entities.BaseData;
import com.ericsson.group.jaxrs.BaseCRUDService;
import com.ericsson.group.services.BaseService;
import com.ericsson.group.utilities.EditExcel;
/*import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;*/
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
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
                .addPackage(BaseDAO.class.getPackage())
                .addPackage(BaseData.class.getPackage())
                .addPackage(BaseService.class.getPackage())
                .addPackage(BaseCRUDService.class.getPackage())
                .addPackage(EditExcel.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
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

        // we're expecting a String back
        ClientResponse<String> responseObj = request.get(String.class);

        Assert.assertEquals(200, responseObj.getStatus());


        Assert.assertEquals(responseObj.getEntity(), "[[1,\"test event description\"]]");
        System.out.println(responseObj.getEntity());
    }

    @Test
    public void testBaseHTTPUnit(@ArquillianResource URL base) throws Exception
    {
        WebConversation webConversation = new WebConversation();
        GetMethodWebRequest request = new GetMethodWebRequest(base + "front/rest/base");
        WebResponse response = webConversation.getResponse(request);

        System.out.println(response.getText());
    }

   @Test public void query1()  throws Exception{
       ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/{imsi}").toExternalForm());
       request.header("Accept", MediaType.APPLICATION_JSON);
       request.pathParameter( "imsi",344930000000011L);

       ClientResponse<String> responseObj = request.get(String.class);

       System.out.println(responseObj.getEntity());
       Assert.assertEquals(true, responseObj.getEntity().contains("\"id\":1"));
    }

    @Test public void query2() throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/date/imsi").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);
        request.queryParameter( "imsi",344930000000011L);
        request.queryParameter( "start","2013-01-11");
        request.queryParameter( "end","2013-01-11");

        ClientResponse<String> responseObj = request.get(String.class);

        System.out.println(responseObj.getEntity());
        Assert.assertEquals("1", responseObj.getEntity());
    }

    @Test public void query3() throws Exception{
        ClientRequest request = new ClientRequest(new URL(base, REST_PATH + "/base/cause").toExternalForm());
        request.header("Accept", MediaType.APPLICATION_JSON);
        request.queryParameter( "imsi",344930000000011L);

        ClientResponse<String> responseObj = request.get(String.class);

        System.out.println(responseObj.getEntity());
        Assert.assertEquals("[[1,\"test event description\"]]", responseObj.getEntity());
    }

    /*@Test public void
    query4(){
        given().
                param("start","2013-01-11").
                param("end","2013-01-11").
                when().
                get(base + "front/rest/base/date/").
                then().
                body("baseDataList.imsi", hasItems(344930000000011L));
    }
*/
    /*@Test public void
    query5(){
        given().
            param("ue_type",21060800).
            param("start","2013-01-11").
            param("end","2013-01-11").
        when().
            get("/rest/base/date/ue_type").
        then().
            body(equalTo("318"));
    }*/

    //Returning Empty
/*    @Test public void
    query6() {
        given().
                param("cause_code",1).
                when().
                get(base + "front/rest/base/imsi").
                then().
                body("baseDataList.imsi", hasItems(344930000000011L));
    }*/

    //query7 BROKEN

   /* @Test public void
    query8(){
        given().
            param("ue_type",33001735).
        when().
            get("/rest/base/ue_type/count").
        then().
            body("[0]", hasItems(112,4097,1),
            		"[1]", hasItems(100,4097,2));
    }

    @Test public void
    query9(){
        given().
            param("ue_type",33001735).
        when().
            get("/rest/base/top10").
        then().
            body("[0]", hasItems(112,4097,1),
            		"[1]", hasItems(100,4097,2));
    }*/

}
