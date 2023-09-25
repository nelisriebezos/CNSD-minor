package com.nelis.cnsd.presentation.dto.response;

public record RemoveCustomerResponse(boolean deleted) {
    public static RemoveCustomerResponse from(boolean deleted) {
        return new RemoveCustomerResponse(deleted);
    }
}
