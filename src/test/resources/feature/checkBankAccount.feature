Feature: What bank accounts does Niels have?
  Niels wants to know what bank accounts he has
  Scenario: One bank account present
    Given one bank account is created
    When Niels asks what bank accounts he has
    Then He would see all bank accounts