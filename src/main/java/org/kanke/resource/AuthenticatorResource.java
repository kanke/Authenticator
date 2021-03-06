package org.kanke.resource;

import org.apache.commons.lang3.StringUtils;
import org.kanke.response.AuthenticatedResponse;
import org.kanke.response.StandardResponse;
import org.kanke.util.TokenHelper;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Path("/auth")
public class AuthenticatorResource {
    private static final String BASIC = "Basic";
    private static final String LOWERCASE_BASIC = "basic";
    private static final String REGEX = ":";

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(@HeaderParam("Authorization") String authorization) {
        String privateKey = null;

        if (StringUtils.isNotBlank(authorization) && authorization.toLowerCase().startsWith(LOWERCASE_BASIC)) {
            final String[] values = getCredentials(authorization);
            String userName = values[0];
            String password = values[1];

            if (StringUtils.isBlank(userName))
                return Response.ok(new StandardResponse(StandardResponse.FAILURE, "username cannot be empty!")).build();

            if (StringUtils.isBlank(password))
                return Response.ok(new StandardResponse(StandardResponse.FAILURE, "password cannot be empty!")).build();

            privateKey = TokenHelper.getInstance().generatePrivateKey(userName, password);
        }
        return Response.ok(new AuthenticatedResponse(StandardResponse.SUCCESS, "You are authenticated successfully. Private key will be valid for 15 minutes", privateKey)).build();
    }

    private String[] getCredentials(String authorization) {
        String base64Credentials = authorization.substring(BASIC.length()).trim();
        byte[] credentialsDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credentialsDecoded, StandardCharsets.UTF_8);
        return credentials.split(REGEX, 2);
    }
}
