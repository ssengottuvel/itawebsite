Feature: Request a free consultation via IT Automation website - Positive flow

  @TestCaseKey=WTA-T3
  Scenario Outline: Request a free consultation via IT Automation website - Positive flow
    Given I am on home page
    Then I should see the text 'Reliable Test/Process Automation Partner'
    And I click 'Contact' menu
    Then I enter my name '<Name>'
    And I enter my email address '<Email>'
    And I enter the request message '<Message>'
    And I click submit button
    Then I should receive conformation message '<Confirmation>'

    Examples: 
      | Name    									| Email 										| Message            	| Confirmation                        														|
      | Senthilkumar Sengottuvel	|  autotest@itautomation.no | Hey, this is cool! 	| Thanks for contacting us! We will be in touch with you shortly. |
  		| Rohit Senthilkumar				|  autotest@itautomation.no | Hey, I am Rohit!		| Thanks for contacting us! We will be in touch with you shortly. |
      