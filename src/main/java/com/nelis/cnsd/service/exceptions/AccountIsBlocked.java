package com.nelis.cnsd.service.exceptions;

public class AccountIsBlocked extends RuntimeException {
    public AccountIsBlocked() {
        super("This account has been blocked");
    }
}
