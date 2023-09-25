package com.nelis.cnsd.service.repositories;

import com.nelis.cnsd.domain.BankAccount;
import com.nelis.cnsd.service.exceptions.BankAccountNotFound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    public Optional<BankAccount> getBankAccountByIBAN(String IBAN);
}
