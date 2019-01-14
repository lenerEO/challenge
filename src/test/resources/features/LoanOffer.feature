Feature: Loan Offer WEB

  Verify via the UI that as a borrower - you are seeing loan offers,
  upon filling the required form with valid inputs.

  Background:
    Given User is in main page

  Scenario: Generate a Loan Offer successfully
    When User checks his rate with parameters: "2,000" and "Home Improvement"
    And User fills out random basic information
    And User fills out important information: "$100,000", "$5,000", "leonard.espiritu", "P@ssw0rd"
    And User checks Terms and submits the form
    Then User can see the offer
    When User reenter to the offer
    Then User can see the same offer