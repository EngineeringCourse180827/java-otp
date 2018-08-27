package com.odde.securetoken;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AuthenticationServiceTest {

    @Test
    public void is_valid_test() {
        StubProfile stubProfile = new StubProfile();
        StubToken stubToken = new StubToken();
        AuthenticationService target = new AuthenticationService(stubProfile, stubToken);

        boolean actual = target.isValid("joey", "91000000");

        assertTrue(actual);
    }

    class StubProfile implements Profile {

        @Override
        public String getPassword(String account) {
            if (account.equals("joey")) {
                return "91";
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

    class StubToken implements Token {

        @Override
        public String getRandom(String account) {
            return "000000";
        }
    }

}
