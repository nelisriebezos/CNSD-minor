package com.nelis.cnsd.service.exceptions;

public class CustomerNotFound extends RuntimeException {
    public CustomerNotFound() {
        super("Customer can not be found");
    }
}
