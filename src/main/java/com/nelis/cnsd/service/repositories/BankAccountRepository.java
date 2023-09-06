package com.nelis.cnsd.service.repositories;

import com.nelis.cnsd.domain.BankAccount;
import com.nelis.cnsd.service.exceptions.BankAccountNotFound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class BankAccountRepository {
    @Builder.Default
    private List<BankAccount> accounts = new ArrayList<>();

    public BankAccount save(BankAccount account) {
        accounts.add(account);
        return account;
    }

    public List<BankAccount> getAll() {
        return accounts;
    }

    public boolean remove(BankAccount account) {
     accounts.remove(account);
     return true;
    }

    public BankAccount getAccountByIBAN(String IBAN) {
        return accounts.stream().filter(account -> account.getIBAN().equals(IBAN)).findFirst().orElseThrow(BankAccountNotFound::new);
    }

}
