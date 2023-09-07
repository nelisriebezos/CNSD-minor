package com.nelis.cnsd.domain;

import com.nelis.cnsd.domain.exceptions.LastOwnerException;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.nelis.cnsd.domain.AccountStatus.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String IBAN;
    private double saldo;
    @Builder.Default
    private AccountStatus status = ACTIVE;
    @Builder.Default
    @ManyToMany
    private List<Customer> owners = new ArrayList<>();

    public BankAccount addOwner(Customer customer) {
        if (!owners.contains(customer)) owners.add(customer);
        return this;
    }

    public BankAccount removeOwner(Customer customer) {
        if (owners.size() == 1) {
            throw new LastOwnerException();
        } else {
        owners.remove(customer);
        }
        return this;
    }

    public BankAccount block() {
        status = BLOCKED;
        return this;
    }

    public boolean isActive() {
        return status.equals(ACTIVE);
    }

    public List<String> getOwnerBSNs() {
        return owners.stream().map(Customer::getBSN).collect(Collectors.toList());
    }
}
