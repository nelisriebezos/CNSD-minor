package com.nelis.cnsd.presentation.dto.response;

import com.nelis.cnsd.domain.AccountStatus;
import com.nelis.cnsd.domain.BankAccount;

import java.util.List;

public record NewBankAccountResponse(Long id, String IBAN, double saldo, AccountStatus status, List<String> owners) {
    public static NewBankAccountResponse from(BankAccount account) {
        return new NewBankAccountResponse(
                account.getId(),
                account.getIBAN(),
                account.getSaldo(),
                account.getStatus(),
                account.getOwnerBSNs()
        );
    }
}
