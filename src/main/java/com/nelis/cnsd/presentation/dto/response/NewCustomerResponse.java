package com.nelis.cnsd.presentation.dto.response;

import com.nelis.cnsd.domain.Customer;

public record NewCustomerResponse(String BSN, String name) {
    public static NewCustomerResponse from(Customer customer) {
        return new NewCustomerResponse(customer.getBSN(),
                customer.getName());
    }
}
