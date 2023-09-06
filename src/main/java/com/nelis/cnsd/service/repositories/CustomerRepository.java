package com.nelis.cnsd.service.repositories;

import com.nelis.cnsd.domain.Customer;
import com.nelis.cnsd.service.exceptions.CustomerNotFound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class CustomerRepository {
    @Builder.Default
    private List<Customer> customers = new ArrayList<>();

    public Customer save(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public boolean remove(Customer customer) {
        customers.remove(customer);
        return true;
    }

    public Customer getCustomerByBSN(String BSN) {
        return customers.stream().filter(customer -> customer.getBSN().equals(BSN)).findFirst().orElseThrow(CustomerNotFound::new);
    }
}
