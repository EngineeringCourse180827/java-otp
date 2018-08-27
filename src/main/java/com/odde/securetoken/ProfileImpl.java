package com.odde.securetoken;

public class ProfileImpl implements Profile {
    @Override
    public String getPassword(String account) {
        return Context.getPassword(account);
    }
}
