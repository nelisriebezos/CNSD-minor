package com.nelis.cnsd.presentation.dto.response;

import com.nelis.cnsd.domain.Customer;

public record UpdateCustomerResponse(Long id, String BSN, String name) {
    public static UpdateCustomerResponse from(Customer customer) {
        return new UpdateCustomerResponse(
                customer.getId(),
                customer.getBSN(),
                customer.getName());
    }
}
