/**
 * 
 */
package no.itautomation.website.steps;

import static org.assertj.core.api.Assertions.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import no.itautomation.website.hooks.ItAutomationDriver;
import no.itautomation.website.pages.home.HomePage;

/**
 * @author senth
 *
 */
public class WTA_T1 {

	private ItAutomationDriver itAutomation;
	private HomePage homePage;

	public WTA_T1(ItAutomationDriver driver) {
		this.itAutomation = driver;
		this.homePage = new HomePage(driver);
	}

	@Given("I am on home page")
	public void i_am_on_home_page() {
		assertThat(itAutomation.driver.getWebDriver().getTitle())
				.isEqualToIgnoringCase("IT Automation – Your Automation Partner");

	}

	@Then("I should see the text {string}")
	public void i_should_see_the_text(String string) {
		assertThat(homePage.isHomePageTextExisit(string.trim())).isTrue();
	}

	
}
