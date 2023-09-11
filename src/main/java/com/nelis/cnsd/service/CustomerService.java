package com.nelis.cnsd.service;

import com.nelis.cnsd.domain.BankAccount;
import com.nelis.cnsd.domain.Customer;
import com.nelis.cnsd.presentation.dto.request.NewCustomerDTO;
import com.nelis.cnsd.presentation.dto.request.UpdateCustomerDTO;
import com.nelis.cnsd.service.exceptions.CustomerNotFound;
import com.nelis.cnsd.service.repositories.BankAccountRepository;
import com.nelis.cnsd.service.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer get(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFound::new);
    }

    public List<BankAccount> getBankAccountsOfCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFound::new).getAccounts();
    }

    public Customer create(NewCustomerDTO dto) {
        Customer newCustomer = Customer.builder()
                .BSN(dto.BSN())
                .name(dto.name())
                .build();
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    public Customer update(Long id, UpdateCustomerDTO dto) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFound::new);
        customer.setName(dto.name());
        return customerRepository.save(customer);
    }

    public boolean remove(Long id) {
        customerRepository.deleteById(id);
        return true;
    }
}