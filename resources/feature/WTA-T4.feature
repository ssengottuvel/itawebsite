Feature: Request a free consultation - Negative test

  @TestCaseKey=WTA-T4
  Scenario Outline: Request a free consultation - Negative test
    Given I am on home page
    Then I should see the text 'Reliable Test and Process Automation Partner'
    And I click 'Contact' menu
    Then I enter my name '<Name>'
    And I enter my email address '<Email>'
    And I enter the request message '<Message>'
    And I click submit button
    Then I should receive error message '<Error>' for validation '<Validation>'

    Examples: 
      |Validation	| Name    | Email       | Message                                  	| Error                               |
      |All     		|         |             |																						| This field is required.							|
      |Email			| Senthil | senthil.com | This is cool. We would like to know more	| Please enter a valid email address. |
      |Email			| Hege    |        4343 | Hey, this is cool!												| Please enter a valid email address. |
