Feature: Train Ticket Book from IRCTC Website

  @Smoketest
  Scenario Outline: End to End Ticket booking from IRCTC Website
    Given User is present in IRCTC Website
    When User will be login through "<username>" and "<password>"
    And Purchase Ticket from "<Source>" to "<Destination>" on "<date>"
    And Compare Train "<Train1>" and "<Train2>" where ticket is available
    And Fill the passengers details following:
      | Debashis |
      | Soma     |
      | Liza     |
    Then Make the Payment and Confirm ticket book

    Examples: 
      | username      | password   | Source | Destination | date              | Train1         | Train2         |
      | dipesh1006pal | Namita@123 | HWH    | NDLS         | 19-September-2024 | POORVA EXPRESS | NETAJI EXPRESS |
