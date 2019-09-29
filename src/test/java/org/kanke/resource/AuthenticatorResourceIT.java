package org.kanke.resource;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticatorResourceIT extends BaseTest {

    @Test
    public void shouldAuthenticateUser() {
        Response resp = given().auth().basic("kanke", "test")
                .post("/auth").then()
                .extract()
                .response();

        final ResponseBody body = resp.getBody();
        assertEquals("You are authenticated successfully. Private key will be valid for 15 minutes", body.path("message"));
    }

    @Test
    public void shouldNotAuthenticateUserMissingUserName() {
        Response resp = given().header("Authorization", "Basic Og==")
                .post("/auth").then()
                .extract()
                .response();

        final ResponseBody body = resp.getBody();
        assertEquals("username cannot be empty!", body.path("message"));
    }

    @Test
    public void shouldNotAuthenticateUserMissingPassword() {
        Response resp = given().header("Authorization", "Basic a2Fua2U6")
                .post("/auth").then()
                .extract()
                .response();

        final ResponseBody body = resp.getBody();
        assertEquals("password cannot be empty!", body.path("message"));
    }



}

