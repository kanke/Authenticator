package org.kanke.resource;

import org.kanke.Authenticator;
import org.kanke.response.StandardResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Authenticator
@Path("/welcome")
public class WelcomeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response displayAuthorizationMessage() {
        return Response.ok(new StandardResponse(StandardResponse.SUCCESS, "Welcome! You have now been Authorized")).build();
    }
}
