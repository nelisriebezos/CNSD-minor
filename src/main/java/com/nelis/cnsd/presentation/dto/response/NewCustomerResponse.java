package com.nelis.cnsd.presentation.dto.response;

import com.nelis.cnsd.domain.Customer;

public record NewCustomerResponse(Long id, String BSN, String name) {
    public static NewCustomerResponse from(Customer customer) {
        return new NewCustomerResponse(
                customer.getId(),
                customer.getBSN(),
                customer.getName());
    }
}
