package com.nelis.cnsd.service.dto.response;

import com.nelis.cnsd.domain.AccountStatus;
import com.nelis.cnsd.domain.BankAccount;

import java.util.List;

public record UpdateBankAccountResponse(String IBAN, double saldo, AccountStatus status, List<String> owners) {
    public static UpdateBankAccountResponse from(BankAccount account) {
        return new UpdateBankAccountResponse(
                account.getIBAN(),
                account.getSaldo(),
                account.getStatus(),
                account.getOwnerBSNs()
        );
    }
}
