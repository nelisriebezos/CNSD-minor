package com.nelis.cnsd.presentation;

import com.nelis.cnsd.presentation.dto.response.*;
import com.nelis.cnsd.service.BankAccountService;
import com.nelis.cnsd.service.CustomerService;
import com.nelis.cnsd.presentation.dto.request.NewBankAccountDTO;
import com.nelis.cnsd.presentation.dto.request.NewCustomerDTO;
import com.nelis.cnsd.presentation.dto.request.UpdateCustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final BankAccountService bankAccountService;

    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(GetCustomerResponse.from(customerService.get(id)));
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<GetCustomerAccountsResponse> getBankAccountsOfCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(GetCustomerAccountsResponse.from(customerService.getBankAccountsOfCustomer(id)));
    }

    @PostMapping("/{id}/accounts")
    public ResponseEntity<NewBankAccountResponse> createAccount(@PathVariable Long id, @RequestBody NewBankAccountDTO dto) {
        return ResponseEntity.ok(NewBankAccountResponse.from(bankAccountService.create(id, dto)));
    }

    @PostMapping
    public ResponseEntity<NewCustomerResponse> createCustomer(@RequestBody NewCustomerDTO dto) {
        return ResponseEntity.ok(NewCustomerResponse.from(customerService.create(dto)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateCustomerResponse> update(@PathVariable Long id, @RequestBody UpdateCustomerDTO dto) {
        return ResponseEntity.ok(UpdateCustomerResponse.from(customerService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RemoveCustomerResponse> remove(@PathVariable Long id) {
        return ResponseEntity.ok(RemoveCustomerResponse.from(customerService.remove(id)));
    }
}
