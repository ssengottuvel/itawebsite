package no.itautomation.website.pages.home;

import org.openqa.selenium.By;

import no.itautomation.website.hooks.ItAutomationDriver;

public class HomePage {
	
    private String HOME_TEXT = "//div[@class='elementor-widget-container']//p[text()='<replace>']";

	protected ItAutomationDriver driver;

	public HomePage(ItAutomationDriver driver) {
		this.driver = driver;
	}
	
	public boolean isHomePageTextExisit(String homeText) {
		return driver.getElement(By.xpath(HOME_TEXT.replace("<replace>", homeText))).exists();
	}

}