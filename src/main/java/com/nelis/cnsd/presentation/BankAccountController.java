package com.nelis.cnsd.presentation;

import com.nelis.cnsd.presentation.dto.request.BlockBankAccountDTO;
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

    @GetMapping("/{IBAN}")
    public ResponseEntity<GetBankAccountResponse> get(@PathVariable String IBAN) {
        return ResponseEntity.ok(GetBankAccountResponse.from(bankAccountService.get(IBAN)));
    }

    @GetMapping("/{IBAN}/owners")
    public ResponseEntity<GetBankAccountResponse> getAccountsFromOwners(@PathVariable String IBAN) {
        return ResponseEntity.ok(GetBankAccountResponse.from(bankAccountService.get(IBAN)));
    }

    @GetMapping
    public ResponseEntity<List<GetBankAccountResponse>> getAllAccounts() {
        return ResponseEntity.ok((bankAccountService.getAll()).stream().map(GetBankAccountResponse::from).collect(Collectors.toList()));
    }

//    ("/block") NIET DOEN! URL mag geen werkwoorden bevatten.
    @PatchMapping("/{IBAN}")
    public ResponseEntity<BlockBankAccountResponse> block(@PathVariable String IBAN, @RequestBody BlockBankAccountDTO dto) {
        return ResponseEntity.ok(BlockBankAccountResponse.from(bankAccountService.block(IBAN, dto)));
    }

//    Overwegen om dit als PUT te implementeren. Zorgt voor minder logica om objecten te updaten
    @PutMapping("/{IBAN}")
    public ResponseEntity<UpdateBankAccountResponse> update(@RequestBody UpdateBankAccountDTO dto) {
        return ResponseEntity.ok(UpdateBankAccountResponse.from(bankAccountService.update(dto)));
    }

//    POST word ook gebruikt om een nieuwe koppeling aan te maken.
//    PUT kun je gebruiken als je het id van het toe te voegen object al hebt. Voordeel is dat PUT idempotent is.
    @PutMapping("/{IBAN}/owners/{BSN}")
    public ResponseEntity<UpdateBankAccountResponse> addBankAccount(@PathVariable String IBAN, @PathVariable String BSN) {
        return ResponseEntity.ok(UpdateBankAccountResponse.from(bankAccountService.addBankAccount(IBAN, BSN)));
    }

    @DeleteMapping("/{IBAN}/owners/{BSN}")
    public ResponseEntity<UpdateBankAccountResponse> removeBankAccount(@PathVariable String IBAN, @PathVariable String BSN) {
        return ResponseEntity.ok(UpdateBankAccountResponse.from(bankAccountService.removeBankAccount(IBAN, BSN)));
    }

    @DeleteMapping("/{BSN}")
    public ResponseEntity<RemoveBankAccountResponse> remove(@PathVariable String BSN) {
        return ResponseEntity.ok(RemoveBankAccountResponse.from(bankAccountService.remove(BSN)));
    }
}
