package com.nelis.cnsd.service;

import com.nelis.cnsd.domain.BankAccount;
import com.nelis.cnsd.domain.Customer;
import com.nelis.cnsd.presentation.dto.request.NewCustomerDTO;
import com.nelis.cnsd.presentation.dto.request.UpdateCustomerDTO;
import com.nelis.cnsd.service.repositories.BankAccountRepository;
import com.nelis.cnsd.service.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;

    public Customer get(String BSN) {
        return customerRepository.getCustomerByBSN(BSN);
    }

    public List<BankAccount> getBankAccountsOfCustomer(String BSN) {
        return customerRepository.getCustomerByBSN(BSN).getAccounts();
    }

    public Customer create(NewCustomerDTO dto) {
        Customer newCustomer = Customer.builder()
                .BSN(dto.BSN())
                .name(dto.name())
                .build();
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    public Customer update(String BSN, UpdateCustomerDTO dto) {
        Customer customer = customerRepository.getCustomerByBSN(BSN);
        customer.setName(dto.name());
        return customer;
    }

    public boolean remove(String BSN) {
        Customer customer = customerRepository.getCustomerByBSN(BSN);
        return customerRepository.remove(customer);
    }
}