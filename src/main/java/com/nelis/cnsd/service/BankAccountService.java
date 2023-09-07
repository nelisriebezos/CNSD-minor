package com.nelis.cnsd.service;

import com.nelis.cnsd.domain.BankAccount;
import com.nelis.cnsd.domain.Customer;
import com.nelis.cnsd.presentation.dto.request.*;
import com.nelis.cnsd.service.exceptions.AccountIsBlocked;
import com.nelis.cnsd.service.repositories.BankAccountRepository;
import com.nelis.cnsd.service.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    public BankAccount get(String IBAN) {
        return bankAccountRepository.getAccountByIBAN(IBAN);
    }

    public List<BankAccount> getAll() {
        return bankAccountRepository.getAll();
    }

    public BankAccount create(String BSN, NewBankAccountDTO dto) {
        Customer customer = customerRepository.getCustomerByBSN(BSN);
        BankAccount newAccount = BankAccount.builder()
                .IBAN(dto.IBAN())
                .saldo(dto.saldo())
                .owners(Arrays.asList(customer))
                .build();
        customer.addAccount(newAccount);
        return bankAccountRepository.save(newAccount);
    }

    public BankAccount update(UpdateBankAccountDTO dto) {
        BankAccount account = bankAccountRepository.getAccountByIBAN(dto.IBAN());
        if (account.isActive()) {
            account.setSaldo(dto.saldo());
            return account;
        } else throw new AccountIsBlocked();
    }

    public boolean block(BlockBankAccountDTO dto) {
        BankAccount account = bankAccountRepository.getAccountByIBAN(dto.IBAN());
        account.block();
        return true;
    }

    public boolean remove(String IBAN) {
        BankAccount account = bankAccountRepository.getAccountByIBAN(IBAN);
        List<Customer> owners = account.getOwners();
        owners.forEach(owner -> owner.removeAccount(account));
        bankAccountRepository.remove(account);
        return true;
    }

    public BankAccount addBankAccount(AddAccountOwnershipDTO dto) {
        Customer customer = customerRepository.getCustomerByBSN(dto.BSN());
        BankAccount account = bankAccountRepository.getAccountByIBAN(dto.IBAN());
        if (account.isActive()) {
            account.addOwner(customer);
            customer.addAccount(account);
            return account;
        } else throw new AccountIsBlocked();
    }

    public BankAccount removeBankAccount(RemoveAccountOwnershipDTO dto) {
        Customer customer = customerRepository.getCustomerByBSN(dto.BSN());
        BankAccount account = bankAccountRepository.getAccountByIBAN(dto.IBAN());
        if (account.isActive()) {
            account.removeOwner(customer);
            customer.removeAccount(account);
            return account;
        } else throw new AccountIsBlocked();
    }
}
