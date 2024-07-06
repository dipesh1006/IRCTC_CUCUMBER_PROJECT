Feature: Train Ticket Book from IRCTC Website after target one special train

  @Regressiontest
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
      | username      | password   | Source   | Destination | class | date              | Train1         |
      | dipesh1006pal | Namita@123 | HWH      | NDLS        | 3A    | 19-September-2024 | POORVA EXPRESS |
      | dipesh1006pal | Namita@123 | BPL      | HWH         | 2A    | 18-September-2024 | BPL HWH EXP    |
      | dipesh1006pal | Namita@123 | PATNA JN | NDLS        | SL    | 18-September-2024 | SHRAMJEEVI EXP |
