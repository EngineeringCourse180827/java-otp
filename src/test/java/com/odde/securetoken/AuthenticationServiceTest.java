package com.odde.securetoken;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    Profile stubProfile = mock(Profile.class);
    Token stubToken = mock(Token.class);
    AuthLogger mockAuthLogger = mock(AuthLogger.class);
    AuthenticationService target = new AuthenticationService(stubProfile, stubToken, mockAuthLogger);

    @Test
    public void is_valid() {
        givenPassword("joey", "91");
        givenToken("000000");

        shouldBeValid("joey", "91000000");
    }

    @Test
    public void is_invalid() {
        givenPassword("joey", "91");
        givenToken("000000");

        shouldBeInvalid("joey", "wrong pass code");
    }

    @Test
    public void save_log_when_invalid() {
        givenPassword("joey", "91");
        givenToken("000000");

        target.isValid("joey", "wrong pass code");

        ArgumentCaptor<String> captor = forClass(String.class);
        verify(mockAuthLogger).save(captor.capture());
        assertThat(captor.getValue()).contains("joey", "login failed");
    }

    private void shouldBeInvalid(String account, String passCode) {
        assertFalse(target.isValid(account, passCode));
    }

    private void shouldBeValid(String account, String passCode) {
        assertTrue(target.isValid(account, passCode));
    }

    private void givenToken(String token) {
        when(stubToken.getRandom(anyString())).thenReturn(token);
    }

    private void givenPassword(String account, String password) {
        when(stubProfile.getPassword(account)).thenReturn(password);
    }

}
