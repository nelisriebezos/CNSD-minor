package com.nelis.cnsd.presentation.dto.response;

import com.nelis.cnsd.domain.Customer;

import java.util.List;

public record GetCustomerResponse(String BSN, String name, List<String> accounts) {
    public static GetCustomerResponse from(Customer customer) {
        return new GetCustomerResponse(
                customer.getBSN(),
                customer.getName(),
                customer.getAccountIBANs()
        );
    }
}
