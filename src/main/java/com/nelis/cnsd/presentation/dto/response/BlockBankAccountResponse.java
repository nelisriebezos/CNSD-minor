package com.nelis.cnsd.presentation.dto.response;

public record BlockBankAccountResponse(boolean blocked) {
    public static BlockBankAccountResponse from(boolean blocked) {
        return new BlockBankAccountResponse(blocked);
    }
}
