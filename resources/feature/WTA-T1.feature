Feature: Check IT Automation webpage is working

  @TestCaseKey=WTA-T1
  Scenario: Check IT Automation webpage is working
    Given I am on home page
    Then I should see the text 'Reliable Test / Process Automation Partner'
