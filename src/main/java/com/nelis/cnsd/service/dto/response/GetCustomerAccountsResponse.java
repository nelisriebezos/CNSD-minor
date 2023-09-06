package com.nelis.cnsd.service.dto.response;

import com.nelis.cnsd.domain.BankAccount;

import java.util.List;

public record GetCustomerAccountsResponse(List<BankAccount> accounts) {
    public static GetCustomerAccountsResponse from(List<BankAccount> accounts) {
        return new GetCustomerAccountsResponse(accounts);
    }
}
