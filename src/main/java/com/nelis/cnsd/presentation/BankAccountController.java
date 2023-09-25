package com.nelis.cnsd.presentation;

import com.nelis.cnsd.presentation.dto.request.UpdateBankAccountDTO;
import com.nelis.cnsd.presentation.dto.response.BlockBankAccountResponse;
import com.nelis.cnsd.presentation.dto.response.GetBankAccountResponse;
import com.nelis.cnsd.presentation.dto.response.RemoveBankAccountResponse;
import com.nelis.cnsd.presentation.dto.response.UpdateBankAccountResponse;
import com.nelis.cnsd.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @GetMapping("/{id}")
    public ResponseEntity<GetBankAccountResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(GetBankAccountResponse.from(bankAccountService.get(id)));
    }

    @GetMapping("/{id}/owners")
    public ResponseEntity<GetBankAccountResponse> getAccountsFromOwners(@PathVariable Long id) {
        return ResponseEntity.ok(GetBankAccountResponse.from(bankAccountService.get(id)));
    }

    @GetMapping
    public ResponseEntity<List<GetBankAccountResponse>> getAllAccounts() {
        return ResponseEntity.ok((bankAccountService.getAll()).stream().map(GetBankAccountResponse::from).collect(Collectors.toList()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BlockBankAccountResponse> block(@PathVariable Long id) {
        return ResponseEntity.ok(BlockBankAccountResponse.from(bankAccountService.block(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateBankAccountResponse> update(@PathVariable Long id, @RequestBody UpdateBankAccountDTO dto) {
        return ResponseEntity.ok(UpdateBankAccountResponse.from(bankAccountService.update(id, dto)));
    }

    @PutMapping("/{bankId}/owners/{customerId}")
    public ResponseEntity<UpdateBankAccountResponse> addOwner(@PathVariable Long bankId, @PathVariable Long customerId) {
        return ResponseEntity.ok(UpdateBankAccountResponse.from(bankAccountService.addOwner(bankId, customerId)));
    }

    @DeleteMapping("/{bankId}/owners/{customerId}")
    public ResponseEntity<UpdateBankAccountResponse> removeOwner(@PathVariable Long bankId, @PathVariable Long customerId) {
        return ResponseEntity.ok(UpdateBankAccountResponse.from(bankAccountService.removeOwner(bankId, customerId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RemoveBankAccountResponse> remove(@PathVariable Long id) {
        return ResponseEntity.ok(RemoveBankAccountResponse.from(bankAccountService.remove(id)));
    }
}
