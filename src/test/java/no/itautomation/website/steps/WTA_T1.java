/**
 * 
 */
package no.itautomation.website.steps;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import com.codeborne.selenide.Condition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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
		assertEquals(itAutomation.driver.getWebDriver().getTitle().toString(), "IT Automation – Your Automation Partner");
	}

	@Then("I should see the text {string}")
	public void i_should_see_the_text(String homeText) {
		itAutomation.getElement(By.xpath(homePage.HOME_TEXT)).shouldHave(Condition.text(homeText));
	}

}
