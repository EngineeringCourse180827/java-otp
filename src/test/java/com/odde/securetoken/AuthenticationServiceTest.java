package com.odde.securetoken;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    Profile stubProfile = mock(Profile.class);
    Token stubToken = mock(Token.class);
    AuthenticationService target = new AuthenticationService(stubProfile, stubToken);

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
