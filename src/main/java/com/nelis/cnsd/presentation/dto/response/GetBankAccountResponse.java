package com.nelis.cnsd.presentation.dto.response;

import com.nelis.cnsd.domain.BankAccount;

import java.util.List;

public record GetBankAccountResponse(Long id, String IBAN, double saldo, String status, List<String> owners) {
    public static GetBankAccountResponse from(BankAccount account) {
        return new GetBankAccountResponse(
                account.getId(),
                account.getIBAN(),
                account.getSaldo(),
                account.getStatus().toString(),
                account.getOwnerBSNs()
        );
    }
}
