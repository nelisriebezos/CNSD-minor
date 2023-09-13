package com.nelis.cnsd.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepDefs {

    @Given("one bank account is created")
    public void oneBankAccountIsCreated() {
        System.out.println("test");
    }

    @When("Niels asks what bank accounts he has")
    public void nielsAsksWhatBankAccountsHeHas() {
    }

    @Then("He would see all bank accounts")
    public void heWouldSeeAllBankAccounts() {
    }


}
