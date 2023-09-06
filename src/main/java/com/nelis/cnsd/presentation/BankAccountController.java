package com.nelis.cnsd.presentation;

import com.nelis.cnsd.service.BankAccountService;
import com.nelis.cnsd.service.dto.request.*;
import com.nelis.cnsd.service.dto.response.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @GetMapping("/{IBAN}")
    public ResponseEntity<GetBankAccountResponse> get(@PathVariable String IBAN) {
        return ResponseEntity.ok(GetBankAccountResponse.from(bankAccountService.get(IBAN)));
    }

    @GetMapping
    public ResponseEntity<List<GetBankAccountResponse>> getAllAccounts() {
        return ResponseEntity.ok((bankAccountService.getAll()).stream().map(GetBankAccountResponse::from).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<NewBankAccountResponse> create(@RequestBody NewBankAccountDTO dto) {
        return ResponseEntity.ok(NewBankAccountResponse.from(bankAccountService.create(dto)));
    }

    @PostMapping("/block")
    public ResponseEntity<BlockBankAccountResponse> create(@RequestBody BlockBankAccountDTO dto) {
        return ResponseEntity.ok(BlockBankAccountResponse.from(bankAccountService.block(dto)));
    }

    @PatchMapping
    public ResponseEntity<UpdateBankAccountResponse> update(@RequestBody UpdateBankAccountDTO dto) {
        return ResponseEntity.ok(UpdateBankAccountResponse.from(bankAccountService.update(dto)));
    }

    @PatchMapping("/add")
    public ResponseEntity<UpdateBankAccountResponse> addBankAccount(@RequestBody AddAccountOwnershipDTO dto) {
        return ResponseEntity.ok(UpdateBankAccountResponse.from(bankAccountService.addBankAccount(dto)));
    }

    @PatchMapping("/remove")
    public ResponseEntity<UpdateBankAccountResponse> removeBankAccount(@RequestBody RemoveAccountOwnershipDTO dto) {
        return ResponseEntity.ok(UpdateBankAccountResponse.from(bankAccountService.removeBankAccount(dto)));
    }

    @DeleteMapping("/{BSN}")
    public ResponseEntity<RemoveBankAccountResponse> remove(@PathVariable String BSN) {
        return ResponseEntity.ok(RemoveBankAccountResponse.from(bankAccountService.remove(BSN)));
    }
}
