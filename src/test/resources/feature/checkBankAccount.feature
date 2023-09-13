Feature: What bank accounts does customer have?
  Customer wants to know what bank accounts he has
  Scenario: One bank account present
    Given A customer is registered
    Given one bank account is created
    When Customer asks what bank accounts he has
    Then He would see all bank accounts