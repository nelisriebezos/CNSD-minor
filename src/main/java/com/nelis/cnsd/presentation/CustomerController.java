package com.nelis.cnsd.presentation;

import com.nelis.cnsd.service.CustomerService;
import com.nelis.cnsd.service.dto.request.NewCustomerDTO;
import com.nelis.cnsd.service.dto.request.UpdateCustomerDTO;
import com.nelis.cnsd.service.dto.response.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{BSN}")
    public ResponseEntity<GetCustomerResponse> get(@PathVariable String BSN) {
        return ResponseEntity.ok(GetCustomerResponse.from(customerService.get(BSN)));
    }

    @GetMapping("/{BSN}/accounts")
    public ResponseEntity<GetCustomerAccountsResponse> getBankAccountsOfCustomer(@PathVariable String BSN) {
        return ResponseEntity.ok(GetCustomerAccountsResponse.from(customerService.getBankAccountsOfCustomer(BSN)));
    }

    @PostMapping
    public ResponseEntity<NewCustomerResponse> create(@RequestBody NewCustomerDTO dto) {
        return ResponseEntity.ok(NewCustomerResponse.from(customerService.create(dto)));
    }

    @PatchMapping
    public ResponseEntity<UpdateCustomerResponse> update(@RequestBody UpdateCustomerDTO dto) {
        return ResponseEntity.ok(UpdateCustomerResponse.from(customerService.update(dto)));
    }

    @DeleteMapping("/{BSN}")
    public ResponseEntity<RemoveCustomerResponse> remove(@PathVariable String BSN) {
        return ResponseEntity.ok(RemoveCustomerResponse.from(customerService.remove(BSN)));
    }
}
