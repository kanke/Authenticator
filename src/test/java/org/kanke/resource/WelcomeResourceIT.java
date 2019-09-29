package org.kanke.resource;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WelcomeResourceIT extends BaseResourceTest {

    @Test
    public void shouldAuthorizeUserToSeeWelcomeMessage() {
        String privateKey = getPrivateKey();
        Response resp = given().header("Private-Key", privateKey)
                .get("/welcome").then()
                .extract()
                .response();

        final ResponseBody body = resp.getBody();
        assertEquals("Welcome! You have now been Authorized", body.path("message"));
    }

    private String getPrivateKey() {
        Response resp = given().header("Authorization", "Basic a2Fua2U6U0hPUA==")
                .post("/auth").then()
                .extract()
                .response();

        final ResponseBody body = resp.getBody();
        return  body.path("privateKey");
    }

}
