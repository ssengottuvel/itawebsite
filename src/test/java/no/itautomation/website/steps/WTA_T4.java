package no.itautomation.website.steps;

import no.itautomation.website.hooks.ItAutomationDriver;
import no.itautomation.website.pages.contact.ContactPage;
import no.itautomation.website.pages.home.HomePage;
import org.openqa.selenium.By;
import com.codeborne.selenide.Condition;

import cucumber.api.java.en.Then;

public class WTA_T4 {
	private ItAutomationDriver itAutomation;
	private ContactPage contactPage;
	private HomePage homePage;

	public WTA_T4(ItAutomationDriver driver) {
		this.itAutomation = driver;
		this.contactPage = new ContactPage(driver);
		this.homePage = new HomePage(driver);
	}

	@Then("I click {string} menu")
	public void i_click_menu(String mnu) {
		itAutomation.getElement(By.xpath(homePage.CONTACT_MENU)).click();
	}

	@Then("I enter my name {string}")
	public void i_enter_my_name(String name) {
		itAutomation.getElement(By.xpath(contactPage.USERNAME)).setValue(name.trim());
	}

	@Then("I enter my email address {string}")
	public void i_enter_my_email_address(String email) {
		itAutomation.getElement(By.xpath(contactPage.EMAIL)).setValue(email.trim());
	}

	@Then("I enter the request message {string}")
	public void i_enter_the_request_message(String message) {
		itAutomation.getElement(By.xpath(contactPage.MESSAGE)).setValue(message.trim());
	}

	@Then("I click submit button")
	public void i_click_submit_button() {
		itAutomation.getElement(By.xpath(contactPage.SUBMIT)).click();
	}

	@Then("I should receive conformation message {string}")
	public void i_should_receive_conformation_message(String confirmation) {
		itAutomation.getElement(By.xpath(contactPage.CONFIRM_MESSAGE.replace("<replace>", confirmation)))
				.shouldHave(Condition.text(confirmation));
	}

	@Then("I should receive error message {string} for validation {string}")
	public void i_should_receive_error_message_for_validation(String error, String validation) {

		if (validation.equals("All")) {
			itAutomation.getElement(By.xpath(contactPage.USERNAME_ERROR)).shouldHave(Condition.text(error.trim()));
			itAutomation.getElement(By.xpath(contactPage.EMAIL_ERROR)).shouldHave(Condition.text(error.trim()));
			itAutomation.getElement(By.xpath(contactPage.MESSAGE_ERROR)).shouldHave(Condition.text(error.trim()));

		} else if (validation.equals("Email")) {
			itAutomation.getElement(By.xpath(contactPage.EMAIL_ERROR)).shouldHave(Condition.text(error.trim()));
		}

	}

}
