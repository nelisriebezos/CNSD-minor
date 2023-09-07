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

    @GetMapping("/{BSN}")
    public ResponseEntity<GetCustomerResponse> get(@PathVariable String BSN) {
        return ResponseEntity.ok(GetCustomerResponse.from(customerService.get(BSN)));
    }

    @GetMapping("/{BSN}/accounts")
    public ResponseEntity<GetCustomerAccountsResponse> getBankAccountsOfCustomer(@PathVariable String BSN) {
        return ResponseEntity.ok(GetCustomerAccountsResponse.from(customerService.getBankAccountsOfCustomer(BSN)));
    }

    @PostMapping("/{BSN}/accounts")
    public ResponseEntity<NewBankAccountResponse> create(@PathVariable String BSN, @RequestBody NewBankAccountDTO dto) {
        return ResponseEntity.ok(NewBankAccountResponse.from(bankAccountService.create(BSN, dto)));
    }

    @PostMapping
    public ResponseEntity<NewCustomerResponse> create(@RequestBody NewCustomerDTO dto) {
        return ResponseEntity.ok(NewCustomerResponse.from(customerService.create(dto)));
    }

//    BSN meegeven in @Pathvariable in plaats van de DTO.
    @PatchMapping("/{BSN}")
    public ResponseEntity<UpdateCustomerResponse> update(@PathVariable String BSN, @RequestBody UpdateCustomerDTO dto) {
        return ResponseEntity.ok(UpdateCustomerResponse.from(customerService.update(BSN, dto)));
    }

    @DeleteMapping("/{BSN}")
    public ResponseEntity<RemoveCustomerResponse> remove(@PathVariable String BSN) {
        return ResponseEntity.ok(RemoveCustomerResponse.from(customerService.remove(BSN)));
    }
}
