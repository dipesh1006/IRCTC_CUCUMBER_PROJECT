Feature: Train Ticket Book from IRCTC Website for Tatkal

  @Lala
  Scenario Outline: End to End Ticket booking from IRCTC Website one special train
    Given User is present in IRCTC Website
    When User will be login through "<username>" and "<password>"
    And Purchase Ticket from "<Source>" to "<Destination>" on "<date>" and class "<class>"
    And Book "<Train1>" Ticket for your journey
    And Fill the passengers details following:
      | Debashis |
      | Soma     |
      | Liza     |
    Then Make the Payment and Confirm ticket book

    Examples: 
      | username      | password   | Source | Destination | class | date             | Train1         |
      | dipesh1006pal | Namita@123 | BPL    | BST         | 3A    | 19-December-2024 | RAPTISAGAR EXP |
