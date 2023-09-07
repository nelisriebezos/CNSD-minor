package com.nelis.cnsd.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String BSN;
    private String name;
    @Builder.Default
    @ManyToMany
    private List<BankAccount> accounts = new ArrayList<>();

    public Customer addAccount(BankAccount bankAccount) {
        if (!accounts.contains(bankAccount)) accounts.add(bankAccount);
        return this;
    }

    public Customer removeAccount(BankAccount bankAccount) {
        accounts.remove(bankAccount);
        return this;
    }

    public List<String> getAccountIBANs() {
        return accounts.stream().map(BankAccount::getIBAN).collect(Collectors.toList());
    }
}
