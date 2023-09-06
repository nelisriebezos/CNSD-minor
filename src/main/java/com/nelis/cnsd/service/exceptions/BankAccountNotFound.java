package com.nelis.cnsd.service.exceptions;

public class BankAccountNotFound extends RuntimeException {
    public BankAccountNotFound() {
        super("Bank account has not been found");
    }
}
