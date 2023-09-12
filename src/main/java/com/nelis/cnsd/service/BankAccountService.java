package com.nelis.cnsd.service;

import com.nelis.cnsd.domain.BankAccount;
import com.nelis.cnsd.domain.Customer;
import com.nelis.cnsd.presentation.dto.request.NewBankAccountDTO;
import com.nelis.cnsd.presentation.dto.request.UpdateBankAccountDTO;
import com.nelis.cnsd.service.exceptions.AccountIsBlocked;
import com.nelis.cnsd.service.exceptions.BankAccountNotFound;
import com.nelis.cnsd.service.exceptions.CustomerNotFound;
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

    public BankAccount get(Long id) {
        return bankAccountRepository.findById(id).orElseThrow(BankAccountNotFound::new);
    }

    public List<BankAccount> getAll() {
        return bankAccountRepository.findAll();
    }

    public BankAccount create(Long id, NewBankAccountDTO dto) {
        System.out.println("im here");
        Customer customer = customerRepository.findById(id).orElseThrow(BankAccountNotFound::new);

        System.out.println(customer);
        System.out.println("customer");

        BankAccount newAccount = BankAccount.builder()
                .IBAN(dto.IBAN())
                .saldo(dto.saldo())
                .owners(Arrays.asList(customer))
                .build();
        customer.addAccount(newAccount);

        System.out.println(newAccount);
        return bankAccountRepository.save(newAccount);
    }

    public BankAccount update(Long id, UpdateBankAccountDTO dto) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(BankAccountNotFound::new);
        if (account.isActive()) {
            account.setSaldo(dto.saldo());
            bankAccountRepository.save(account);
            return account;
        } else throw new AccountIsBlocked();
    }

    public boolean block(Long id) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(BankAccountNotFound::new);
        account.block();
        bankAccountRepository.save(account);
        return true;
    }

    public boolean remove(Long id) {
        bankAccountRepository.deleteById(id);
        return true;
    }

    public BankAccount addOwner(Long bankId, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFound::new);
        BankAccount account = bankAccountRepository.findById(bankId).orElseThrow(BankAccountNotFound::new);
        if (account.isActive()) {
            account.addOwner(customer);
            customer.addAccount(account);
            return bankAccountRepository.save(account);
        } else throw new AccountIsBlocked();
    }

    public BankAccount removeOwner(Long bankId, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFound::new);
        BankAccount account = bankAccountRepository.findById(bankId).orElseThrow(BankAccountNotFound::new);
        if (account.isActive()) {
            account.removeOwner(customer);
            customer.removeAccount(account);
            return bankAccountRepository.save(account);
        } else throw new AccountIsBlocked();
    }
}
