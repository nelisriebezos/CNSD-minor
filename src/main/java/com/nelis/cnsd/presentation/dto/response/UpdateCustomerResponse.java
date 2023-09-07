package com.nelis.cnsd.presentation.dto.response;

import com.nelis.cnsd.domain.Customer;

public record UpdateCustomerResponse(String BSN, String name) {
    public static UpdateCustomerResponse from(Customer customer) {
        return new UpdateCustomerResponse(customer.getBSN(),
                customer.getName());
    }
}
