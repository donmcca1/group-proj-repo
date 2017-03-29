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
	        //Parameters can also be specified in Get but this is less secure
	    	//get("/rest/base/date/imsi?imsi=310560000000012&start=2013-11-01&end=2013-11-01").
	    	get("/rest/base/date/imsi").
	    then().
	            body(equalTo("240"));
	}

}
