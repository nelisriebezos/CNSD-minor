Feature: What bank accounts does customer have?
  Customer wants to know what bank accounts he has
  Scenario: One bank account present
    Given A customer is registered
    And One bank account is created for customer
    When Customer asks what bank accounts he has
    Then They would see one bank account