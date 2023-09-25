package com.nelis.cnsd.presentation.dto.response;

public record RemoveBankAccountResponse(boolean removed) {
    public static RemoveBankAccountResponse from(boolean removed) {
        return new RemoveBankAccountResponse(removed);
    }
}