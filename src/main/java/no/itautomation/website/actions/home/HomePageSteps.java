/**
 * 
 */
package no.itautomation.website.actions.home;

import no.itautomation.website.hooks.ItAutomationDriver;
import no.itautomation.website.pages.home.HomePage;

/**
 * @author senth
 *
 */
public class HomePageSteps {
	
	 private String HOME_TEXT = "//div[@class='elementor-widget-container']//p[text()='<replace>']";

		private ItAutomationDriver driver;
		private HomePage homePage;

		public HomePageSteps(ItAutomationDriver driver) {
			this.driver = driver;
			homePage = new HomePage(driver);
		}
		
		
}
