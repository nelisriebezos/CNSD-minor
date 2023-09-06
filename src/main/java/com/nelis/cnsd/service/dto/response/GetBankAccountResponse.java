package com.nelis.cnsd.service.dto.response;

import com.nelis.cnsd.domain.AccountStatus;
import com.nelis.cnsd.domain.BankAccount;

import java.util.List;

public record GetBankAccountResponse(String IBAN, double saldo, AccountStatus status, List<String> owners) {
    public static GetBankAccountResponse from(BankAccount account) {
        return new GetBankAccountResponse(
                account.getIBAN(),
                account.getSaldo(),
                account.getStatus(),
                account.getOwnerBSNs()
        );
    }
}
