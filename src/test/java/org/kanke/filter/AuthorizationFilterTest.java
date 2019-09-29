package org.kanke.filter;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kanke.util.TokenHelper;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.crypto.SecretKey;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;

import static org.kanke.util.TokenHelper.getInstance;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;


@RunWith(PowerMockRunner.class)
@PrepareForTest({TokenHelper.class, Keys.class})
@PowerMockIgnore({ "org.mockito.*", "javax.crypto.*"})
public class AuthorizationFilterTest {

    private static final String PRIVATE_KEY = "Private-Key";
    private static final String INCORRECT_PRIVATE_KEY = "expectedPrivateKey";
    private TokenHelper tokenHelper;
    private ContainerRequestContext containerRequestContext;
    private AuthorizationFilter authorizationFilter;
    private SecretKey key;

    @Before
    public void setUp() {
        containerRequestContext = mock(ContainerRequestContext.class);
        mockStatic(Keys.class);
        key = mock(SecretKey.class);
        when(Keys.secretKeyFor(SignatureAlgorithm.HS256)).thenReturn(key);
        authorizationFilter = new AuthorizationFilter();

        tokenHelper = spy(TokenHelper.class);
        mockStatic(TokenHelper.class);
        when(getInstance()).thenReturn(tokenHelper);
    }

    @Test(expected = WebApplicationException.class)
    public void shouldNotFilterRequestDueToIncorrectPrivateKey() {
        when(containerRequestContext.getHeaderString(PRIVATE_KEY)).thenReturn(INCORRECT_PRIVATE_KEY);

        authorizationFilter.filter(containerRequestContext);

        verify(tokenHelper, times(0)).claimKey(INCORRECT_PRIVATE_KEY);
    }

    @Test(expected = WebApplicationException.class)
    public void shouldNotFilterRequestDueToMissingHeader() {
        when(containerRequestContext.getHeaderString(PRIVATE_KEY)).thenReturn(null);

        authorizationFilter.filter(containerRequestContext);

        verify(authorizationFilter, times(0)).filter(containerRequestContext);
    }
}
