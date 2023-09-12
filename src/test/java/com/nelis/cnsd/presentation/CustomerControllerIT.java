package com.nelis.cnsd.presentation;

import com.nelis.cnsd.domain.BankAccount;
import com.nelis.cnsd.domain.Customer;
import com.nelis.cnsd.presentation.dto.request.NewBankAccountDTO;
import com.nelis.cnsd.presentation.dto.response.GetBankAccountResponse;
import com.nelis.cnsd.presentation.dto.response.GetCustomerResponse;
import com.nelis.cnsd.presentation.dto.response.NewBankAccountResponse;
import com.nelis.cnsd.service.repositories.BankAccountRepository;
import com.nelis.cnsd.service.repositories.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private String path;

    @BeforeEach
    void init() {
        path = "http://localhost:" + port + "/customers/";

    }

    @AfterEach
    void cleanUp() {
        customerRepository.deleteAll();
        bankAccountRepository.deleteAll();
    }

    @Test
    void getCustomer() {
        Customer c = Customer.builder()
                .BSN("BSN")
                .name("Niels")
                .build();
        c = customerRepository.save(c);

        ResponseEntity<GetCustomerResponse> response = restTemplate.getForEntity(
                path + c.getId(),
                GetCustomerResponse.class
        );

        GetCustomerResponse responseBody = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(responseBody.id());
        assertEquals(c.getBSN(), responseBody.BSN());
        assertEquals(c.getName(), responseBody.name());
        assertEquals(c.getAccountIBANs(), responseBody.accounts());
    }

    @Test
    void getAccountsOfCustomer() {
        BankAccount b = BankAccount.builder()
                .IBAN("IBAN")
                .saldo(0.0)
                .build();

        Customer c = Customer.builder()
                .BSN("BSN")
                .name("Niels")
                .build();

        b.addOwner(c);
        b = bankAccountRepository.save(b);
        c.addAccount(b);
        c = customerRepository.save(c);

        ResponseEntity<List<GetBankAccountResponse>> response = restTemplate.exchange(
                path + c.getId() + "/accounts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        List<GetBankAccountResponse> responseBody = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(c.getAccounts().size(), responseBody.size());
    }

    @Test
    void createAccount() {
        Customer c = Customer.builder()
                .BSN("BSN")
                .name("Niels")
                .build();
        c = customerRepository.save(c);

        NewBankAccountDTO dto = new NewBankAccountDTO("iban", 0.0);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<NewBankAccountDTO> requestEntity = new HttpEntity<>(dto, headers);
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        ResponseEntity<NewBankAccountResponse> response = restTemplate.exchange(
                path + "/create",
                HttpMethod.POST,
                requestEntity,
                NewBankAccountResponse.class
        );

        NewBankAccountResponse responseBody = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseBody.id());
        assertEquals("iban", responseBody.IBAN());
        assertEquals(0.0, responseBody.saldo());
        assertEquals(1, customerRepository.findById(c.getId()).get().getAccounts().size());
    }


}
