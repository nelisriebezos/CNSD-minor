package com.nelis.cnsd.cucumber;

import com.nelis.cnsd.presentation.dto.request.NewBankAccountDTO;
import com.nelis.cnsd.presentation.dto.request.NewCustomerDTO;
import com.nelis.cnsd.presentation.dto.response.GetBankAccountResponse;
import com.nelis.cnsd.presentation.dto.response.NewBankAccountResponse;
import com.nelis.cnsd.presentation.dto.response.NewCustomerResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
public class CustomerStepDefs {

    private RestTemplate restTemplate = new RestTemplate();
    private String path = "http://localhost:8081/customers";

    private Long id;
    private List<GetBankAccountResponse> bankAccounts;

    @Given("A customer is registered")
    public void aCustomerIsRegistered() {
        HttpEntity<NewCustomerDTO> requestEntity = new HttpEntity<>(new NewCustomerDTO("BSN", "Niels"));
        ResponseEntity<NewCustomerResponse> response = restTemplate.exchange(
                path,
                HttpMethod.POST,
                requestEntity,
                NewCustomerResponse.class
        );
        id = response.getBody().id();
        log.info("Create user response: " + response.getBody());
    }

    @Given("One bank account is created for customer")
    public void oneBankAccountIsCreated() {
        HttpEntity<NewBankAccountDTO> requestEntity = new HttpEntity<>(new NewBankAccountDTO("IBAN", 125.0));
        ResponseEntity<NewBankAccountResponse> response = restTemplate.exchange(
                path + "/" + id + "/accounts",
                HttpMethod.POST,
                requestEntity,
                NewBankAccountResponse.class
        );
        log.info("Create account response: " + response.getBody());
    }

    @When("Customer asks what bank accounts he has")
    public void customerAsksWhatBankAccountsHeHas() {
        ResponseEntity<List<GetBankAccountResponse>> response = restTemplate.exchange(
                path + "/" + id + "/accounts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        bankAccounts = response.getBody();
        log.info("Get all accounts response: " + response.getBody());
    }

    @Then("They would see all bank accounts")
    public void heWouldSeeAllBankAccounts() {
        assertEquals(1, bankAccounts.size());
    }
}
