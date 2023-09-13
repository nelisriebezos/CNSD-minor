package com.nelis.cnsd.cucumber;

import com.nelis.cnsd.presentation.dto.request.NewCustomerDTO;
import com.nelis.cnsd.presentation.dto.response.NewCustomerResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class CustomerStepDefs {
    private RestTemplate restTemplate = new RestTemplate();
    private String path = "http://localhost:8080/customers";

    @Given("A customer is registered")
    public void aCustomerIsRegistered() {
        System.out.println(path);
        HttpEntity<NewCustomerDTO> requestEntity = new HttpEntity<>(new NewCustomerDTO("BSN", "Niels"));
        ResponseEntity<NewCustomerResponse> response = restTemplate.exchange(
                path,
                HttpMethod.POST,
                requestEntity,
                NewCustomerResponse.class
        );
        System.out.println(response.getBody());
    }

    @Given("one bank account is created")
    public void oneBankAccountIsCreated() {
//        HttpEntity<NewBankAccountDTO> requestEntity2 = new HttpEntity<>(new NewBankAccountDTO("IABN", 125.0));
//        ResponseEntity<NewBankAccountResponse> response2 = restTemplate.exchange(
//                path + response1.getBody().id() + "/accounts",
//                HttpMethod.POST,
//                requestEntity2,
//                NewBankAccountResponse.class
//        );
    }

    @When("Customer asks what bank accounts he has")
    public void customerAsksWhatBankAccountsHeHas() {
    }

    @Then("He would see all bank accounts")
    public void heWouldSeeAllBankAccounts() {
    }


}
