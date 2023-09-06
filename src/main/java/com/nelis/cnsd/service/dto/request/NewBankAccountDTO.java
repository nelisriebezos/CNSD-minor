package com.nelis.cnsd.service.dto.request;


public record NewBankAccountDTO(String IBAN, double saldo, String ownerBSN) {
}
