Feature: Loan States API

  Verify that information provided by this API is as per the specifications.

  Scenario: Recover the elegible states successfully
    Given User wants to recover the elegible states from API "https://credapi.credify.tech/api/loanapp/v1/states"
    When User performs the call to the API
    Then The result code is 200
    And All the state names returned are valid
    And Total state count is 48
    And Only one state has a min age 19
    And "Georgia" is the only state with min loan amount of 3005