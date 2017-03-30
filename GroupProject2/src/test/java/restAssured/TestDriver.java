package restAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class TestDriver {

    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/GroupProject2-0.0.1-SNAPSHOT";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

    }
    
    @Test public void
    get_200_status_code() {
        
        when().
                get("/rest/base").
        then().
                statusCode(200);

    }
    
    @Test public void
    query1() {
        when().
                get("/rest/base/{imsi}",310560000000012L).
        then().
                body("baseDataList.event_id", hasItems(4098,4097,4097),
                		"baseDataList.cause_code", hasItems(0,11,13));
    }
    
    @Test public void
    query2() {
        given().
            param("imsi",310560000000012L).
            param("start","2013-01-11").
            param("end","2013-01-11").
        when().
            get("/rest/base/date/imsi").
        then().
                body(equalTo("384"));
    }
    
    @Test public void
    query3(){
    	given().
        	param("imsi",191911000339931L).
        when().
        	get("/rest/base/cause").
        then().
            body("$", hasItems(6));
    }
    
    @Test public void
    query4(){
        given().
            param("start","2013-01-11").
            param("end","2013-01-11").
        when().
            get("/rest/base/date/").
        then().
            body("baseDataList.imsi", hasItems(344930000000011L,310560000000012L,344930000000011L));
    }
    
    @Test public void
    query5(){
        given().
            param("ue_type",21060800).
            param("start","2013-01-11").
            param("end","2013-01-11").
        when().
            get("/rest/base/date/ue_type").
        then().
            body(equalTo("636"));
    }
    
    //query6 NOT DONE
    
    //query7 BROKEN
    
    @Test public void
    query8(){
        given().
            param("ue_type",33001735).
        when().
            get("/rest/base/ue_type/count").
        then().
            body("[0]", hasItems(112,4097,1),
            		"[1]", hasItems(100,4097,2));
    }
    
/*    @Test public void
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