package com.nelis.cnsd.domain;

import com.nelis.cnsd.domain.exceptions.LastOwnerException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.nelis.cnsd.domain.AccountStatus.ACTIVE;
import static com.nelis.cnsd.domain.AccountStatus.BLOCKED;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String IBAN;
    private double saldo;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AccountStatus status = ACTIVE;
    @Builder.Default
    @ManyToMany(mappedBy = "accounts")
    @ToString.Exclude
    private List<Customer> owners = new ArrayList<>();
    @CreatedDate
    private Timestamp created;
    @LastModifiedDate
    private Timestamp lastUpdated;

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
