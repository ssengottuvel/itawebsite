package no.itautomation.website.pages.contact;

import no.itautomation.website.hooks.ItAutomationDriver;

public class ContactPage {
	 public String USERNAME = "//input[@id='wpforms-6-field_0']";
	 public String EMAIL = "//input[@id='wpforms-6-field_1']";
	 public String MESSAGE = "//textarea[@id='wpforms-6-field_2']";
	 public String SUBMIT = "//button[@id='wpforms-submit-6']";
	 public String USERNAME_ERROR = "//label[@id='wpforms-6-field_0-error']";
	 public String EMAIL_ERROR = "//label[@id='wpforms-6-field_1-error']";
	 public String MESSAGE_ERROR = "//label[@id='wpforms-6-field_2-error']";
	 public String CONFIRM_MESSAGE = "//p[contains(text(),'<replace>')]";
	    
		protected ItAutomationDriver driver;

		public ContactPage(ItAutomationDriver driver) {
			this.driver = driver;
		}
}
