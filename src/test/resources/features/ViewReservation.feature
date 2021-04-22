Feature: View details of a reservation made by a Offi-user
  Scenario: Offi-user want to see his reservation
    Given Offi-user is within in the Home window
    When he clicks in the Reservations button
    Then the system shows the Reservation window with all the Reservations

  Scenario: Offi-user want to see details of his Reservation
    Given Offi-user is within of the Reservations window
    When he clicks in a Reservation
    Then the system shows details of the selected Reservation