package com.nelis.cnsd.service.dto.response;

import com.nelis.cnsd.domain.AccountStatus;
import com.nelis.cnsd.domain.BankAccount;

import java.util.List;

public record RemoveBankAccountResponse(boolean removed) {
    public static RemoveBankAccountResponse from(boolean removed) {
        return new RemoveBankAccountResponse(removed);
    }
}