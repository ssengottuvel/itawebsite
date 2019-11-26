Feature: Request a free consultation - Negative test
    @TestCaseKey=WTA-T4
    Scenario Outline: Request a free consultation - Negative test
        
        Given I visit IT Automation website
        	And I click 'Contact' menu    
            Then I enter my name <Name>
            And I enter my email address <Email>
            And I enter the request message <Message>
            Then I should receive conformation message <Error>
            
            Examples:
            |Name			|Email			|Message									|Error								|								
            |				|				|											|This field is required.			|
            |Senthil		|senthil.com	|This is cool. We would like to know more	|Please enter a valid email address.|
            |Hege			|4343			|Hey, this is cool!							|Please enter a valid email address.|
            