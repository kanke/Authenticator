package org.kanke.util;


import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.kanke.util.TokenHelper.getInstance;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TokenHelper.class})
@PowerMockIgnore("javax.*")
public class TokenHelperTest {

    private static final String EXPECTED_PRIVATE_KEY = "expectedPrivateKey";
    private static final String USER_NAME = "kanke";
    private static final String PASSWORD = "test";
    private TokenHelper tokenHelper;

    @Before
    public void setUp() {
        tokenHelper = mock(TokenHelper.class);
        mockStatic(TokenHelper.class);
        when(getInstance()).thenReturn(tokenHelper);
    }

    @Test
    public void shouldGeneratePrivateKey() {
        when(tokenHelper.generatePrivateKey(USER_NAME, PASSWORD)).thenReturn(EXPECTED_PRIVATE_KEY);
        String actualPrivateKey = tokenHelper.generatePrivateKey(USER_NAME, PASSWORD);

        assertEquals(EXPECTED_PRIVATE_KEY, actualPrivateKey);
        verify(tokenHelper, times(1)).generatePrivateKey(USER_NAME, PASSWORD);
    }

    @Test
    public void shouldClaimPrivateKey() {
        tokenHelper.claimKey(EXPECTED_PRIVATE_KEY);

        verify(tokenHelper, times(1)).claimKey(EXPECTED_PRIVATE_KEY);
    }

    @Test
    public void shouldGetExpirationDate() throws Exception {
        Date expectedExpirationDate = Whitebox.invokeMethod(tokenHelper,
                "getExpirationDate");
        assertEquals(expectedExpirationDate.toString(),  DateUtils.addMinutes(new Date(), 15).toString());
    }

}
