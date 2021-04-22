Feature: Reserve Office
  Scenario: Offi-user views a Office to reserve
    Given Offi-user is within of Offices window
    When he clicks a Office
    Then the system show a window with details of that Office

  Scenario: Offi-User reserve the selected Office
    Given Offi-user is within of the selected Office window
    When he clicks in the button Reserve
    Then the system will register the Reservation