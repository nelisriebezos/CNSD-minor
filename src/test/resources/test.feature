Feature: What bank accounts does Bram have?
  Bram wants to know what bank accounts he has
  Scenario: Three bank accounts present
    Given one bank accounts is created
    When Niels asks what bank accounts he has
    Then He would see all bank accounts