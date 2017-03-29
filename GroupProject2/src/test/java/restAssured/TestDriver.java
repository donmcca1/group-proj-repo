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
                body("baseDataList.event_id", hasItems(4098,4097,4097));
    }
    
    @Test public void
    query2() {
        given().
            param("imsi",310560000000012L).
            param("start","2013-11-01").
            param("end","2013-11-01").
        when().
            get("/rest/base/date/imsi").
        then().
                body(equalTo("240"));
    }
    
    @Test public void
    query4(){
        given().
            param("start","2013-11-01").
            param("end","2013-11-01").
        when().
            get("/rest/base/date/").
        then().
            body("baseDataList.imsi", hasItems(344930000000011L,310560000000012L,344930000000011L));
    }
    
    @Test public void
    query5(){
        given().
            param("ue_type",21060800).
            param("start","2013-11-01").
            param("end","2013-11-01").
        when().
            get("/rest/base/date/ue_type").
        then().
            body(equalTo("400"));
    }

}