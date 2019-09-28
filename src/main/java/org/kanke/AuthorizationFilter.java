package org.kanke;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.commons.lang3.StringUtils;
import org.kanke.response.StandardResponse;
import org.kanke.response.UnAuthorizedResponse;
import org.kanke.util.TokenHelper;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Authenticator
@Priority(Priorities.AUTHENTICATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    private static final String PRIVATE_KEY = "Private-Key";
    private TokenHelper tokenHelper = TokenHelper.getInstance();

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        String privateKeyHeaderValue = containerRequestContext.getHeaderString(PRIVATE_KEY);

        if (StringUtils.isBlank(privateKeyHeaderValue))
            throw new WebApplicationException(getUnAuthorizeResponse(PRIVATE_KEY + " is missing in the header"));
        try {
            tokenHelper.claimKey(privateKeyHeaderValue);
        } catch (Exception exception) {
            if (exception instanceof ExpiredJwtException) {
                throw new WebApplicationException(getUnAuthorizeResponse(PRIVATE_KEY + " has expired"));
            } else if (exception instanceof MalformedJwtException) {
                throw new WebApplicationException(getUnAuthorizeResponse(PRIVATE_KEY + " is not correct"));
            }
        }

    }

    private Response getUnAuthorizeResponse(String message) {
        return Response.ok(new UnAuthorizedResponse
                (StandardResponse.SUCCESS, message)).build();
    }
}