Feature: Request a free consultation via IT Automation website
    @TestCaseKey=WTA-T3
    Scenario: Request a free consultation via IT Automation website
        
        Given I visit IT Automation website
        	And I click 'Services' menu
            And I move to the section 'Let’s make awesome things, together.'
            And I click the button 'get a quote'
            Then I enter my name 'Senthil'
            And I enter my email address 'senthilkumar.sengottuvel@gmail.com'
            And I enter the request message 'This is cool. We would like to know more'
            And I click submit button
            Then I should receive conformation message 'Thanks for contacting us! We will be in touch with you shortly.'