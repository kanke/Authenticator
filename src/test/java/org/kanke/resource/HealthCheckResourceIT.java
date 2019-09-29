package org.kanke.resource;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthCheckResourceIT extends BaseResourceTest {

    @Test
    public void shouldCheckApplicationHealth() {
        Response resp = given()
                .get("/health-check").then()
                .extract()
                .response();

        final ResponseBody body = resp.getBody();
        assertEquals("Authenticator app is up and running :-)", body.prettyPrint());
    }

}
