package restapitests;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by OANY on 1/14/2018.
 */
public class TestGoogleAPI
{
    @Test
    public void makeSureThatGoogleIsUp() {
        given().when().get("http://www.google.com").then().statusCode(200);
    }

}
