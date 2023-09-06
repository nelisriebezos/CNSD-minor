package com.nelis.cnsd.domain.exceptions;

public class LastOwnerException extends RuntimeException {
    public LastOwnerException() {
        super("You cant remove the last owner, delete the account instead");
    }
}
