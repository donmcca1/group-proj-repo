package restAssured;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

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

}
